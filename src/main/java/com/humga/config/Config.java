package com.humga.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public JdbcDaoImpl userDetailsService() {
//        JdbcDaoImpl jdbcDaoImpl = new JdbcDaoImpl();
//        jdbcDaoImpl.setDataSource(); setDataSource(dataSource());
//        jdbcDaoImpl.setUsersByUsernameQuery("select username,password,enabled from comp_users where username = ?");
//        jdbcDaoImpl.setAuthoritiesByUsernameQuery("select username,authority from comp_authorities where username = ?");
//        return jdbcDaoImpl;
//    }

    //в конфигурации Spring security укажем, чтобы наше api не требовало аутентификации
    @EnableWebSecurity
    public static class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
        }
    }
}
