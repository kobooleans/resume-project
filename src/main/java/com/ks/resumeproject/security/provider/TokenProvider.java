package com.ks.resumeproject.security.provider;

import com.ks.resumeproject.security.domain.AccountContext;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.domain.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class TokenProvider {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final Key key;

    // application.yml에서 secret 값 가져와서 key에 저장
    public TokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // Member 정보를 가지고 AccessToken, RefreshToken을 생성하는 메서드
    public TokenDto generateToken(Authentication authentication) {
        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        /** timeout 시간 1시간으로 설정 */
        Date accessTokenExpiresIn = new Date(now + 1000 * 60 * 60);

        AccountDto accountDto = ((AccountContext)authentication.getPrincipal()).getAccountDto();

        accountDto.setPassword(null);

        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .claim("account", accountDto) // account 추가 반영
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                /** timeout 시간 1시간으로 설정 */
                .expiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return TokenDto.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenExpiresIn(accessTokenExpiresIn.getTime())
                .randomId(((AccountContext)authentication.getPrincipal()).getAccountDto().getRandomId())
                .build();
    }

    // Jwt 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken) {
        // Jwt 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // jwt token 내에서 존재하는 AccountDto 정보 재정의 후 principal에 넣기
        LinkedHashMap map = (LinkedHashMap) claims.get("account");
        AccountDto accountDto = new AccountDto( new BigInteger(map.get("id").toString()) , map.get("username").toString(), null, map.get("roleType").toString(), map.get("randomId").toString());

        UserDetails principal = new AccountContext(accountDto , (List<GrantedAuthority>) authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    // 토큰 정보를 검증하는 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith( (SecretKey) key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            logger.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            logger.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            logger.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            logger.info("JWT claims string is empty.", e);
        }
        return false;
    }


    // accessToken
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}
