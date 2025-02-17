package com.ks.resumeproject.viewer.controller;

import com.ks.resumeproject.error.domain.ErrorCode;
import com.ks.resumeproject.error.exception.CustomException;
import com.ks.resumeproject.manage.domain.ProjectDto;
import com.ks.resumeproject.resume.service.ResumeService;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.util.CookieUtil;
import com.ks.resumeproject.security.util.SecurityUtil;
import com.ks.resumeproject.users.service.SecurityService;
import com.ks.resumeproject.viewer.domain.Viewer;
import com.ks.resumeproject.viewer.service.ViewerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/viewer")
@Tag(name = "viewer", description = "페이지 뷰어를 위한 Controller")
public class ViewerController {

    // 이력서 조회 관련 Service
    private final ResumeService resumeService;

    private final ViewerService viewerService;


    @GetMapping(value = "/select/{username}")
    @Operation(summary = "메인 페이지 조회 - ", description = "설정에서 정의한 메인 페이지를 조회한다.")
    public ResponseEntity<Viewer> selectProjectList(
            @Parameter(description = "사용자명", required = true, example = "admin") @PathVariable("username") String username
    ){

        AccountDto accountDto = new AccountDto();
        accountDto.setUsername(username);

        // randomId 조회
        String randomId = viewerService.selectRandomId(accountDto);

        if(randomId == null){
            throw new CustomException(ErrorCode.NOT_FOUND_VIEWER);
        }

        accountDto.setRandomId(randomId);

        Viewer viewer = viewerService.cvProjectViewerList(accountDto);

        return ResponseEntity.ok(viewer);
    }

    @GetMapping(value = "/select/{username}/{randomId}")
    @Operation(summary = "페이지 조회 - ", description = "페이지를 조회한다.")
    public ResponseEntity<Viewer> selectProjectList(
            @Parameter(description = "사용자명", required = true, example = "admin") @PathVariable("username") String username,
            @Parameter(description = "페이지ID", required = true, example = "test") @PathVariable("randomId") String randomId
    ){

        AccountDto accountDto = new AccountDto();
        accountDto.setUsername(username);
        accountDto.setRandomId(randomId);

        Viewer viewer = viewerService.cvProjectViewerList(accountDto);

        return ResponseEntity.ok(viewer);
    }

}
