package com.PiperChat.User.post;

import com.PiperChat.User.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostEntity> findAllPosts() {
        return postRepository.findAll();
    }
    @Override
    public PostEntity createPost(PostEntity postEntity) {
        return postRepository.save(postEntity);
    }

    @Override
    public void deletePosts(Long id) {
        postRepository.deleteById(id);
    }
}
