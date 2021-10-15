package com.poscom.controllers;

import com.poscom.model.Comment;
import com.poscom.model.Post;
import com.poscom.services.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @author Adam Jennings
 * @since 14/10/2021
 */
@Slf4j
@RestController
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @RequestMapping("/posts")
    public List<Post> getPosts() {
        log.debug("[getPosts] Getting a list of all Posts...");
        List<Post> posts = postService.getPosts();
        log.debug("[getPosts] Retrieved {} posts", posts.size());
        return posts;
    }

    @RequestMapping("/post/{postId}")
    public Post getPost(@PathVariable Long postId) {
        log.debug("[getPost] Getting Post with ID: [{}]", postId);
        Post post = postService.getPost(postId);
        log.debug("[getPost] Retrieved Post [{}]", post);
        return post;
    }

    @RequestMapping("/posts/{postId}/comments")
    public Set<Comment> getComments(@PathVariable Long postId) {
        log.debug("[getComments] Getting a list of all Comments from Post [{}]...", postId);
        Set<Comment> comments = postService.getComments(postId);
        log.debug("[getComments] Retrieved [{}] comments", comments.size());
        return comments;
    }
}