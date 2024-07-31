package com.kanduit.sso.application.mapper;

import com.kanduit.sso.domain.entity.User;
import com.kanduit.sso.dto.request.UserInitRequestDTO;
import com.kanduit.sso.dto.user.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toUserDTO(User user) {
        return new UserDTO(
                new UserDTO.InfoDTO(user.getDisplayName(), user.getBirthDate()),
                new UserDTO.EmailDTO(user.getEmail(), user.getEmailVerifiedAt()),
                new UserDTO.PasswordDTO(user.getHashedPassword(), user.getNextPasswordUpdate()),
                new UserDTO.PhoneDTO(user.getCountryCode(), user.getPhoneNumber(), user.getPhoneVerifiedAt())
        );
    }

    public User toUser(UserInitRequestDTO requestDTO) {
        return new User(requestDTO.email());
    }
}
