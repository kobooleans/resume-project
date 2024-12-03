package com.ks.resumeproject.security.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {
    public String getCookie(HttpServletRequest request, String key) {
        // 쿠키 배열 가져오기
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // 쿠키 이름이 "token"인 경우 값 반환
                if (key.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public void removeCookie(HttpServletResponse response, String key) {
        ResponseCookie cookie = ResponseCookie.from(key, null) // 쿠키 값 설정
                .path("/") // 쿠키 경로
                .maxAge(0) // 쿠키 만료
                .httpOnly(true) // HttpOnly 설정
                .secure(true) // HTTPS에서만 전송
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString()); // 헤더에 쿠키 추가
    }
}
