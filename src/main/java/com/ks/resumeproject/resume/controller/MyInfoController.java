package com.ks.resumeproject.resume.controller;


import com.ks.resumeproject.resume.domain.MyinfoDto;
import com.ks.resumeproject.resume.domain.ResumeDto;
import com.ks.resumeproject.resume.service.MyInfoService;
import com.ks.resumeproject.resume.service.ResumeService;
import com.ks.resumeproject.security.domain.AccountDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/myinfo")
@Tag(name = "myinfo", description = "개인 정보 목록을 위한 api")
public class MyInfoController {

    private final MyInfoService myInfoService;

    @Operation(summary = "이력서 개인 정보 목록 조회", description = "이력서에 사용되는 개인정보 목록을 조회한다.")
    @PostMapping("/getMyInfo")
    public MyinfoDto getMyInfo(@RequestBody AccountDto accountDto){
        return myInfoService.getMyInfo(accountDto);
    }

    @Operation(summary = "이력서 개인 정보 추가", description = "이력서에 사용되는 개인정보 목록을 추가한다.")
    @PostMapping("/setMyInfo")
    public String setMyInfo(@RequestBody MyinfoDto myinfoDto){
        myInfoService.setMyInfo(myinfoDto);

        return "success";
    }
}
