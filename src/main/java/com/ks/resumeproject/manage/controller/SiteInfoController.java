package com.ks.resumeproject.manage.controller;

import com.ks.resumeproject.manage.domain.SiteInfoDto;
import com.ks.resumeproject.manage.service.SiteInfoService;
import com.ks.resumeproject.users.domain.PageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/siteInfo")
@RequiredArgsConstructor
@Tag(name = "SiteInfo", description = "하단 사이트 관리")
public class SiteInfoController {

    private final SiteInfoService siteInfoService;


    @Operation(summary = "하단 사이트 조회", description = "하단 사이트를 조회한다.")
    @GetMapping("/select")
    public ResponseEntity<Map<String, List<SiteInfoDto>>> select(){
        List<SiteInfoDto> siteInfoDtos = siteInfoService.getFooterSiteList();

        return ResponseEntity.ok(Map.of("result", siteInfoDtos));
    }

    @Operation(summary = "하단 사이트 등록 조회", description = "하단 사이트 등록 시 페이지 목록을 조회한다.")
    @GetMapping("/selectPage")
    public ResponseEntity<Map<String, List<PageDto>>> selectPage(){
        List<PageDto> pageDtos = siteInfoService.selectPage();

        return ResponseEntity.ok(Map.of("result", pageDtos));
    }


    @Operation(summary = "하단 사이트 등록", description = "하단 사이트를 등록한다.")
    @PostMapping("/insert")
    public ResponseEntity<Map> insert(@RequestBody SiteInfoDto siteInfoDto){
        siteInfoService.insertFooterSite(siteInfoDto);

        return ResponseEntity.ok(Map.of("result","success"));
    }

    @Operation(summary = "하단 사이트 삭제", description = "하단 사이트를 삭제한다.")
    @PostMapping("/delete")
    public ResponseEntity<Map> delete(@RequestBody List<SiteInfoDto> siteInfoDto){
        siteInfoService.deleteFooterSite(siteInfoDto);

        return ResponseEntity.ok(Map.of("result","success"));
    }

    @Operation(summary = "하단 사이트 수정", description = "하단 사이트를 수정한다.")
    @PostMapping("/update")
    public ResponseEntity<Map> update(@RequestBody SiteInfoDto siteInfoDto){
        siteInfoService.updateFooterSite(siteInfoDto);

        return ResponseEntity.ok(Map.of("result","success"));
    }
}
