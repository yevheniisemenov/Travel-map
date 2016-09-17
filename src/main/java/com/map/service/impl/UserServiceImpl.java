package com.map.service.impl;

import com.map.exception.DuplicatedUserException;
import com.map.model.Role;
import com.map.model.User;
import com.map.repository.RoleRepository;
import com.map.repository.UserRepository;
import com.map.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.text.MessageFormat;
import java.util.Collections;

/**
 * @author Yevhenii Semenov, Andrew Pasika
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public User register(User user, Errors errors) {
        if (errors.hasErrors()) {
            throw new IllegalArgumentException("Submitted User doesn't follow required rules!");
        }
        Validate.notNull(user, "No user entity was provided.");
        checkUserId(user.getId(), false);
        log.debug("Registration of user [{}]", user);
        User savedUser = userRepository.save(prepareForRegistration(user));
        log.debug("User registered (ID:{})", user.getId());
        return savedUser;
    }

    private void setRole(User user) {
        if (user.getAuthorities() == null || user.getAuthorities().isEmpty()) {
            Role userRole = roleRepository.findByRole("USER");
            user.setAuthorities(Collections.singleton(userRole));
        }
    }

    private void checkUserForUnicity(User User) {
        User byEmail = userRepository.findByEmail(User.getEmail());
        User byUsername = userRepository.findByUsername(User.getUsername());

        if (byEmail != null && byUsername != null) {
            throw new DuplicatedUserException("User with such email and login already exists!");
        } else if (byEmail != null) {
            throw new DuplicatedUserException("User with such email already exists!");
        } else if (byUsername != null) {
            throw new DuplicatedUserException("User with such username already exists!");
        }
    }

    private User prepareForRegistration(User user) {
        checkUserForUnicity(user);
        setRole(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    private void checkUserId(Long id, boolean expected) {
        boolean exists = id != null && userRepository.exists(id);
        String message = null;
        if (exists && !expected) {
            message = MessageFormat.format("User already exists!", id);
        }
        if (expected && !exists) {
            message = MessageFormat.format("User doesn't exists yet!", id);
        }
        if (message != null) {
            throw new IllegalArgumentException(message);
        }
    }
}
