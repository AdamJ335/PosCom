package com.poscom.services;

import com.poscom.model.User;
import com.poscom.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Adam Jennings
 * @since 15/10/2021
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User getUser(Long userId) {
        return userRepository.getUserById(userId);
    }
}
