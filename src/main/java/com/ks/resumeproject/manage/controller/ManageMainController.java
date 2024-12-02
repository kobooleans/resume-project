package com.ks.resumeproject.manage.controller;

import com.ks.resumeproject.manage.domain.ProjectDto;
import com.ks.resumeproject.manage.service.ManageMainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/manageMain")
@RequiredArgsConstructor
@Tag(name = "manageMain", description = "설정메인 Controller")
public class ManageMainController {

    private final ManageMainService manageMainService;

    @PostMapping(value = "/projectCalList")
    @Operation(summary = "프로젝트 일정 리스트", description = "프로젝트 일정 리스트를 조회한다.")
    public ResponseEntity<List<ProjectDto>> selectProjectCalList(@RequestBody ProjectDto projectDto){
        List<ProjectDto> projectList = manageMainService.selectProjectCalList(projectDto);
        return ResponseEntity.ok(projectList);
    }

    @GetMapping(value = "/getManageInfo")
    @Operation(summary = "정보 조회", description = "사용자의 페이지, 이력서 및 포트폴리오 정보 조회")
    public ResponseEntity<Map> selectManageInfo(){
        return ResponseEntity.ok(Map.of("result",manageMainService.selectManageInfo()));
    }
}
