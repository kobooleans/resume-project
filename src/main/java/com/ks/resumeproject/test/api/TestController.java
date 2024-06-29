package com.ks.resumeproject.test.api;

import com.ks.resumeproject.resolver.AcceptHeaderResolver;
import com.ks.resumeproject.test.domain.TestDTO;
import com.ks.resumeproject.test.service.TestService;
import com.ks.resumeproject.test.domain.TestVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Map;

@RestController
@Tag(name = "test-controller", description = "Swagger 테스트용 API 컨트롤러")
@RequestMapping("/test")
public class TestController {

    private final TestService testService;
    private final MessageSource messageSource;
    private final AcceptHeaderResolver resolver;

    @Autowired
    public TestController(TestService testService, MessageSource messageSource) {
        this.testService = testService;
        this.messageSource = messageSource;
        this.resolver = new AcceptHeaderResolver();
    }

    /**
     * @Valid를 이용하면, service 단이 아닌 객체 안에서, 들어오는 값에 대해 검증을 할 수 있다.
     *
     * @return
     */
    @Operation(summary = "POST-TEST", description = "DTO 값을 가져와 그대로 출력하는 테스트입니다.")
    @PostMapping("")
    public TestDTO testResult(@Valid @RequestBody TestDTO testDTO){
        return testDTO;
    }

    @Operation(summary = "GET-TEST", description = "테스트 페이지를 호출합니다. {name} 파라미터를 추가하여 해당 이름 파라미터를 가져옵니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = TestDTO.class)))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!"),
    })
    @GetMapping("/{name}")
    public TestDTO test(@Parameter(description = "사용자명", required = true, example = "KS") @PathVariable("name") String name
                        , HttpServletRequest request){
        TestVO repository = testService.selectTest();
        TestDTO param = new TestDTO();

        // 국제화 테스트
        Locale locale = resolver.resolveLocale(request);
        String message = messageSource.getMessage("greeting.message", null, locale);

        param.setName(name == null ? "TEST" : name);
        param.setServerName(repository == null ? "RESUME PROJECT WITH LOCAL-NOSQL SERVER" : message + " " + repository.getTestName());

        return param;
    }

}
