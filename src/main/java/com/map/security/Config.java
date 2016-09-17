package com.map.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.map.common.Constants.Web.*;

/**
 * @author Andrew Pasika
 */
@Configuration
public class Config extends WebSecurityConfigurerAdapter {

    @Autowired
    UserAuthSuccessHandler userAuthSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Login & logout config
        http
                .formLogin()
                .loginPage(LOGIN_URI)
                .successHandler(userAuthSuccessHandler)
                .and()
                .logout()
                .logoutSuccessUrl(HOME_URI);

        // Remember-me config
        http
                .rememberMe()
                .key("LibrarySecurity");

        http.httpBasic();

        http.csrf().disable();

        http
                .authorizeRequests()
                .antMatchers(LOGOUT_URI).authenticated()
                .antMatchers(USER_BASE_PATH + REGISTER_URI).permitAll()
                .antMatchers(ERROR_URI).permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
}
