package com.PiperChat.User;

import com.PiperChat.User.profile.UserProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<UserEntity>> findAll() {
        return ResponseEntity.ok(userService.findAllUsers());
    }
    @GetMapping(path = "/users/{id}")
    public ResponseEntity<UserEntity> findbyId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " is not found!")));
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<UserProfileDTO> findByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.findByUserName(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with username " + username + " is not found!")));
    }

    @GetMapping(path = "/users/profile")
    public ResponseEntity<List<UserProfileDTO>> findByUserProfileName(){
        return ResponseEntity.ok(userService.findUserProfile());
    }

    @PostMapping(path = "/users")
    public ResponseEntity<String> create(@RequestBody UserEntity userEntity){

        if (userRepository.existsByUsername(userEntity.getUsername()) || userRepository.existsByEmail(userEntity.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Username or email already taken!!");
        }
        UserEntity savedUserEntity = userService.createUser(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created with ID: " + savedUserEntity.getId());
    }

}
