package com.ks.resumeproject.multi.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class FileDto {
    private BigInteger fileId;
    private int fileSubId;
    private String fileNm;
    private byte[] file;
    private String fileType;
    private String useYn;
    private String bucketKey;
}
