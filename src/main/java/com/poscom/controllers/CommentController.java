package com.poscom.controllers;

import com.poscom.model.Comment;
import com.poscom.rest.RestResponse;
import com.poscom.services.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author Adam Jennings
 * @since 14/10/2021
 */
@Slf4j
@RestController
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @RequestMapping("/posts/{postId}/comments")
    public RestResponse<Set<Comment>> getComments(@PathVariable Long postId) {
        log.debug("[getComments] Getting a list of all Comments from Post [{}]...", postId);
        Set<Comment> comments = commentService.getComments(postId);
        log.debug("[getComments] Retrieved [{}] comments", comments.size());
        return RestResponse.successful(comments);
    }
}
