package com.kanduit.sso.domain.service;

import com.kanduit.sso.application.mapper.UserMapper;
import com.kanduit.sso.domain.entity.User;
import com.kanduit.sso.dto.request.UserInitRequestDTO;
import com.kanduit.sso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public boolean initUser(UserInitRequestDTO userInitRequestDTO) {
        User newUser = userMapper.toUser(userInitRequestDTO);
        Optional<User> queriedUser = userRepository.findByEmail(newUser.getEmail());
        if (queriedUser.isPresent()) {
            return userMapper.toUserDTO(queriedUser.get()).getEmailData().verifiedAt() == null;
        } else
            userRepository.save(newUser);

        /* TODO: Implement email address verification.
         * Short-term multiple email verification attempts should be blocked. For example, use a `verify` table to track and manage verification requests.
         * Add functionality to send a registration link to the user's email address for verification.
         * Ensure that the email verification process is secure and handles user responses properly.
         */

        return true;
    }
}
