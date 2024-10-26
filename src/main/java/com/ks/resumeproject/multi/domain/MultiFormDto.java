package com.ks.resumeproject.multi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class MultiFormDto {
    private String type;
    private BigInteger fileId;
    private String fileSubId;
    private int[] fileSubIds;
    private List<MultipartFile> files;
}
