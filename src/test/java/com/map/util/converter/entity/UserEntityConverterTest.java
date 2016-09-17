package com.map.util.converter.entity;

import com.map.BaseApplicationTest;
import com.map.MapApplication;
import com.map.dto.CommentDto;
import com.map.dto.RoleDto;
import com.map.dto.UserDto;
import com.map.model.Role;
import com.map.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Date;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Andrew Pasika
 */
@ContextConfiguration(classes = MapApplication.class)
public class UserEntityConverterTest extends BaseApplicationTest {

    @Autowired
    UserEntityConverter userEntityConverter;

    @Test
    public void whenConvertUserDtoToEntity_thenCorrect() {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .name("Bruce")
                .surname("Lee")
                .username("bruce.lee")
                .password("password")
                .email("bruce.lee@gmail.com")
                .comments(Collections.singletonList(new CommentDto(1L, new User(), "Travel-map", Date.valueOf("2016-09-17"))))
                .authorities(new HashSet<>(Collections.singletonList(RoleDto.USER)))
                .build();

        User user = userEntityConverter.convert(userDto);

        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getSurname(), user.getSurname());
        assertEquals(userDto.getUsername(), user.getUsername());
        assertEquals(userDto.getPassword(), user.getPassword());
        assertEquals(userDto.getEmail(), user.getEmail());
        // Comments
        assertEquals(userDto.getComments().get(0).getId(), user.getComments().get(0).getId());
        assertEquals(userDto.getComments().get(0).getUser(), user.getComments().get(0).getUser());
        assertEquals(userDto.getComments().get(0).getDate(), user.getComments().get(0).getDate());
        assertEquals(userDto.getComments().get(0).getText(), user.getComments().get(0).getText());
        // Authorities
        user.getAuthorities().forEach(System.out::println);
        for (RoleDto roleDto : userDto.getAuthorities()) {
            assertTrue(user.getAuthorities().stream().map(Role::getAuthority)
                    .allMatch((o) -> o.equalsIgnoreCase(roleDto.getAuthority())));
        }

    }
}
