package com.ks.resumeproject.security.filter;

import com.ks.resumeproject.security.domain.TokenDto;
import com.ks.resumeproject.security.provider.TokenProvider;
import com.ks.resumeproject.security.util.CookieUtil;
import com.ks.resumeproject.users.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final TokenProvider jwtTokenProvider;
    private final CookieUtil cookieUtil;
    private final UserService userService;

    private final int EXPIRES_IN = 900; // 토큰사용 가능시간
    private final int R_EXPIRES_IN = 3600; // 리프레시 토큰 가능시간 1시간

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 1. Request Header에서 JWT 토큰 추출
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String token = cookieUtil.getCookie(httpRequest, "token");
        String refreshToken = cookieUtil.getCookie(httpRequest, "refreshed");
        if(refreshToken != null){
            TokenDto tokenDto = userService.refreshAccessToken(refreshToken);

            if(token == null || token.equals("null")) {

                token = tokenDto.getAccessToken();

                cookieUtil.addCookie((HttpServletResponse) response, "token", tokenDto.getAccessToken(), EXPIRES_IN);
            }

            // RefreshToken 갱신 조건 확인 후 만료 시간 연장
            if (shouldExtendRefreshToken(tokenDto)) {
                cookieUtil.addCookie((HttpServletResponse) response, "refreshed", refreshToken, R_EXPIRES_IN);
            }
        }

        // 2. validateToken으로 토큰 유효성 검사
        if (token != null && !token.equals("null") && jwtTokenProvider.validateToken(token)) {
            // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext에 저장
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    /**
     * Refresh Token 갱신 여부를 결정하는 메서드
     */
    private boolean shouldExtendRefreshToken(TokenDto tokenDto) {
        // Refresh Token 만료 시간을 가져옵니다 (예: JWT에서 만료 시간 추출)
        Date expirationTime = tokenDto.getTokenExpires();

        // 현재 시간과 만료 시간의 차이를 계산
        long timeRemaining = expirationTime.getTime() - System.currentTimeMillis();

        // 만료까지 남은 시간이 10분 이하라면 갱신
        return timeRemaining <= TimeUnit.MINUTES.toMillis(10);
    }

    // Request Header에서 토큰 정보 추출
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
