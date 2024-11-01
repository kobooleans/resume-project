package com.ks.resumeproject.manage.controller;

import com.ks.resumeproject.manage.domain.MemberManageDto;
import com.ks.resumeproject.manage.service.MemberManageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/memberManage")
@RequiredArgsConstructor
@Tag(name = "memberManage", description = "설정/회원관리 Controller")
public class MemberManageController {

    private final MemberManageService memberManageService;

    @Operation(summary = "비밀번호 변경", description = "비밀번호를 변경한다.")
    @PostMapping("/updatePw")
    public String updatePw(@RequestBody MemberManageDto memberManageDto){
        memberManageService.updatePw(memberManageDto);
        return "success";
    }
}
