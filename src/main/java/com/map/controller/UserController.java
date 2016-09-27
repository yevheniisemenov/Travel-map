package com.map.controller;

import com.map.config.security.SecurityAuthService;
import com.map.dto.UserDto;
import com.map.model.User;
import com.map.service.UserService;
import com.map.util.converter.dto.UserDtoConverter;
import com.map.util.converter.entity.UserEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

import static com.map.common.Constants.Web.USER_BASE_PATH;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Andrew Pasika
 * @author Yevhenii Semenov
 */
@Controller
@RequestMapping(USER_BASE_PATH)
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SecurityAuthService authService;

    @Autowired
    UserDtoConverter userDtoConverter;

    @Autowired
    UserEntityConverter userEntityConverter;

    @ResponseStatus(CREATED)
    @RequestMapping(path = "/register", method = POST, consumes = MULTIPART_FORM_DATA_VALUE)
    public UserDto register(@Valid UserDto userDto) {
        User registeredUser = userService.register(userEntityConverter.convert(userDto));
        authService.autoLogin(registeredUser);
        return userDtoConverter.convert(registeredUser);
    }
}
