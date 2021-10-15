package com.poscom.services;

import com.poscom.model.Comment;
import com.poscom.model.Post;

import java.util.List;
import java.util.Set;

/**
 * @author Adam Jennings
 * @since 15/10/2021
 */
public interface PostService {

    List<Post> getPosts();

    Set<Comment> getComments(Long postId);

    Post getPost(Long postId);
}
