package com.ks.resumeproject.users.controller;

import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.domain.TokenDto;
import com.ks.resumeproject.security.manager.CustomDynamicAuthorizationManager;
import com.ks.resumeproject.security.util.CookieUtil;
import com.ks.resumeproject.security.util.SecurityUtil;
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

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "security", description = "로그인을 제외한 회원에대한 전반적인 api (로그인은 /api/login을 기준으로 한다.)")
public class SecurityController {

    private final UserService userService;
    private final CustomDynamicAuthorizationManager manager;
    private final SecurityUtil securityUtil;
    private final CookieUtil cookieUtil;

    private final int EXPIRES_IN = 900; // 토큰사용 가능시간
    private final int R_EXPIRES_IN = 3600; // 리프레시 토큰 가능시간 1시간


    @GetMapping("/dummy")
    public ResponseEntity<Map<String,Object>> dummy(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(Map.of("result", "dummy"));
    }

    @Operation(summary = "계정 일치여부 조회", description = "JWT 형식의 사용자 정보를 가져와 계정 일치여부를 확인한다.")
    @GetMapping("/selectJsonData/{username}/{paramsName}")
    public ResponseEntity<Map<String, Boolean>> signIn(
            @Parameter(description = "사용자명", required = true, example = "admin") @PathVariable("username") String username,
            @Parameter(description = "파라미터 사용자명", required = true, example = "admin") @PathVariable("paramsName") String paramsName) {

        Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();

        if (authentication.getPrincipal().equals("anonymousUser")) {
            return ResponseEntity.ok(Map.of("loginUserEq", Boolean.TRUE));
        }

        Boolean loginUserEq = securityUtil.getAccount().getAccountDto().getUsername().equals(username) && username.equals(paramsName);

        return ResponseEntity.ok(Map.of("loginUserEq", loginUserEq));

    }

    @GetMapping("/isLogin")
    @Operation(summary = "로그인 여부 확인", description = "로그인 여부를 확인한다.")
    public ResponseEntity<Map<String,Object>> isLogin(HttpServletRequest request, HttpServletResponse response) {

        String accessToken = cookieUtil.getCookie(request, "token");
        String refreshToken = cookieUtil.getCookie(request, "refreshed");

        if(refreshToken == null && accessToken == null){
            return ResponseEntity.ok(Map.of("isLogin", Boolean.FALSE));
        }

        TokenDto tokenDto = userService.refreshAccessToken(refreshToken);

        if(accessToken == null){
            cookieUtil.addCookie(response, "token", tokenDto.getAccessToken(), EXPIRES_IN);
        }

        cookieUtil.addCookie(response, "refreshed", refreshToken, R_EXPIRES_IN);

        return ResponseEntity.ok(Map.of("isLogin", Boolean.TRUE, "result", tokenDto));
    }

    @GetMapping("/refresh")
    @Operation(summary = "토큰 재생성", description = "시간 초과 시 토큰을 재생성한다.")
    public ResponseEntity<Map<String,Boolean>> refresh(HttpServletRequest request, HttpServletResponse response) {

        String refreshToken = cookieUtil.getCookie(request, "refreshed");

        TokenDto tokenDto = userService.refreshAccessToken(refreshToken);
        cookieUtil.addCookie(response, "token", tokenDto.getAccessToken(), EXPIRES_IN);
        cookieUtil.addCookie(response, "token", tokenDto.getRefreshToken(), R_EXPIRES_IN);

        return ResponseEntity.ok(Map.of("isLogin", Boolean.TRUE));
    }

    @Operation(summary = "로그인", description = "JWT 형식의 사용자 정보를 가져옵니다.")
    @PostMapping("/signin")
    public TokenDto signIn(@RequestBody AccountDto accountDto, HttpServletResponse response) {
        String username = accountDto.getUsername();
        String password = accountDto.getPassword();

        TokenDto tokenDto = userService.signIn(username, password);

        cookieUtil.addCookie(response, "token", tokenDto.getAccessToken(), EXPIRES_IN);
        cookieUtil.addCookie(response, "refreshed", tokenDto.getRefreshToken(), R_EXPIRES_IN); // 하루

        return tokenDto;
    }

    @Operation(summary = "회원가입 아이디 중복검사", description = "ROLE_USER 계정의 사용자의 username의 중복여부를 확인합니다.")
    @PostMapping(value = "/signup/checkUsername")
    public ResponseEntity<Map<String,Boolean>> checkUsername(@Valid @RequestBody AccountDto accountDto) {
        Boolean success = userService.checkUsername(accountDto);

        return ResponseEntity.ok(Map.of("isSuccess", success));
    }

    @Operation(summary = "이메일 인증 코드 전송", description = "ROLE_USER 계정의 사용자의 username의 중복여부를 확인합니다.")
    @PostMapping(value = "/signup/sendEmail")
    public ResponseEntity<Map<String,Object>> sendEmail(@Valid @RequestBody AccountDto accountDto) {
        Map<String,Object> success = userService.sendEmail(accountDto);

        return ResponseEntity.ok(Map.of("isSuccess", success.get("success"), "message", success.get("message")));
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

        Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        cookieUtil.removeCookie(response, "token");
        cookieUtil.removeCookie(response, "refreshed");

        return ResponseEntity.ok(Map.of("result", "logout"));
    }

    @Operation(summary = "페이지 접근", description = "페이지 접근 가능여부를 확인한다.")
    @PostMapping(value = "/access")
    public ResponseEntity<Map<String, Map>> access(@RequestBody Map map){

        Map result = userService.checkAccessYn(map);

        return ResponseEntity.ok(Map.of("result",result));
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

}
