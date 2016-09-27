package com.map.config.security;

import com.map.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static com.map.common.Constants.Web.*;

/**
 * @author Andrew Pasika
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Login & logout config
        http
                .formLogin()
                .loginPage(LOGIN_URI)
                .successHandler(authenticationSuccessHandler)
                .and()
                .logout()
                .logoutSuccessUrl(HOME_URI);

        // Remember-me config
        http
                .rememberMe()
                .key("LibrarySecurity");

        http.httpBasic();

        http.csrf().disable(); // TODO: 9/27/16 enable in production

        http.authorizeRequests()
                .anyRequest().hasRole(RoleDto.USER.name())
                .antMatchers(USER_BASE_PATH + REGISTER_URI).permitAll()
                .antMatchers(ERROR_URI).permitAll();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider userAuthenticationProvider = new DaoAuthenticationProvider();
        userAuthenticationProvider.setUserDetailsService(userDetailsService);
        userAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return userAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
}
