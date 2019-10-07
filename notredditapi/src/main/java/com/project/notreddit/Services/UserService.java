package com.project.notreddit.Services;

import com.project.notreddit.Models.Comment;
import com.project.notreddit.Models.Post;
import com.project.notreddit.Models.User;

import java.util.List;

public interface UserService {

    public Iterable<User> listUsers();

    public Iterable<Post> listUserPosts(String username);

    public Iterable<Comment> listUserComments(String username);

    public User getUser(String username);


}
