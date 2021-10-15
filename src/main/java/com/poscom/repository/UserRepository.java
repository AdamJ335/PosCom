package com.poscom.repository;

import com.poscom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Adam Jennings
 * @since 14/10/2021
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserById(Long userId);

}
