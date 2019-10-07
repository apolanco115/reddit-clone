package com.project.notreddit.Services;

import com.project.notreddit.Models.Post;
import com.project.notreddit.Models.User;
import com.project.notreddit.Repositories.PostRepository;
import com.project.notreddit.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<Post> listAllPosts(){
        return postRepository.findAll();
    }

    @Override
    public Post createPost(Post post){
        return postRepository.save(post);
    }

}
