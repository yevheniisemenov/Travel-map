package com.map.config.security;

import com.map.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author Andrew Pasika
 */
@Service
public class SecurityAuthService {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    UserDetailsService userDetailsService;

    public void autoLogin(User user) {
        UserDetails accountDetails = userDetailsService.loadUserByUsername(user.getUsername());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(accountDetails.getUsername()
                , accountDetails.getPassword(), accountDetails.getAuthorities());
        Authentication authenticate = authManager.authenticate(token);
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authenticate);
    }
}
