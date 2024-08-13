package com.ks.resumeproject.portfolio.controller;

import com.ks.resumeproject.portfolio.domain.CategoryDto;
import com.ks.resumeproject.portfolio.service.PortfolioService;
import com.ks.resumeproject.portfolio.service.impl.PortfolioServiceImpl;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.users.service.SecurityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

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
    public String categoryInsert(@RequestBody CategoryDto categoryDto){
        portfolioService.insertCategory(categoryDto);
        return "success";
    }

}
