package com.ks.resumeproject.security.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {
    private String username;
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private long tokenExpiresIn;
    private Date tokenExpires;
    private String randomId;
}
