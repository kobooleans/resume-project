package com.ks.resumeproject.resume.controller;

import com.ks.resumeproject.resume.domain.CoverLetterDto;
import com.ks.resumeproject.resume.service.CoverLetterService;
import com.ks.resumeproject.security.domain.AccountDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coverLetter")
@Tag(name = "coverLetter", description = "자기소개서 등록, 수정, 삭제를 위한 Controller")
public class CoverLetterController {

    private final CoverLetterService coverLetterService;

    @Operation(summary = "이력서 자기소개서 목록을 가져온다.", description = "이력서에 사용되는 자기소개서 목록을 조회한다.")
    @PostMapping("/getCoverLetterList")
    public List<CoverLetterDto> getCoverLetterList(@RequestBody AccountDto accountDto){

        return coverLetterService.getCoverLetterList(accountDto);
    }

    @Operation(summary = "이력서 자기소개서 등록", description = "이력서에 사용되는 자기소개서을 등록한다.")
    @PostMapping("/insertCoverLetter")
    public String insertCoverLetter(@RequestBody CoverLetterDto coverLetterDto){
        int result = coverLetterService.insertCoverLetter(coverLetterDto);
        System.out.println(result);
        if(result>0){
            return "success";
        }else{
            return "failure";
        }

    }

    @Operation(summary = "이력서 자기소개서 수정", description = "이력서에 사용되는 자기소개서을 수정한다.")
    @PostMapping("/updateCoverLetter")
    public String updateCoverLetter(@RequestBody CoverLetterDto coverLetterDto){
        int result = coverLetterService.updateCoverLetter(coverLetterDto);
        if(result>0){
            return "success";
        }else{
            return "failure";
        }
    }

    @Operation(summary = "이력서 자기소개서 삭제", description = "이력서에 사용되는 자기소개서을 삭제한다.")
    @PostMapping("/deleteCoverLetter")
    public String deleteCoverLetter(@RequestBody CoverLetterDto coverLetterDto){
        int result = coverLetterService.deleteCoverLetter(coverLetterDto);
        System.out.println(result);
        if(result>0){
            return "success";
        }else{
            return "failure";
        }
    }

    @Operation(summary = "이력서 자기소개서 목록 수정", description = "이력서에 사용되는 자기소개서을 수정한다.")
    @PostMapping("/updateCoverLetterList")
    public String updateCoverLetterList(@RequestBody List<Map<String, Object>> updateList){
        System.out.println(updateList);
        int result = coverLetterService.updateCoverLetterList(updateList);
        System.out.println(result);
        if(result>0){
            return "success";
        }else{
            return "failure";
        }
    }
}
