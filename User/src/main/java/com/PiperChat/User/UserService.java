package com.PiperChat.User;

import com.PiperChat.User.profile.UserProfileDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();
    Optional<User> findUserById(Long id);
    Optional<UserProfileDTO> findByUserName(String username);
    List<UserProfileDTO> findUserProfile();
    User createUser(User userEntity);
}
