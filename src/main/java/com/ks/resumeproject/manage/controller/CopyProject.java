package com.ks.resumeproject.manage.controller;

import com.ks.resumeproject.manage.domain.ProjectDto;
import com.ks.resumeproject.manage.service.CopyProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/copyProject")
@Tag(name = "multi", description = "파일 관리를 위한 Controller")
public class CopyProject {

    private final CopyProjectService copyProjectService;

    @GetMapping(value = "/selectProjectList")
    @Operation(summary = "파일수정", description = "파일을 수정한다.")
    public ResponseEntity<List<ProjectDto>> selectProjectList(){
        List<ProjectDto> projectList = copyProjectService.selectProjectList();
        return ResponseEntity.ok(projectList);
    }


}
