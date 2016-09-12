package com.map.repository;

import com.map.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yevhenii Semenov
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
