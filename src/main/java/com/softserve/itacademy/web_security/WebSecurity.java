package com.softserve.itacademy.web_security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;
import java.sql.DriverManager;


@Configuration
@EnableWebSecurity
public class WebSecurity  extends WebSecurityConfigurerAdapter {

        @Override
    protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().
                    anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login-form")
                    .defaultSuccessUrl("/home")
                    .failureUrl("/login-form?error=true")
                    .permitAll()
                    .and()
                    .logout()
                    .logoutUrl("/perfom-logout")
                    .logoutSuccessUrl("/login-form");

        }


        @Bean
        DataSource dataSource(){
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
            dataSource.setUsername("postgrades");
            dataSource.setPassword("root");
            return dataSource;

        }


        @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,
                                @Qualifier("UserDetailsService") UserDetailsService userDetailsService,
                                DataSource dataSource) throws Exception{
            auth.jdbcAuthentication().dataSource(dataSource);
            auth.userDetailsService(userDetailsService);
        }



}
