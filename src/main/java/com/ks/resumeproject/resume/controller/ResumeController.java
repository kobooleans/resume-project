package com.ks.resumeproject.resume.controller;

import com.ks.resumeproject.portfolio.domain.CategoryDto;
import com.ks.resumeproject.resume.domain.ResumeDto;
import com.ks.resumeproject.resume.service.ResumeService;
import com.ks.resumeproject.security.domain.AccountDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resume")
@Tag(name = "resume", description = "이력서 기본 Controller")
public class ResumeController {

    private final ResumeService resumeService;

    @Operation(summary = "이력서 사용목록 조회", description = "이력서에 사용되는 목록을 조회한다.")
    @PostMapping("/getResumeSet")
    public ResumeDto getResumeSet(@RequestBody AccountDto accountDto){
        return resumeService.getResumeSet(accountDto);
    }

    @Operation(summary = "이력서 사용목록 추가", description = "이력서에 사용되는 목록을 추가한다.")
    @PostMapping("/setResumeItem")
    public ResponseEntity<Map<String, String>> setResumeItem(@RequestBody ResumeDto resumeDto){
        resumeService.setResumeItem(resumeDto);

        return ResponseEntity.ok(Map.of("result","success"));
    }

}
