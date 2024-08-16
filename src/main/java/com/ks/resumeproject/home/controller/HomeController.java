package com.ks.resumeproject.home.controller;

import com.ks.resumeproject.home.service.HomeService;
import com.ks.resumeproject.home.domain.TitleDto;
import com.ks.resumeproject.security.domain.AccountDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
@Tag(name = "home", description = "타이틀 정보를 관리하기 위한 Controller")
public class HomeController {

    private final HomeService homeService;

    @Operation(summary = "타이틀 조회", description = "타이틀 정보를 가지고 온다.")
    @PostMapping("/selectTitle")
    public TitleDto selectTitle(@RequestBody AccountDto accountDto){
        return homeService.selectTitle(accountDto);
    }

    @Operation(summary = "타이틀 수정", description = "타이틀 정보를 수정한다.")
    @PostMapping("/updateTitle")
    public String updateTitle(@RequestBody TitleDto titleDto){
        homeService.updateTitle(titleDto);
        return "success";
    }
}
