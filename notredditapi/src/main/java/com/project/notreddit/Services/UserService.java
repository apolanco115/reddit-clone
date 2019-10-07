package com.project.notreddit.Services;

import com.project.notreddit.Models.Comment;
import com.project.notreddit.Models.Post;
import com.project.notreddit.Models.User;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService {

    public Iterable<User> listUsers();

    public Iterable<Post> listUserPosts(String username);

    public Iterable<Comment> listUserComments(String username);

    public User getUser(String username);

    public String createUser(User newUser);

    public String login(User user);

}
