package com.ks.resumeproject.security.util;

import com.ks.resumeproject.security.domain.AccountContext;
import com.ks.resumeproject.security.domain.AccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class SecurityUtil {

    private static Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    public static AccountContext getAccount() {
        if((SecurityContextHolder.getContext().getAuthentication().getPrincipal()).equals("anonymousUser")){
            logger.error("로그인하지 않은 사용자는 해당 값을 가지고올 수 없습니다. 확인 바랍니다.");
            return null;
        }
        return ((AccountContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
