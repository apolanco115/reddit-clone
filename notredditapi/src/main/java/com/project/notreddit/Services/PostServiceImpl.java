package com.project.notreddit.Services;

import com.project.notreddit.Models.Post;
import com.project.notreddit.Models.User;
import com.project.notreddit.Repositories.PostRepository;
import com.project.notreddit.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
        User user = userRepository.findById(post.getUser().getId()).get();
        user.addPost(post);
        return postRepository.save(post);
    }

    @Override
    @Transactional
    public void deletePostById(Long postId){
        Post post = postRepository.findById(postId).get();
        User user = userRepository.findById(post.getUser().getId()).get();
        user.getPosts().remove(post);
    }

}
