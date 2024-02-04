package com.PiperChat.User.post;

import java.util.List;

public interface PostService {
    List<PostEntity> findAllPosts();
    PostEntity createPost(PostEntity postEntity);
    void deletePosts(Long id);
}
