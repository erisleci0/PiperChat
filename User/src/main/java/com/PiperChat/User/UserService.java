package com.PiperChat.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> findAllUsers();
    Optional<UserEntity> findUserById(Long id);
    Optional<UserProfileDTO> findByUserName(String username);
    List<UserProfileDTO> findUserProfile();
    UserEntity createUser(UserEntity userEntity);
}
