package com.map.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yevhenii Semenov
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

    @Size(min = 1, max = 26)
    private String username;

    @Size(min = 6, max = 26)
    private String password;

    @NotEmpty
    @Email(regexp = ".+@.+\\..+")
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();
}
