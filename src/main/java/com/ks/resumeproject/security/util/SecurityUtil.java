package com.ks.resumeproject.security.util;

import com.ks.resumeproject.security.domain.AccountContext;
import com.ks.resumeproject.security.domain.AccountDto;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static AccountContext getAccount() {
        return ((AccountContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
