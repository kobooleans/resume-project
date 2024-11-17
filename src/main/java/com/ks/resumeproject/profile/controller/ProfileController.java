package com.ks.resumeproject.profile.controller;

import com.ks.resumeproject.multi.domain.FileDto;
import com.ks.resumeproject.profile.service.ProfileService;
import com.ks.resumeproject.security.domain.AccountDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
@Tag(name = "profile", description = "프로필 이미지 관리 관련 api")
public class ProfileController {

    private final ProfileService profileService;

    @Operation(summary = "프로필 조회", description = "프로필 조회")
    @PostMapping("/getProfile")
    public List<FileDto> getProfile(@RequestBody AccountDto accountDto){
        return profileService.getProfile(accountDto);
    }

    @Operation(summary = "프로필 저장", description = "프로필 저장")
    @PostMapping("/setProfile")
    public ResponseEntity<Map<String, String>> setProfile(@RequestBody AccountDto accountDto){
        profileService.setProfile(accountDto);
        return ResponseEntity.ok(Map.of("result","success"));
    }

}
