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

    List<PortfolioDto> portfolioAllList(PortfolioDto portfolioDto);

    void insertPortfolio(PortfolioDto portfolioDto);

    BigInteger selectMaxPortId(PortfolioDto portfolioDto);

    void insertPortfolioDetail(PortfolioDetailDto portfolioDetailDto);

    PortfolioDto portfolioList(PortfolioDto portfolioDto);

    List<PortfolioDetailDto> portfolioDetailList(PortfolioDto portfolioDto);

    void updatePortfolio(PortfolioDto portfolioDto);

    void deletePortfolio(PortfolioDto portfolioDto);

    void deletePortfolioDetailAll(PortfolioDto portfolioDto);

    int selectCategoryCnt(CategoryDto categoryDto);

    int updatePortfolioImg(PortfolioDto portfolioDto);

    void updateFileUseYn(PortfolioDto portfolioDto);

    void updatePortFileUseYn(PortfolioDto portfolioDto);
}
