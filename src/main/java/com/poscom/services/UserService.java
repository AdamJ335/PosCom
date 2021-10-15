package com.poscom.services;

import com.poscom.model.User;

/**
 * @author Adam Jennings
 * @since 15/10/2021
 */
public interface UserService {

    User getUser(Long userId);

}
