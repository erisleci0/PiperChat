package com.PiperChat.User;

import com.PiperChat.User.Profile.UserProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserProfileDTO> findByUserName(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public List<UserProfileDTO> findUserProfile() {
        return userRepository.findUserProfile();
    }

    @Override
    public User createUser(User userEntity) {
        return userRepository.save(userEntity);
    }
}
