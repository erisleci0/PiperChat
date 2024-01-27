package com.PiperChat.User;

import com.PiperChat.User.profile.UserProfileDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> findAllUsers();
    Optional<UserEntity> findUserById(Long id);
    Optional<UserProfileDTO> findByUserName(String username);
    List<UserProfileDTO> findUserProfile();
    UserEntity createUser(UserEntity userEntity);
}
