package com.ks.resumeproject.resume.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CoverLetterDto {

    private BigInteger id;
    private String randomId;
    private BigInteger coverLetterId;
    private String title;
    private String content;
    private BigInteger seq;
}
