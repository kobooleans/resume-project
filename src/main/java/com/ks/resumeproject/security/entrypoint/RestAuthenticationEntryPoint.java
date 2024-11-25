package com.ks.resumeproject.security.entrypoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("code", HttpServletResponse.SC_UNAUTHORIZED);
        responseData.put("message", authException.getMessage());
        if(responseData.get("message").equals("자격 증명에 실패하였습니다.")){
            responseData.put("code", "AUTH-F01");
        }else{
            responseData.put("code", "AUTH-F02");
        }
        response.getWriter().write(mapper.writeValueAsString(responseData));

    }
}
