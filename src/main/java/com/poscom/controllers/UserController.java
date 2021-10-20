package com.poscom.controllers;

import com.poscom.model.User;
import com.poscom.rest.RestResponse;
import com.poscom.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Adam Jennings
 * @since 14/10/2021
 */
@Slf4j
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping("/users/{userId}")
    public RestResponse<User> getUser(@PathVariable Long userId) {
        log.debug("[getUser] Getting User with ID: [{}]", userId);
        User user = userService.getUser(userId);
        log.debug("[getUser] Retrieved User [{}]", user);
        return RestResponse.successful(user);
    }
}
