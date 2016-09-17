package com.map.model;

import com.map.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author Yevhenii Semenov, Andrew Pasika
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @Size(min = 2, max = 24)
    private String username;

    @Size(min = 6, max = 24)
    private String password;

    @NotEmpty
    @Email(regexp = ".+@.+\\..+")
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany
    Collection<Role> authorities = new HashSet<>();

    public UserDto toDto() {
        return UserDto.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .username(username)
                .password(password)
                .email(email)
                .build();
    }

}
