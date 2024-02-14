package com.in28minutes.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//        1.	모든 요청에 자격 증명
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );
//        2.	자격 증명이 없거나 인증되지 않았다면 기본값으로 로그인 웹 페이지가 나타난다.
        http.httpBasic(withDefaults());
//        3.	CSRF 확인 -> POST와 PUT 요청에 영향을 주게 된다.
        http.csrf().disable();

        return http.build();
    }
}
