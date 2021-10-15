package com.poscom.services;

import com.poscom.model.Comment;
import com.poscom.model.Post;
import com.poscom.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author Adam Jennings
 * @since 15/10/2021
 */
@Slf4j
@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;


    @Override
    public List<Post> getPosts() {
        return postRepository.findAll();

    }

    @Override
    public Set<Comment> getComments(Long postId) {
        return postRepository.findCommentsById(postId);
    }

    @Override
    public Post getPost(Long postId) {
        log.debug("[getPost] Returning Post by ID [{}]", postId);
        return postRepository.findPostById(postId);
    }

}
