package com.ks.resumeproject.portfolio.service;

import com.ks.resumeproject.portfolio.domain.CategoryDto;
import com.ks.resumeproject.security.domain.AccountDto;

import java.util.List;

public interface PortfolioService {
    List<CategoryDto> categoryList(AccountDto accountDto);

    void insertCategory(CategoryDto categoryDto);

}
