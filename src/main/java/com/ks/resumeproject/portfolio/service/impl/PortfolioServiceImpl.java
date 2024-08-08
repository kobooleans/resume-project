package com.ks.resumeproject.portfolio.service.impl;

import com.ks.resumeproject.portfolio.domain.CategoryDto;
import com.ks.resumeproject.portfolio.repository.PortfolioMapper;
import com.ks.resumeproject.portfolio.service.PortfolioService;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.users.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final SecurityService securityService;

    private final PortfolioMapper portfolioMapper;

    @Override
    public List<CategoryDto> categoryList(AccountDto accountDto) {
        /*로그인 시 사용자 Id에 대한 값을 가지고 있지 않기 때문에 사용자 Id에 대한 쿼리 실행 */
        AccountDto account = securityService.selectUserAccount(accountDto.getUsername());
        accountDto.setId(account.getId());
        return portfolioMapper.categoryList(accountDto);
    }

    @Override
    public void insertCategory(CategoryDto categoryDto) {
        /*사용자 Id가 없는 경우를 대비해 토큰에 있는 username을 가지고 온다.
        * 토큰의 암호화를 풀기위해 SecurityContextHolder 사용 */
        String username = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        if(categoryDto.getId() == null){
            AccountDto account = securityService.selectUserAccount(username);
            categoryDto.setId(account.getId());
        }

        portfolioMapper.insertCategory(categoryDto);
    }
}
