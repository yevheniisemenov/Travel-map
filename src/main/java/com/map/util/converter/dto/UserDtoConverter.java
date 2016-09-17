package com.map.util.converter.dto;

import com.map.dto.RoleDto;
import com.map.dto.UserDto;
import com.map.model.User;
import com.map.util.converter.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * @author Andrew Pasika
 */
@Component
public class UserDtoConverter implements Converter<User, UserDto> {

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDto convert(User user) {
        modelMapper.map(user, UserDto.class);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        userDto.setAuthorities(user.getAuthorities().stream().map((o) -> RoleDto.findByRole(o.getAuthority()))
                .collect(Collectors.toCollection(HashSet::new)));
        return userDto;
    }
}
