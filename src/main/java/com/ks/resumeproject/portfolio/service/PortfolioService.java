package com.ks.resumeproject.portfolio.service;

import com.ks.resumeproject.portfolio.domain.CategoryDto;
import com.ks.resumeproject.portfolio.domain.PortfolioDto;
import com.ks.resumeproject.security.domain.AccountDto;

import java.util.List;

public interface PortfolioService {
    List<CategoryDto> categoryList(AccountDto accountDto);

    void insertCategory(CategoryDto categoryDto);

    void updateCategory(CategoryDto categoryDto);

    void deleteCategory(CategoryDto categoryDto);

    List<PortfolioDto> portfolioList(PortfolioDto portfolioDto);

    void insertPortfolio(PortfolioDto portfolioDto);
}
