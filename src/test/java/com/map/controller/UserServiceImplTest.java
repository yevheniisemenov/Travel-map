package com.map.controller;

import com.map.BaseApplicationTest;
import com.map.MapApplication;
import com.map.model.User;
import com.map.repository.UserRepository;
import com.map.service.UserService;
import com.map.testUtils.EntityGenerator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Andrew Pasika
 */
@ContextConfiguration(classes = MapApplication.class)
public class UserServiceImplTest extends BaseApplicationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void registerTwoUsers_validData_ok() {
        userService.register(EntityGenerator.generateNotRegisteredUser());
        List<User> users = userRepository.findAll();
        assertThat(users.size(), equalTo(1));

        userService.register(EntityGenerator.generateNotRegisteredUser());
        users = userRepository.findAll();
        assertThat(users.size(), equalTo(2));
    }
}
