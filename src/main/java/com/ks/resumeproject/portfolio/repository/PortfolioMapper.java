package com.ks.resumeproject.portfolio.repository;

import com.ks.resumeproject.portfolio.domain.CategoryDto;
import com.ks.resumeproject.portfolio.domain.PortfolioDetailDto;
import com.ks.resumeproject.portfolio.domain.PortfolioDto;
import com.ks.resumeproject.security.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface PortfolioMapper {
    List<CategoryDto> categoryList(AccountDto accountDto);

    void insertCategory(CategoryDto categoryDto);

    void updateCategory(CategoryDto categoryDto);

    void deleteCategory(CategoryDto categoryDto);

    List<PortfolioDto> portfolioList(PortfolioDto portfolioDto);

    void insertPortfolio(PortfolioDto portfolioDto);

    BigInteger selectMaxPortId(PortfolioDto portfolioDto);

    void insertPortfolioDetail(PortfolioDetailDto portfolioDetailDto);
}
