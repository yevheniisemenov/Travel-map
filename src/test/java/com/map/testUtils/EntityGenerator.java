package com.map.testUtils;

import com.map.model.User;

/**
 * @author Andrew Pasika
 */
public class EntityGenerator {

    private static int userNum;

    /**
     * Generates new {@link User} for every request
     *
     * @return generated {@link User}
     */
    public static User generateNotRegisteredUser() {
        userNum++;
        return User.builder()
                .name("Robert")
                .surname("Langdon")
                .email("Robert" + userNum + "@somewhere.com")
                .username("Rob" + userNum + ".Langdon")
                .password("kodDavinchi")
                .build();
    }
}
