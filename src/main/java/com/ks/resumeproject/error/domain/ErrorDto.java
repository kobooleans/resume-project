package com.ks.resumeproject.error.domain;

import lombok.Data;

@Data
public class ErrorDto {
    private String code;
    private String message;
    private String name;


    public ErrorDto(String code) {
        this.code = code;
    }

    public ErrorDto(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorDto(String code, String message, String name) {
        this.code = code;
        this.message = message;
        this.name = name;
    }
}
