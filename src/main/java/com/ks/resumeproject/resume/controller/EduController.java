package com.ks.resumeproject.resume.controller;

import com.ks.resumeproject.resume.domain.EduDto;
import com.ks.resumeproject.resume.service.EduService;
import com.ks.resumeproject.security.domain.AccountDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/edu")
@Tag(name = "edu", description = "학력 등록, 수정, 삭제를 위한 Controller")
public class EduController {

    private final EduService eduService;

    @Operation(summary = "학력 조회", description = "학력 정보를 가지고 온다.")
    @PostMapping("/selectEdu")
    public List<EduDto> selectEdu(@RequestBody AccountDto accountDto){
        return eduService.selectEdu(accountDto);
    }
    @Operation(summary = "학력 등록", description = "학력 정보를 등록한다.")
    @PostMapping("/insertEdu")
    public String insertEdu(@RequestBody EduDto eduDto){
        eduService.insertEdu(eduDto);

        return "success";
    }

    @Operation(summary = "학력 수정", description = "학력 정보를 수정한다.")
    @PostMapping("/updateEdu")
    public String updateEdu(@RequestBody EduDto eduDto){
        eduService.updateEdu(eduDto);

        return "success";
    }

    @Operation(summary = "학력 삭제", description = "학력 정보를 삭제한다.")
    @PostMapping("/deleteEdu")
    public String deleteEdu(@RequestBody EduDto eduDto){
        eduService.deleteEdu(eduDto);

        return "success";
    }


}
