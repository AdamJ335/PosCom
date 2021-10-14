package com.poscom.repositories;

import com.poscom.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Adam Jennings
 * @since 14/10/2021
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}
