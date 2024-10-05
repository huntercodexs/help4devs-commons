package com.huntercodexs.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${app.basic-authentication.user-name}")
    String username;
    @Value("${app.basic-authentication.password}")
    String password;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http
//            .cors()
//            .and()
//            .csrf().disable()
//            .authorizeRequests()
//            .antMatchers(
//                "/api/**"
//            )
//            .authenticated()
//            .and()
//            .httpBasic();

        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/api/**"
                ).permitAll();

    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser(username)
            .password("{noop}"+password)
            .roles("USER");
    }

}
