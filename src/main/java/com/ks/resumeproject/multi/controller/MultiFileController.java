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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String,BigInteger>> setFile(@ModelAttribute @Valid MultiFormDto form){
        BigInteger fileId = multiFileService.setFile(form);
        return ResponseEntity.ok(Map.of("fileId", fileId));
    }

    @PostMapping(value = "/modifyFile",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "파일수정", description = "파일을 수정한다.")
    public ResponseEntity<Map<String,BigInteger>> modifyFile(@ModelAttribute @Valid MultiFormDto form){
        BigInteger fileId = multiFileService.modifyFile(form);
        return ResponseEntity.ok(Map.of("fileId", fileId));
    }

    @PostMapping(value = "/deleteFile",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "파일삭제", description = "파일을 삭제한다.")
    public ResponseEntity<Map<String,BigInteger>> deleteFile(@ModelAttribute @Valid MultiFormDto form){
        BigInteger fileId =  multiFileService.deleteFile(form);
        return ResponseEntity.ok(Map.of("fileId", fileId));
    }

    @PostMapping(value = "/inputImage",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "이미지 등록", description = "이미지 파일을 등록한다.")
    public ResponseEntity<FileDto> inputImage(@ModelAttribute @Valid MultiFormDto form){
        FileDto fileDto = multiFileService.inputImage(form);
        return ResponseEntity.ok(fileDto);
    }


}
