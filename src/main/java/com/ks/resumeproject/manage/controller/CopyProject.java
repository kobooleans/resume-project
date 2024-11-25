package com.ks.resumeproject.manage.controller;

import com.ks.resumeproject.manage.domain.ProjectDto;
import com.ks.resumeproject.manage.service.CopyProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/copyProject")
@Tag(name = "multi", description = "파일 관리를 위한 Controller")
public class CopyProject {

    private final CopyProjectService copyProjectService;

    @GetMapping(value = "/select")
    @Operation(summary = "페이지 조회", description = "페이지를 조회한다.")
    public ResponseEntity<List<ProjectDto>> selectProjectList(){
        List<ProjectDto> projectList = copyProjectService.selectProjectList();
        return ResponseEntity.ok(projectList);
    }

    @PostMapping(value = "/copy")
    @Operation(summary = "페이지 복제", description = "페이지를 복제한다.")
    public ResponseEntity<Map<String,String>> copyProject(@RequestBody List<ProjectDto> projectDtos){

        copyProjectService.copyProject(projectDtos);

        return ResponseEntity.ok(Map.of("result","success"));
    }

    @PostMapping(value = "/save")
    @Operation(summary = "페이지 내용 저장", description = "페이지 내용을 저장한다.")
    public ResponseEntity<Map<String,String>> saveProject(@RequestBody List<ProjectDto> projectDto){

        copyProjectService.saveProject(projectDto);

        return ResponseEntity.ok(Map.of("result","success"));
    }

    @PostMapping(value = "/delete")
    @Operation(summary = "페이지 내용 저장", description = "페이지 내용을 저장한다.")
    public ResponseEntity<Map<String,String>> deleteProject(@RequestBody List<ProjectDto> projectDto){

        copyProjectService.deleteProject(projectDto);

        return ResponseEntity.ok(Map.of("result","success"));
    }

}
