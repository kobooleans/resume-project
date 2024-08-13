package com.ks.resumeproject.multi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class MultiFormDto {
    private String type;
    private List<MultipartFile> files;
}
