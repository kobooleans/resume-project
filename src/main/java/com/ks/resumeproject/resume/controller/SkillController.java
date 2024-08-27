package com.ks.resumeproject.resume.controller;

import com.ks.resumeproject.resume.domain.ResumeDto;
import com.ks.resumeproject.resume.domain.SkillDto;
import com.ks.resumeproject.resume.domain.SkillSetDto;
import com.ks.resumeproject.resume.service.SkillService;
import com.ks.resumeproject.security.domain.AccountDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/skill")
@Tag(name = "skill", description = "이력서 스킬 Controller")
public class SkillController {

    private final SkillService skillService;

    @Operation(summary = "이력서 스킬 목록 조회", description = "이력서에 사용되는 스킬 목록을 조회한다.")
    @PostMapping("/getSkillSet")
    public ResponseEntity<Map<String, List<SkillSetDto>>> getSkillSet(@RequestBody AccountDto accountDto){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("skills", skillService.getSkillSet(accountDto)));
    }

    @Operation(summary = "이력서 스킬 목록 저장", description = "이력서에 사용되는 스킬 목록을 저장한다.")
    @PostMapping("/insertSkillSet")
    public ResponseEntity<Map<String, String>> insertSkillSet(@RequestBody SkillSetDto skillDtos){
        skillService.insertSkillSet(skillDtos);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("result","success"));
    }

}
