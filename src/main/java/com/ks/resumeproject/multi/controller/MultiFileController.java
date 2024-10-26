package com.ks.resumeproject.multi.controller;

import com.ks.resumeproject.multi.domain.FileDto;
import com.ks.resumeproject.multi.domain.MultiFormDto;
import com.ks.resumeproject.multi.service.MultiFileService;
import com.ks.resumeproject.portfolio.domain.CategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/multi")
@Tag(name = "multi", description = "파일 관리를 위한 Controller")
public class MultiFileController {

    private final MultiFileService multiFileService;


    @PostMapping(value = "/form",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "파일업로드", description = "파일을 업로드한다.")
    public BigInteger setFile(@ModelAttribute @Valid MultiFormDto form){
        return multiFileService.setFile(form);
    }

    @PostMapping(value = "/modifyFile",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "파일수정", description = "파일을 수정한다.")
    public BigInteger modifyFile(@ModelAttribute @Valid MultiFormDto form){
        return multiFileService.modifyFile(form);
    }

    @PostMapping(value = "/deleteFile",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "파일삭제", description = "파일을 삭제한다.")
    public BigInteger deleteFile(@ModelAttribute @Valid MultiFormDto form){
        return multiFileService.deleteFile(form);
    }
}
