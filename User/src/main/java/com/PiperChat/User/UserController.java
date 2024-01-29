package com.PiperChat.User;

import com.PiperChat.User.profile.UserProfileDTO;
import com.PiperChat.User.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<List<UserEntity>> findAll(@RequestHeader("Authorization") String token) {
        String jwt = token.replace("Bearer", "");
        Claims claims = Jwts.parserBuilder().setSigningKey(SecurityConstants.JWT_SECRET).build().parseClaimsJws(jwt).getBody();

        String role = claims.get("role", String.class);
        if(role.equals("User")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(userService.findAllUsers());
    }
    @GetMapping(path = "/users/{id}")
    public ResponseEntity<UserEntity> findbyId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " is not found!")));
    }
    @GetMapping(path = "/{username}/following")
    public ResponseEntity<List<UserProfileDTO>> findByFollowing(@PathVariable String username) {
        return ResponseEntity.ok(userRepository.findFollowingByUsername(username));
    }
    @GetMapping(path = "/{username}")
    public ResponseEntity<UserProfileDTO> findByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.findByUserName(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with username " + username + " is not found!")));
    }
    @GetMapping(path = "/users/profile/{id}")
    public ResponseEntity<UserEntity> findByTokenId(@PathVariable Long id, @RequestHeader("Authorization") String token){
        String jwt = token.replace("Bearer", "");
        Claims claims = Jwts.parserBuilder().setSigningKey(SecurityConstants.JWT_SECRET).build().parseClaimsJws(jwt).getBody();

        Long userId = claims.get("userId", Long.class);

        if (!Objects.equals(id, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Username not found!")));
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
