package com.map.repository;

import com.map.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yevhenii Semenov
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
