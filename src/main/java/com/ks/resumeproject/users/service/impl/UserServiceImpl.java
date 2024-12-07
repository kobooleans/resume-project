package com.ks.resumeproject.users.service.impl;

import com.ks.resumeproject.error.domain.ErrorCode;
import com.ks.resumeproject.error.exception.CustomException;
import com.ks.resumeproject.security.domain.AccountContext;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.domain.EmailCodeDto;
import com.ks.resumeproject.security.domain.TokenDto;
import com.ks.resumeproject.security.provider.TokenProvider;
import com.ks.resumeproject.security.util.SecurityUtil;
import com.ks.resumeproject.users.domain.AccountMyPageDto;
import com.ks.resumeproject.users.domain.PageDto;
import com.ks.resumeproject.users.repository.UserMapper;
import com.ks.resumeproject.users.service.UserService;
import com.ks.resumeproject.util.ComUtil;
import com.ks.resumeproject.util.MailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final ComUtil comUtil;
    private final MailUtil mailUtil;
    private final SecurityUtil securityUtil;
    private final TemplateEngine templateEngine;

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\." +
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

    @Override
    public boolean checkUsername(AccountDto accountDto) {
        return userMapper.userNameCheck(accountDto).equals("Y");
    }

    @Override
    public boolean checkEmail(AccountDto accountDto) {
        return userMapper.checkEmail(accountDto).equals("Y");
    }


    @Override
    public Map<String,Object> sendEmail(AccountDto accountDto) {
        //해당 메일로 등록된 계정이 있는지 확인한다.
        if(!checkEmail(accountDto)) {
            return Map.of("success", false, "message", "해당 이메일로 등록된 계정이 있습니다.");
        }
        //이메일 형식 확인
        String email = accountDto.getUserEmail();
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()) {
            return Map.of("success", false, "message", "이메일 형식에 맞지 않습니다.");
        }

        String authCode = createRandomCode();
        EmailCodeDto emailCodeDto = new EmailCodeDto();
        emailCodeDto.setUserEmail(email);
        emailCodeDto.setAuthCode(authCode);
        boolean emailCodeChk = userMapper.insertAuthCode(emailCodeDto);
        if(emailCodeChk) {

            Context context = new Context();
            context.setVariable("username", email);
            context.setVariable("authCode", authCode);

            String htmlContent = templateEngine.process("email-template", context);

            //이메일 전송
            mailUtil.sendEmail(email, "CVFit 인증 메일 입니다.", htmlContent);
            return Map.of("success", true, "message", "");
        }else{
            return Map.of("success", false, "message", "이메일 전송에 실패하였습니다.");
        }

    }


    @Override
    public Map updateEmail(AccountDto accountDto) {
        EmailCodeDto emailCodeDto = new EmailCodeDto();
        emailCodeDto.setAuthCode(accountDto.getAuthCode());
        emailCodeDto.setUserEmail(accountDto.getUserEmail());
        Map authCodeChk = userMapper.selectAuthCode(emailCodeDto);
        if(authCodeChk == null || authCodeChk.isEmpty()){
            throw new AuthenticationServiceException("이메일 인증에 실패하였습니다.");
        }
        AccountDto account  = securityUtil.getAccount().getAccountDto();
        accountDto.setUsername(account.getUsername());
        accountDto.setId(account.getId());
        boolean updateEmailChk = userMapper.updateEmail(accountDto);
        if(updateEmailChk) {
            return Map.of("success", true);
        }else{
            return Map.of("success", false);
        }

    }

    private String createRandomCode(){
        int length = 6;

        try {
            SecureRandom random = SecureRandom.getInstanceStrong();
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            StringBuilder builder = new StringBuilder(length);

            for (int i = 0; i < length; i++) {
                builder.append(chars.charAt(random.nextInt(chars.length())));
            }

            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("Error occurred while generating alphanumeric code", e);
            throw new IllegalArgumentException("임시코드 생성에 실패하였습니다.");
        }
    }

    @Override
    public Map checkAccessYn(Map map) {
        AccountDto accountDto = new AccountDto();

        accountDto.setUsername(map.get("username").toString());
        map.put("id",userMapper.getAccountId(accountDto));

        Map resultMap = userMapper.checkAccessYn(map);
        if(resultMap != null){
            return resultMap;
        }

        return Map.of("accessYn",false);
    }

    @Override
    public TokenDto refreshAccessToken(String refreshToken) {
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        Authentication authentication = jwtTokenProvider.getAuthenticationFromRefresh(refreshToken);
        List<AccountMyPageDto> accountMyPageDto = userMapper.pageList(new BigInteger(((AccountContext)authentication.getPrincipal()).getAccountDto().getId().toString()));

        // 새로운 Access Token 발급
        return jwtTokenProvider.generateToken(authentication, accountMyPageDto);
    }

    @Override
    public void signUp(AccountDto accountDto) {
        //이메일 인증
        if(accountDto.getUserEmail() == null || accountDto.getUserEmail().isEmpty()){
            throw new AuthenticationServiceException("email is empty");
        }else{
            EmailCodeDto emailCodeDto = new EmailCodeDto();
            emailCodeDto.setAuthCode(accountDto.getAuthCode());
            emailCodeDto.setUserEmail(accountDto.getUserEmail());
            Map authCodeChk = userMapper.selectAuthCode(emailCodeDto);
            if(authCodeChk == null || authCodeChk.isEmpty()){
                throw new AuthenticationServiceException("이메일 인증에 실패하였습니다.");
            }
        }

        // 패스워드 재검증
        if(accountDto.getPassword() == null || accountDto.getPassword().isEmpty()){
            throw new AuthenticationServiceException("Password is empty");
        }

        // 아이디 재검증
        if(userMapper.userNameCheck(accountDto).equals("Y")){
            accountDto.setRoleType("ROLE_USER");
            accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));

            PageDto pageDto = new PageDto();

            userMapper.signUp(accountDto);

            // 기본 Main 페이지 생성
            pageDto.setUsername(accountDto.getUsername());
            pageDto.setRandomId(comUtil.addRandomVal(10));
            pageDto.setAccountId(userMapper.getAccountId(accountDto));
            pageDto.setPageId(new BigInteger("0"));
            pageDto.setPageDescription("MAIN");
            pageDto.setUseYn("Y");

            userMapper.insertPage(pageDto);

        }else{
            throw new AuthenticationServiceException("Username is duplicated");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public TokenDto signIn(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        if(authentication == null || !passwordEncoder.matches(password, ((AccountContext)authentication.getPrincipal()).getPassword())){
            throw new BadCredentialsException("Invalid password");
        }

        List<AccountMyPageDto> accountMyPageDto = userMapper.pageList(new BigInteger(((AccountContext)authentication.getPrincipal()).getAccountDto().getId().toString()));

        return jwtTokenProvider.generateToken(authentication, accountMyPageDto);
    }



}
