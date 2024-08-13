package com.ks.resumeproject.profile.controller;

import com.ks.resumeproject.multi.domain.FileDto;
import com.ks.resumeproject.profile.service.ProfileService;
import com.ks.resumeproject.security.domain.AccountDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @Operation(summary = "프로필 조회", description = "프로필 조회")
    @PostMapping("/getProfile")
    public List<FileDto> getProfile(@RequestBody AccountDto accountDto){
        return profileService.getProfile(accountDto);
    }

    @Operation(summary = "프로필 저장", description = "프로필 저장")
    @PostMapping("/setProfile")
    public void setProfile(@RequestBody AccountDto accountDto){
        profileService.setProfile(accountDto);
    }

}
