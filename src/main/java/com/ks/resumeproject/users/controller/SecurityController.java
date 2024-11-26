package com.ks.resumeproject.users.controller;

import com.ks.resumeproject.security.domain.AccountContext;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.domain.TokenDto;
import com.ks.resumeproject.security.manager.CustomDynamicAuthorizationManager;
import com.ks.resumeproject.security.util.SecurityUtil;
import com.ks.resumeproject.test.domain.TestDto;
import com.ks.resumeproject.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "security", description = "로그인을 제외한 회원에대한 전반적인 api (로그인은 /api/login을 기준으로 한다.)")
public class SecurityController {

    private final UserService userService;
    private final CustomDynamicAuthorizationManager manager;
    private final SecurityUtil securityUtil;

    @Operation(summary = "계정 일치여부 조회", description = "JWT 형식의 사용자 정보를 가져와 계정 일치여부를 확인한다.")
    @GetMapping("/selectJsonData/{username}")
    public ResponseEntity<Map<String, Boolean>> signIn(@Parameter(description = "사용자명", required = true, example = "admin") @PathVariable("username") String username) {
        if(username.equals("null")){
            return ResponseEntity.ok(Map.of("loginUserEq", Boolean.TRUE));
        }
        Boolean loginUserEq = securityUtil.getAccount().getAccountDto().getUsername().equals(username);

        return ResponseEntity.ok(Map.of("loginUserEq", loginUserEq));

    }

    @Operation(summary = "로그인", description = "JWT 형식의 사용자 정보를 가져옵니다.")
    @PostMapping("/signin")
    public TokenDto signIn(@RequestBody AccountDto accountDto) {
        String username = accountDto.getUsername();
        String password = accountDto.getPassword();
        return userService.signIn(username, password);
    }

    @Operation(summary = "회원가입 아이디 중복검사", description = "ROLE_USER 계정의 사용자의 username의 중복여부를 확인합니다.")
    @PostMapping(value = "/signup/checkUsername")
    public ResponseEntity<Map<String,Boolean>> checkUsername(@Valid @RequestBody AccountDto accountDto) {
        Boolean success = userService.checkUsername(accountDto);

        return ResponseEntity.ok(Map.of("isSuccess", success));
    }

    @Operation(summary = "회원가입", description = "ROLE_USER 계정의 사용자를 등록합니다.")
    @PostMapping(value = "/signup")
    public ResponseEntity<Map<String, String>> signup(@Valid @RequestBody AccountDto accountDto) {
        userService.signUp(accountDto);

        return ResponseEntity.ok(Map.of("result", "signup"));
    }


    @Operation(summary = "로그아웃", description = "JWT 형식의 로그인 정보를 지워 로그아웃합니다.")
    @GetMapping(value = "/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletRequest request, HttpServletResponse response) {

        AccountContext accountContext = securityUtil.getAccount();

        Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return ResponseEntity.ok(Map.of("result", "logout"));
    }

    @Operation(summary = "페이지 접근", description = "페이지 접근 가능여부를 확인한다.")
    @PostMapping(value = "/access")
    public ResponseEntity<Map<String, Boolean>> access(@RequestBody Map map){

        Map result = userService.checkAccessYn(map);

        return ResponseEntity.ok(result);
    }


    @Operation(summary = "사용안함", description = "REST 형식의 로그인 시 csrf 토큰을 가져옵니다.")
    @GetMapping(value = "/csrfToken")
    public CsrfToken csrfToken(HttpServletRequest request, HttpServletResponse response) {
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }

    @Operation(summary = "사용자 검증", description = "사용자 정보를 가져와 해당 유저가 존재하는지 검증합니다.")
    @GetMapping(value = "/selectUser")
    public Authentication selectUser(HttpServletRequest request) {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Operation(summary = "인가 및 권한 재 설정을 위한 get method", description = "get 형식으로 url 호출 시 인가 및 권한을 재 업로드합니다.")
    @GetMapping(value = "/reAuthorization")
    public String selectAuthReMapping(HttpServletRequest request) {
        manager.mapping();

        return "Success ReMapping";
    }

    @GetMapping(value= "/testAdmin")
    public String testAdmin(HttpServletRequest request) {
        return "Admin Test";
    }

    @GetMapping(value= "/testManager")
    public String testManager(HttpServletRequest request) {
        return "Manager Test";
    }

    @GetMapping(value= "/testUser")
    public String testUser(HttpServletRequest request) {
        return "User Test";
    }

}
