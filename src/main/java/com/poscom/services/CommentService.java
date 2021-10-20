package com.poscom.services;

import com.poscom.model.Comment;

import java.util.Set;

/**
 * @author Adam Jennings
 * @since 20/10/2021
 */
public interface CommentService {
    Set<Comment> getComments(Long postId);
}
