package com.humga.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final String SQL_USERS_BY_USERNAME =
            "select username,password,enabled from users where username = ?";
    private final String SQL_AUTHORITIES_BY_USERNAME =
            "select username, authority from users u join authorities a on u.id = a.user_id where username = ?";
    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JdbcDaoImpl userDetailsService() {
        JdbcDaoImpl jdbcDaoImpl = new JdbcDaoImpl();
        jdbcDaoImpl.setDataSource(dataSource);
        //select name, product_name from customers c join orders o on c.id = o.customer_id where name = ?
        jdbcDaoImpl.setUsersByUsernameQuery(SQL_USERS_BY_USERNAME);
        jdbcDaoImpl.setAuthoritiesByUsernameQuery(SQL_AUTHORITIES_BY_USERNAME);
        return jdbcDaoImpl;
    }

    //в конфигурации Spring security укажем, чтобы наше api не требовало аутентификации
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
}

