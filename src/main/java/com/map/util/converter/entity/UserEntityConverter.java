package com.map.util.converter.entity;

import com.map.dto.UserDto;
import com.map.model.Role;
import com.map.model.User;
import com.map.util.converter.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * @author Andrew Pasika
 */
@Component
public class UserEntityConverter implements Converter<UserDto, User> {

    @Autowired
    ModelMapper modelMapper;

    @Override
    public User convert(UserDto userDto) {
        configMapper();
        User user = modelMapper.map(userDto, User.class);
        user.setAuthorities(userDto.getAuthorities().stream().map((o) -> Role.builder()
                .role(o.getAuthority()).build()).collect(Collectors.toCollection(HashSet::new)));
        return user;
    }

    private void configMapper() {
        modelMapper.addMappings(new PropertyMap<UserDto, User>() {
            @Override
            protected void configure() {
                map().setAuthorities(null);
            }
        });
    }
}
