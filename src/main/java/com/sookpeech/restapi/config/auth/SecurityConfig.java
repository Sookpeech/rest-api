package com.sookpeech.restapi.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //enable spring security
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.
                csrf().disable()
                .headers().frameOptions().disable() // h2-console 사용 위해 옵션들 disable
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/h2-console/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint() //로그인 성공 시 사용자 정보 가져올 때 설정 담당
                            .userService(customOAuth2UserService); //로그인 성공 시 다음 조치를 진행할 인터페이스 구현체 등록
    }
}
