package com.PiperChat.User.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "/posts")
    public ResponseEntity<List<PostEntity>> findAll(){
        return ResponseEntity.ok(postService.findAllUsers());
    }

    @DeleteMapping(path = "posts/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        postService.deletePosts(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
