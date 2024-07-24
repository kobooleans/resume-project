package com.ks.resumeproject.conf;

import com.ks.resumeproject.security.dsl.RestApiDsl;
import com.ks.resumeproject.security.entrypoint.RestAuthenticationEntryPoint;
import com.ks.resumeproject.security.filter.JwtAuthenticationFilter;
import com.ks.resumeproject.security.handler.RestAccessDeniedHandler;
import com.ks.resumeproject.security.handler.RestAuthenticationFailureHandler;
import com.ks.resumeproject.security.handler.RestAuthenticationSuccessHandler;
import com.ks.resumeproject.security.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    /*private final AuthenticationProvider restAuthenticationProvider;
    private final RestAuthenticationSuccessHandler restSuccessHandler;
    private final RestAuthenticationFailureHandler restFailureHandler;*/

    private final TokenProvider tokenProvider;

    @Bean
    public SecurityFilterChain restSecurityFilterChain(HttpSecurity http) throws Exception {

        /** jwt 방식으로의 변경으로 인한 해당부분 주석처리 */
        /*AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(restAuthenticationProvider);
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();   */         // build() 는 최초 한번 만 호출해야 한다

                /** jwt 방식으로의 변경으로 인한 해당부분 주석처리 */
                //.securityMatcher("/api/**")
                //.authorizeHttpRequests(auth -> auth
                //        .requestMatchers("/css/**", "/images/**", "/js/**", "/favicon.*", "/*/icon-*").permitAll()
                //        .requestMatchers("/api","/api/login","/api/signup", "api/logout", "api/csrf-token","api/selectUser").permitAll()
                //        .requestMatchers("/api/user").hasAuthority("ROLE_USER")
                //        .requestMatchers("/api/manager").hasAuthority("ROLE_MANAGER")
                //        .requestMatchers("/api/admin").hasAuthority("ROLE_ADMIN")
                //        .anyRequest().authenticated())
                //.cors(cors -> cors.configurationSource(configurationSource()))
                //.authenticationManager(authenticationManager)
                //.exceptionHandling(exception -> exception
                //        .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                //        .accessDeniedHandler(new RestAccessDeniedHandler()))
                //.with(new RestApiDsl<>(), restDsl -> restDsl
                //        .restSuccessHandler(restSuccessHandler)
                //        .restFailureHandler(restFailureHandler)
                //        .loginProcessingUrl("/api/login")
                //        .loginProcessingUrl("/api/login"));
                //.csrf((csrfConfig) -> {
                //    csrfConfig.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
                //    csrfConfig.csrfTokenRepository(httpSessionCsrfTokenRepository());
                //});

        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(menigiment -> {
                    menigiment.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .cors(cors -> cors.configurationSource(configurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/images/**", "/js/**", "/favicon.*", "/*/icon-*").permitAll()
                        .requestMatchers("/api","/api/signin","/api/signup").permitAll()
                        .requestMatchers("/api/user").hasAuthority("ROLE_USER")
                        .requestMatchers("/api/manager").hasAuthority("ROLE_MANAGER")
                        .requestMatchers("/api/admin").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                        .accessDeniedHandler(new RestAccessDeniedHandler()))
                .addFilterBefore(new JwtAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:5173/");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
