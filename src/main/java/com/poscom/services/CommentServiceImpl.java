package com.poscom.services;

import com.poscom.model.Comment;
import com.poscom.model.Post;
import com.poscom.repository.CommentRepository;
import com.poscom.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Adam Jennings
 * @since 20/10/2021
 */
@Slf4j
@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public Set<Comment> getComments(Long postId) {

        Post post = postRepository.findPostById(postId);

        if(post == null){
            return null;
        }

        return commentRepository.findCommentsByPost(post);
    }
}
