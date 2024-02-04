package com.PiperChat.User.post;

import com.PiperChat.User.security.SecurityConstants;
import com.PiperChat.User.user.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
public class PostController {
    private PostService postService;
    private PostRepository postRepository;
    @Autowired
    public PostController(PostService postService,PostRepository postRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
    }
    @GetMapping(path = "/posts")
    public ResponseEntity<List<PostEntity>> findAll(){
        return ResponseEntity.ok(postService.findAllPosts());
    }
    @PostMapping(path = "/posts")
    public ResponseEntity<PostEntity> createPosts(@RequestBody PostEntity postEntity){
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postEntity));
    }
    @DeleteMapping(path = "posts/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        postService.deletePosts(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
