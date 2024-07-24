package com.ks.resumeproject.users.controller;

import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.domain.TokenDto;
import com.ks.resumeproject.test.domain.TestDto;
import com.ks.resumeproject.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "security", description = "로그인을 제외한 회원에대한 전반적인 api (로그인은 /api/login을 기준으로 한다.)")
public class SecurityController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "JWT 형식의 사용자 정보를 가져옵니다.")
    @PostMapping("/signin")
    public TokenDto signIn(@RequestBody AccountDto accountDto) {
        String username = accountDto.getUsername();
        String password = accountDto.getPassword();
        return userService.signIn(username, password);
    }

    @Operation(summary = "회원가입", description = "ROLE_USER 계정의 사용자를 등록합니다.")
    @PostMapping(value = "/signup")
    public String signup(@Valid @RequestBody AccountDto accountDto) {
        userService.signUp(accountDto);
        return "signup";
    }


    @Operation(summary = "로그아웃", description = "JWT 형식의 로그인 정보를 지워 로그아웃합니다.")
    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "logout";
    }

    @Operation(summary = "사용안함", description = "REST 형식의 로그인 시 csrf 토큰을 가져옵니다.")
    @GetMapping(value = "/csrf-token")
    public CsrfToken csrfToken(HttpServletRequest request, HttpServletResponse response) {
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }

    @Operation(summary = "사용자 검증", description = "사용자 정보를 가져와 해당 유저가 존재하는지 검증합니다.")
    @GetMapping(value = "/selectUser")
    public Authentication selectUser(HttpServletRequest request) {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
