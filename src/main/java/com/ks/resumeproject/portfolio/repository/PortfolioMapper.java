package com.ks.resumeproject.portfolio.repository;

import com.ks.resumeproject.multi.domain.FileDto;
import com.ks.resumeproject.portfolio.domain.CategoryDto;
import com.ks.resumeproject.portfolio.domain.PortfolioDetailDto;
import com.ks.resumeproject.portfolio.domain.PortfolioDto;
import com.ks.resumeproject.portfolio.domain.PortfolioSkillDetailDto;
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

    BigInteger selectMaxSkillId(PortfolioDto portfolioDto);

    void insertPortSkill(PortfolioDto portfolioDto);

    void insertPortSkillDetail(PortfolioSkillDetailDto portfolioSkillDetailDto);

    BigInteger selectPortSkillId(PortfolioDto portfolioDto);

    List<PortfolioSkillDetailDto> portSkillDetailList(PortfolioDto portfolioDto);

    void deletePortSkill(PortfolioDto portfolioDto);

    void deletePortSkillDetailAll(PortfolioDto portfolioDto);

    List<FileDto> fileList(PortfolioDto portDto);

    int updatePortAttachFile(PortfolioDto portfolioDto);

    int getAttachFileCnt(PortfolioDto portfolioDto);

    void updateAttachFileToNull(PortfolioDto portfolioDto);

    List<PortfolioSkillDetailDto> selectSkillDtailDtos(PortfolioDto portfolioDto);
}
