package com.ks.resumeproject.home.controller;

import com.ks.resumeproject.home.service.HomeService;
import com.ks.resumeproject.home.domain.TitleDto;
import com.ks.resumeproject.manage.domain.SiteInfoDto;
import com.ks.resumeproject.security.domain.AccountDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
@Tag(name = "home", description = "타이틀 정보를 관리하기 위한 Controller")
public class HomeController {

    private final HomeService homeService;

    @Operation(summary = "타이틀 조회", description = "타이틀 정보를 가지고 온다.")
    @PostMapping("/selectTitle")
    public ResponseEntity<Map> selectTitle(@RequestBody AccountDto accountDto){
        Map map = new HashMap();

        TitleDto titleDto = homeService.selectTitle(accountDto);
        map.put("title",titleDto);

        List<SiteInfoDto> info = homeService.selectSiteInfo(accountDto);
        map.put("siteInfo",info);

        return ResponseEntity.ok(Map.of("result",map));
    }

    @Operation(summary = "타이틀 수정", description = "타이틀 정보를 수정한다.")
    @PostMapping("/updateTitle")
    public ResponseEntity<Map> updateTitle(@RequestBody TitleDto titleDto){
        homeService.updateTitle(titleDto);
        return ResponseEntity.ok(Map.of("result","success"));
    }
}
