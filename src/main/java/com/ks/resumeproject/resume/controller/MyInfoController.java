package com.ks.resumeproject.resume.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/myinfo")
@Tag(name = "myinfo", description = "개인 정보 목록을 위한 api")
public class MyInfoController {
}
