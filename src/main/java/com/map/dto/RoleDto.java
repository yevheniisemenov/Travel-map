package com.map.dto;

import com.map.model.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;

import static com.map.common.Constants.Common.ROLE_PREFIX;

/**
 * @author Andrew Pasika
 */
public enum RoleDto implements Dto<Role>, GrantedAuthority {

    NOT_VERIFIED("NOT_VERIFIED"),
    USER("USER"),
    ADMIN("ADMIN");

    private String role;

    public static RoleDto findByRole(String role) {
        String withPrefix = ROLE_PREFIX + role;
        return Arrays.stream(RoleDto.values()).filter((o) -> o.getAuthority().equalsIgnoreCase(withPrefix))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    RoleDto(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return ROLE_PREFIX + role;
    }

    @Override
    public Role toEntity() {
        Role entity = new Role();
        entity.setRole(this.role);
        return entity;
    }
}
