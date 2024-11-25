package com.ks.resumeproject.conf;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@OpenAPIDefinition(
        info = @Info(title = "Spring Boot Swagger Application",
                description = "Spring Boot로 개발하는 RESTful API 명세서 입니다.",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
@Profile({"local", "dev"}) // local 또는 dev 환경에서만 활성화
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi chatOpenApi() {
        String[] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("Spring Boot Swagger Application")
                .pathsToMatch(paths)
                .build();
    }
}
