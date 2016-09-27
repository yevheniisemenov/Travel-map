package com.map.testUtils;

import com.map.dto.UserDto;

/**
 * @author Andrew Pasika
 */
public class DtoGenerator {

    public static UserDto generateNotRegisteredUserDto() {
        return UserDto.builder()
                .name("Robert")
                .surname("Langdon")
                .email("Robert@somewhere.com")
                .username("Rob.Langdon")
                .password("kodDavinchi")
                .build();
    }
}
