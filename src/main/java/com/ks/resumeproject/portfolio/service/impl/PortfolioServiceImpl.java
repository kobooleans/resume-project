package com.ks.resumeproject.portfolio.service.impl;

import com.ks.resumeproject.portfolio.domain.CategoryDto;
import com.ks.resumeproject.portfolio.domain.PortfolioDetailDto;
import com.ks.resumeproject.portfolio.domain.PortfolioDto;
import com.ks.resumeproject.portfolio.repository.PortfolioMapper;
import com.ks.resumeproject.portfolio.service.PortfolioService;
import com.ks.resumeproject.security.domain.AccountContext;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.util.SecurityUtil;
import com.ks.resumeproject.users.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final SecurityService securityService;

    private final PortfolioMapper portfolioMapper;

    private final SecurityUtil securityUtil;

    @Override
    public List<CategoryDto> categoryList(AccountDto accountDto) {
        /*로그인 시 사용자 Id에 대한 값을 가지고 있지 않기 때문에 사용자 Id에 대한 쿼리 실행 */
        AccountDto account = securityService.selectUserAccount(accountDto.getUsername());
        accountDto.setId(account.getId());
        return portfolioMapper.categoryList(accountDto);
    }

    @Override
    public void insertCategory(CategoryDto categoryDto) {
        /*사용자 Id가 없는 경우를 대비해 토큰에 있는 id를 가지고 온다.
        * 토큰의 암호화를 풀기위해 SecurityContextHolder 사용 */
        AccountContext accountContext = securityUtil.getAccount();

        if(categoryDto.getId() == null){
            categoryDto.setId(accountContext.getAccountDto().getId());
        }

        portfolioMapper.insertCategory(categoryDto);
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        /*사용자 Id가 없는 경우를 대비해 토큰에 있는 id를 가지고 온다.
         * 토큰의 암호화를 풀기위해 SecurityContextHolder 사용 */
        AccountContext accountContext = securityUtil.getAccount();

        if(categoryDto.getId() == null){
            categoryDto.setId(accountContext.getAccountDto().getId());
        }

        portfolioMapper.updateCategory(categoryDto);
    }

    @Override
    public void deleteCategory(CategoryDto categoryDto) {
        /*사용자 Id가 없는 경우를 대비해 토큰에 있는 id를 가지고 온다.
         * 토큰의 암호화를 풀기위해 SecurityContextHolder 사용 */
        AccountContext accountContext = securityUtil.getAccount();

        if(categoryDto.getId() == null){
            categoryDto.setId(accountContext.getAccountDto().getId());
        }

        portfolioMapper.deleteCategory(categoryDto);
    }

    @Override
    public List<PortfolioDto> portfolioList(PortfolioDto portfolioDto) {
        /*로그인 시 사용자 Id에 대한 값을 가지고 있지 않기 때문에 사용자 Id에 대한 쿼리 실행 */
        AccountDto account = securityService.selectUserAccount(portfolioDto.getUsername());
        portfolioDto.setId(account.getId());

        return portfolioMapper.portfolioList(portfolioDto);

    }

    @Override
    public void insertPortfolio(PortfolioDto portfolioDto) {
        AccountContext accountContext = securityUtil.getAccount();

        if(portfolioDto.getId() == null){
            portfolioDto.setId(accountContext.getAccountDto().getId());
        }

        BigInteger maxPortId = portfolioMapper.selectMaxPortId(portfolioDto);

        portfolioDto.setPortId(maxPortId);

        portfolioMapper.insertPortfolio(portfolioDto);

       for(PortfolioDetailDto dto : portfolioDto.getDetailList()){
           if(!dto.getDetailTitle().isEmpty() && !dto.getDetailContent().isEmpty()){
               dto.setPortId(maxPortId);
               dto.setId(portfolioDto.getId());
               dto.setRandomId(portfolioDto.getRandomId());
               /*상세 구분이 필요 없어졌지만 not null이라서 공백 set*/
               dto.setDetailDivision("");

               portfolioMapper.insertPortfolioDetail(dto);
           }
       }
    }
}
