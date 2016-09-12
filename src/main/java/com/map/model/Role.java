package com.map.model;

import com.map.dto.RoleDto;
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
public class Role implements GrantedAuthority, DtoConvertible<RoleDto> {

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

    @Override
    public RoleDto toDto() {
        return RoleDto.findByRole(role);
    }
}