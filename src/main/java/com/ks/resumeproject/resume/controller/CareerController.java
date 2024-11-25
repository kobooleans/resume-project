package com.ks.resumeproject.resume.controller;

import com.ks.resumeproject.resume.domain.CareerDto;
import com.ks.resumeproject.resume.service.CareerService;
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
@RequestMapping("/api/career")
@Tag(name = "career", description = "경력 등록, 수정, 삭제를 위한 Controller")
public class CareerController {

    private final CareerService careerService;

    @Operation(summary = "이력서 경력 목록을 가져온다.", description = "이력서에 사용되는 경력 목록을 조회한다.")
    @PostMapping("/getCareerList")
    public List<CareerDto> getCareerList(@RequestBody AccountDto accountDto){
        return careerService.getCareerList(accountDto);
    }

    @Operation(summary = "이력서 경력 등록", description = "이력서에 사용되는 경럭을 등록한다.")
    @PostMapping("/insertCareer")
    public ResponseEntity<Map> insertCareer(@RequestBody CareerDto careerDto){
        careerService.insertCareer(careerDto);

        return ResponseEntity.ok(Map.of("result","success"));
    }

    @Operation(summary = "이력서 경력 수정", description = "이력서에 사용되는 경럭을 수정한다.")
    @PostMapping("/updateCareer")
    public ResponseEntity<Map> updateCareer(@RequestBody CareerDto careerDto){
        careerService.updateCareer(careerDto);

        return ResponseEntity.ok(Map.of("result","success"));
    }

    @Operation(summary = "이력서 경력 삭제", description = "이력서에 사용되는 경럭을 삭제한다.")
    @PostMapping("/deleteCareer")
    public ResponseEntity<Map> deleteCareer(@RequestBody CareerDto careerDto){
        careerService.deleteCareer(careerDto);

        return ResponseEntity.ok(Map.of("result","success"));
    }
}
