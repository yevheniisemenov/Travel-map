package com.map.service;

import com.map.model.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

/**
 * @author Yevhenii Semenov, Andrew Pasika
 */
@Service
public interface UserService {

    User register(User User, Errors errors);
}
