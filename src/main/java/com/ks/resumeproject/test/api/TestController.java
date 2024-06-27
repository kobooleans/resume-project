package com.ks.resumeproject.test.api;

import com.ks.resumeproject.test.domain.TestDTO;
import com.ks.resumeproject.test.service.TestService;
import com.ks.resumeproject.test.domain.TestVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Tag(name = "예제 API", description = "Swagger 테스트용 API")
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @Operation(summary = "테스트 페이지 호출", description = "테스트 페이지를 호출합니다. {name} 파라미터를 추가하면 해당 이름이 출력됩니다.\nhttp://localhost:8080/test?name=CHO")
    @Parameter(name = "map", description = "TestMap 파라미터")
    @GetMapping("/test")
    public String test(@RequestParam Map<String, String> map){
        TestVO repository = testService.selectTest();
        TestDTO param = new TestDTO();

        param.setName(map.isEmpty() ? "TEST" : map.get("name"));
        param.setServerName(repository == null ? "RESUME PROJECT WITH LOCAL-NOSQL SERVER" : repository.getTestName());
        return "Hello World! "+ param.getServerName() + " Developer : " + param.getName();
    }

}
