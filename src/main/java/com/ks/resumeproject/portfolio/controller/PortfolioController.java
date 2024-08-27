package com.ks.resumeproject.portfolio.controller;

import com.ks.resumeproject.portfolio.domain.CategoryDto;
import com.ks.resumeproject.portfolio.domain.PortfolioDto;
import com.ks.resumeproject.portfolio.service.PortfolioService;
import com.ks.resumeproject.portfolio.service.impl.PortfolioServiceImpl;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.users.service.SecurityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
@Tag(name = "portfolio", description = "프토폴리오 등록.수정.삭제, 파일등록, 카테고리 등록 등 포트폴리오 전반적 api")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @Operation(summary = "카테고리 조회", description = "카테고리 정보를 가지고 온다.")
    @PostMapping("/categoryList")
    public List<CategoryDto> categoryList(@RequestBody AccountDto accountDto){
          return portfolioService.categoryList(accountDto);
    }

    @Operation(summary = "카테고리 등록", description = "카테고리 정보를 저장한다.")
    @PostMapping("/addCategory")
    public String insertCategory(@RequestBody CategoryDto categoryDto){
        portfolioService.insertCategory(categoryDto);
        return "success";
    }

    @Operation(summary = "카테고리 수정", description = "카테고리 정보를 수정한다.")
    @PostMapping("/updateCategory")
    public String updateCategory(@RequestBody CategoryDto categoryDto){
        portfolioService.updateCategory(categoryDto);
        return "success";
    }

    @Operation(summary = "카테고리 삭제", description = "카테고리 정보를 삭제한다.")
    @PostMapping("/deleteCategory")
    public ResponseEntity<Map> deleteCategory(@RequestBody CategoryDto categoryDto){
        portfolioService.deleteCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("delYn", "success"));
    }

    @Operation(summary = "포트폴리오 전체 조회", description = "등록되어 있는 포트폴리오 조회한다.")
    @PostMapping("/portfolioAllList")
    public List<PortfolioDto> portfolioList(@RequestBody PortfolioDto portfolioDto){
        return portfolioService.portfolioAllList(portfolioDto);
    }

    @Operation(summary = "포트폴리오 저장", description = "포트폴리오 정보를 저장한다.")
    @PostMapping("/addPortfolio")
    public String insertPortfolio(@RequestBody PortfolioDto portfolioDto){
        portfolioService.insertPortfolio(portfolioDto);
        return "success";
    }

    @Operation(summary = "포트폴리오 상세 조회", description = "포트폴리오 상세 정보를 조회한다.")
    @PostMapping("/portfolioDetailList")
    public PortfolioDto portfolioDetailList(@RequestBody PortfolioDto portfolioDto){
        return portfolioService.portfolioDetailList(portfolioDto);
    }


    @Operation(summary = "포트폴리오 수정", description = "포트폴리오 정보를 수정한다.")
    @PostMapping("/updatePortfolioDetail")
    public String updatePortfolioDetail(@RequestBody PortfolioDto portfolioDto){
        portfolioService.updatePortfolioDetail(portfolioDto);
        return "success";
    }

    @Operation(summary = "포트폴리오 삭제", description = "포트폴리오 삭제한다.")
    @PostMapping("/deletePortfolio")
    public String deletePortfolio(@RequestBody PortfolioDto portfolioDto){
        portfolioService.deletePortfolio(portfolioDto);
        return "success";
    }
}
