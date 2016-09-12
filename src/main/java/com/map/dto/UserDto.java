package com.map.dto;

import com.map.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Yevhenii Semenov
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Dto<User> {

    private Long id;

    @Size(min = 2, max = 24)
    private String name;

    @Size(min = 2, max = 24)
    private String surname;

    @NotNull
    @Size(min = 2, max = 24)
    private String username;

    @Size(min = 6)
    private String password;

    @NotNull
    @Email
    private String email;


    @Override
    public User toEntity() {
        return User.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .username(username)
                .password(password)
                .email(email)
                .build();
    }
}
