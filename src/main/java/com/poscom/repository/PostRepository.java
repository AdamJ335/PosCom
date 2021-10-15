package com.poscom.repository;

import com.poscom.model.Comment;
import com.poscom.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * @author Adam Jennings
 * @since 14/10/2021
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    Set<Comment> findCommentsById(Long id);

    Post findPostById(Long postId);
}
