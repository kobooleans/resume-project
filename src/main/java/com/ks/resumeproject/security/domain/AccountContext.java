package com.ks.resumeproject.security.domain;

import com.ks.resumeproject.users.domain.AccountMyPageDto;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class AccountContext implements UserDetails {
    private AccountDto accountDto;
    private final List<GrantedAuthority> roles;
    private final List<AccountMyPageDto> myPageDto;

    public AccountContext(AccountDto accountDto, List<GrantedAuthority> roles, List<AccountMyPageDto> myPageDto) {
      this.accountDto = accountDto;
      this.roles = roles;
      this.myPageDto = myPageDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
    @Override
    public String getPassword() {
        return accountDto.getPassword();
    }
    @Override
    public String getUsername() {
        return accountDto.getUsername();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
