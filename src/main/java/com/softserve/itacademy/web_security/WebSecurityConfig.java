package com.softserve.itacademy.web_security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UsernamePasswordAuthenticationFilter authenticationFilter;

    @Autowired
    public void setAuthenticationFilter(@Lazy UsernamePasswordAuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/login-form").permitAll()
                .antMatchers("/users/create").permitAll()
                .anyRequest().authenticated();
//                .and().csrf().disable();

        http.addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling().authenticationEntryPoint(
                (request, response, authException) -> response.sendRedirect("/login-form"));
        http.exceptionHandling().accessDeniedHandler(
                (request, response, accessDeniedException) -> response.sendRedirect("/forbidden"));
    }

    @Bean("bCrypt")
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}