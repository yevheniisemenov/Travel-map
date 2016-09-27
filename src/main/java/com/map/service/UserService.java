package com.map.service;

import com.map.model.User;
import org.springframework.stereotype.Service;

/**
 * @author Andrew Pasika
 * @author Yevhenii Semenov
 */
@Service
public interface UserService {

    User register(User user);
}
