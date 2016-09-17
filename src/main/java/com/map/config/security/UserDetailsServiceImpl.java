package com.map.config.security;

import com.map.model.User;
import com.map.repository.UserRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * @author Andrew Pasika
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        Validate.notNull(user, MessageFormat.format("No user found with {0}", username));
        return new org.springframework.security.core.userdetails.User(user.getUsername()
                , user.getPassword(), user.getAuthorities());
    }
}
