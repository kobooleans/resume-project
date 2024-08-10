package com.ks.resumeproject.security.provider;

import com.ks.resumeproject.security.domain.AccountContext;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.users.service.SecurityService;
import com.ks.resumeproject.security.token.RestAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("restAuthenticationProvider")
public class RestAuthenticationProvider {

    private final SecurityService securityService;
    private final PasswordEncoder passwordEncoder;

    public RestAuthenticationProvider(SecurityService securityService, PasswordEncoder passwordEncoder) {
        this.securityService = securityService;
        this.passwordEncoder = passwordEncoder;
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String loginId = authentication.getName();
        String password = (String) authentication.getCredentials();

        AccountContext accountContext = null;

        AccountDto accountDto = securityService.selectAccount(loginId);

        if(accountDto != null){
            // TODO: 해당 부분을 통한 로그인 과정을 거치나, 해당 부분 이상여부 다시 한번 확인필요성 존재
            List<GrantedAuthority> roles = new ArrayList<>();

            String roleType = accountDto.getRoleType();
            roles.add(new SimpleGrantedAuthority(roleType));

            accountContext = new AccountContext(accountDto, roles, null);
        }

        if(accountContext == null || !passwordEncoder.matches(password, accountContext.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }

        return new RestAuthenticationToken(accountContext.getAuthorities(), accountContext.getAccountDto(), null);
    }

    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(RestAuthenticationToken.class);
    }
}
