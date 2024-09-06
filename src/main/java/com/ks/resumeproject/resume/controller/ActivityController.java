package com.ks.resumeproject.resume.controller;

import com.ks.resumeproject.resume.domain.ActivityDto;
import com.ks.resumeproject.resume.domain.AwardDto;
import com.ks.resumeproject.resume.domain.CareerDto;
import com.ks.resumeproject.resume.service.ActivityService;
import com.ks.resumeproject.security.domain.AccountDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/activity")
@Tag(name = "career", description = "경험/활동/교육 등록, 수정, 삭제를 위한 Controller")
public class ActivityController {

    private final ActivityService activityService;

    @Operation(summary = "이력서 경험/활동/교육 목록을 가져온다.", description = "이력서에 사용되는 경험/활동/교육 목록을 조회한다.")
    @GetMapping("/getActivityList/{username}/{randomId}")
    public ResponseEntity<List<ActivityDto>> getActivityList(
            @Parameter(description = "사용자명", required = true, example = "admin") @PathVariable("username") String username
          , @Parameter(description = "페이지ID", required = true, example = "vhHk36eMvx") @PathVariable("randomId") String randomId){


        List<ActivityDto> list = activityService.getActivityList(username, randomId);


        return ResponseEntity.ok(list);
    }


    @Operation(summary = "이력서 경험/활동/교육을 등록한다.", description = "이력서에 사용되는 경험/활동/교육을 등록한다.")
    @PutMapping("/insertActivity")
    public ResponseEntity<ActivityDto> insertActivity(@RequestBody ActivityDto activityDto){

        activityService.insertActivity(activityDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(activityDto);
    }

    @Operation(summary = "이력서 경험/활동/교육을 수정한다.", description = "이력서에 사용되는 경험/활동/교육을 수정한다.")
    @PutMapping("/updateActivity")
    public ResponseEntity<ActivityDto> updateActivity(@RequestBody ActivityDto activityDto){

        activityService.updateActivity(activityDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(activityDto);
    }

    @Operation(summary = "이력서 경험/활동/교육을 삭제한다.", description = "이력서에 사용되는 경험/활동/교육을 삭제한다.")
    @PostMapping("/deleteActivity")
    public ResponseEntity<ActivityDto> deleteActivity(@RequestBody ActivityDto activityDto){

        activityService.deleteActivity(activityDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(activityDto);
    }

}
