package com.PiperChat.User.post;

import java.util.List;

public interface PostService {
    List<PostEntity> findAllUsers();
    void deletePosts(Long id);
}
