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

            if(token == null || token.equals("null")) {
                TokenDto tokenDto = userService.refreshAccessToken(refreshToken, response);
                token = tokenDto.getAccessToken();

                cookieUtil.addCookie((HttpServletResponse) response, "token", tokenDto.getAccessToken(), EXPIRES_IN);
            }

            cookieUtil.addCookie((HttpServletResponse) response, "refreshed", refreshToken, R_EXPIRES_IN);
        }

        // 2. validateToken으로 토큰 유효성 검사
        if (token != null && !token.equals("null") && jwtTokenProvider.validateToken(token)) {
            // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext에 저장
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
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
