package com.PiperChat.User;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findUserById(Long id) {
        return findUserById(id);
    }

    @Override
    public Optional<UserProfileDTO> findByUserName(String username) {
        return findByUserName(username);
    }

    @Override
    public List<UserProfileDTO> findUserProfile() {
        return userRepository.findUserProfile();
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
