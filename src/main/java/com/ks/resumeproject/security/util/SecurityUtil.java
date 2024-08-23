package com.ks.resumeproject.security.util;

import com.ks.resumeproject.security.domain.AccountContext;
import com.ks.resumeproject.error.domain.ErrorCode;
import com.ks.resumeproject.error.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    public AccountContext getAccount() {
        Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
        if(!(authentication.isAuthenticated())){
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
        return ((AccountContext) authentication.getPrincipal());
    }
}
