package com.ks.resumeproject.resume.controller;


import com.ks.resumeproject.resume.domain.AwardDto;
import com.ks.resumeproject.resume.service.AwardService;
import com.ks.resumeproject.security.domain.AccountDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/award")
@Tag(name = "award", description = "자격/어학/수상 등록, 수정, 삭제를 위한 Controller")
public class AwardController {

    private final AwardService awardService;

    @Operation(summary = "자격/어학/수상 목록을 가져온다.", description = "이력서에 사용되는 자격/어학/수상 목록을 조회한다.")
    @PostMapping("/getAwardList")
    public Map<String, Object> getAwardList(@RequestBody AccountDto accountDto){
        return awardService.getAwardList(accountDto);
    }

    @Operation(summary = "이력서 자격/어학/수상 등록", description = "이력서에 사용되는 자격/어학/수상을 등록한다.")
    @PostMapping("/insertAward")
    public ResponseEntity<Map> insertAward(@RequestBody AwardDto awardDto){
        int result = awardService.insertAward(awardDto);
        //System.out.println(result);
        if(result>0){
            return ResponseEntity.ok(Map.of("result","success"));
        }else{
            return ResponseEntity.ok(Map.of("result","failure"));
        }

    }

    @Operation(summary = "이력서 자격/어학/수상 수정", description = "이력서에 사용되는 자격/어학/수상을 수정한다.")
    @PostMapping("/updateAward")
    public ResponseEntity<Map> updateAward(@RequestBody AwardDto awardDto){
        int result = awardService.updateAward(awardDto);
        if(result>0){
            return ResponseEntity.ok(Map.of("result","success"));
        }else{
            return ResponseEntity.ok(Map.of("result","failure"));
        }
    }

    @Operation(summary = "이력서 자격/어학/수상 삭제", description = "이력서에 사용되는 자격/어학/수상을 삭제한다.")
    @PostMapping("/deleteAward")
    public ResponseEntity<Map> deleteAward(@RequestBody AwardDto awardDto){
        int result = awardService.deleteAward(awardDto);
        //System.out.println(result);
        if(result>0){
            return ResponseEntity.ok(Map.of("result","success"));
        }else{
            return ResponseEntity.ok(Map.of("result","failure"));
        }
    }

    @Operation(summary = "이력서 자격/어학/수상 목록 수정", description = "이력서에 사용되는 자격/어학/수상을 수정한다.")
    @PostMapping("/updateAwardList")
    public ResponseEntity<Map> updateAwardList(@RequestBody List<Map<String, Object>> updateList){
        //System.out.println(updateList);
        int result = awardService.updateAwardList(updateList);
        //System.out.println(result);
        if(result>0){
            return ResponseEntity.ok(Map.of("result","success"));
        }else{
            return ResponseEntity.ok(Map.of("result","failure"));
        }
    }
}
