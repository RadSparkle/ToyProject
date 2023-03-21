package com.toyproject.util.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CorsFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)    // 세션 사용 안함
                .and()
                .addFilter(corsFilter)    // 인증(O), security Filter 에 등록 / @CrossOrigin (인증X)
                .formLogin().disable()    // Form login 안함
                .httpBasic().disable()
                .authorizeRequests()
                .anyRequest()        // 그 외 모든 요청에 대해서 허용하라.
                .permitAll();
    }
}
