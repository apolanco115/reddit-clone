package com.project.notreddit.Services;

import com.project.notreddit.Models.Comment;
import com.project.notreddit.Models.Post;
import com.project.notreddit.Models.User;
import com.project.notreddit.Repositories.UserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<User> listUsers(){
        return userRepository.findAll();
    }

    @Override
    public Iterable<Post> listUserPosts(String username){
        User user = getUser(username);
        return user.getPosts();
    }

    @Override
    public Iterable<Comment> listUserComments(String username){
        User user = getUser(username);
        return user.getComments();
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

}
