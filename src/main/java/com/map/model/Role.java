package com.map.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Andrew Pasika
 */
@Data
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String role;

    @Getter
    @ManyToMany(mappedBy = "authorities")
    private Collection<User> users;

    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public String getAuthority() {
        return role;
    }
}