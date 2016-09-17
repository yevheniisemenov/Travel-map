package com.map.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Yevhenii Semenov, Andrew Pasika
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

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
    private List<CommentDto> comments = new ArrayList<>();
    private Collection<RoleDto> authorities = new ArrayList<>();
}
