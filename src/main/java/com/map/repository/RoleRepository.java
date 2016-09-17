package com.map.repository;

import com.map.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Andrew Pasika
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String role);
}
