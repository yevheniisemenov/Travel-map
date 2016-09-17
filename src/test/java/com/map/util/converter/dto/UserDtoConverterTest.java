package com.map.util.converter.dto;

import com.map.BaseApplicationTest;
import com.map.MapApplication;
import com.map.dto.RoleDto;
import com.map.dto.UserDto;
import com.map.model.Comment;
import com.map.model.Role;
import com.map.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Date;
import java.util.Collections;

import static com.map.common.Constants.Common.ROLE_PREFIX;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Andrew Pasika
 */
@ContextConfiguration(classes = MapApplication.class)
public class UserDtoConverterTest extends BaseApplicationTest {

    @Autowired
    UserDtoConverter userDtoConverter;

    @Test
    public void whenConvertUserEntityToUserDto_thenCorrect() {
        User user = User.builder()
                .id(1L)
                .name("Bruce")
                .surname("Lee")
                .username("bruce.lee")
                .password("password")
                .email("bruce.lee@gmail.com")
                .comments(Collections.singletonList(new Comment(1L, new User(), "Travel-map", Date.valueOf("2016-09-17"))))
                .authorities(Collections.singletonList(Role.builder().role("ADMIN").build()))
                .build();

        UserDto userDto = userDtoConverter.convert(user);

        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getName(), userDto.getName());
        assertEquals(user.getSurname(), userDto.getSurname());
        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getEmail(), userDto.getEmail());
        // Comments
        assertEquals(user.getComments().get(0).getId(), userDto.getComments().get(0).getId());
        assertEquals(user.getComments().get(0).getDate(), userDto.getComments().get(0).getDate());
        assertEquals(user.getComments().get(0).getText(), userDto.getComments().get(0).getText());
        assertEquals(user.getComments().get(0).getUser(), userDto.getComments().get(0).getUser());
        // Authorities
        for (Role role : user.getAuthorities()) {
            assertTrue(userDto.getAuthorities().stream().map(RoleDto::getAuthority)
                    .allMatch((o) -> o.equalsIgnoreCase(ROLE_PREFIX + role.getAuthority())));
        }
    }
}
