package com.poscom.controllers;

import com.poscom.model.Post;
import com.poscom.rest.RestResponse;
import com.poscom.services.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public RestResponse<List<Post>> getPosts() {
        log.debug("[getPosts] Getting a list of all Posts...");
        List<Post> posts = postService.getPosts();
        log.debug("[getPosts] Retrieved {} posts", posts.size());
        return RestResponse.successful(posts);
    }

    @RequestMapping("/posts/{postId}")
    public RestResponse<Post> getPost(@PathVariable Long postId) {
        log.debug("[getPost] Getting Post with ID: [{}]", postId);
        Post post = postService.getPost(postId);
        log.debug("[getPost] Retrieved Post [{}]", post);
        return RestResponse.successful(post);
    }

}
