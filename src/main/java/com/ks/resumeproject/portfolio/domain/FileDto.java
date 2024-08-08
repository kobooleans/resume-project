package com.ks.resumeproject.portfolio.domain;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Blob;

@Data
public class FileDto {
    private BigInteger fileId;
    private String fileNm;
    private Blob file;
    private String fileType;
    private String useYn;
}
