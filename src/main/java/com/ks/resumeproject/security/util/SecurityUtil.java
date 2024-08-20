package com.ks.resumeproject.security.util;

import com.ks.resumeproject.security.domain.AccountContext;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SecurityUtil {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    public AccountContext getAccount() {
        Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
        if(!(authentication.isAuthenticated())){
            logger.error("로그인하지 않은 사용자는 해당 값을 가지고올 수 없습니다. 확인 바랍니다.");
            return null;
        }
        return ((AccountContext) authentication.getPrincipal());
    }
}
