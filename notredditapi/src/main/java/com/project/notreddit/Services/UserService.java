package com.project.notreddit.Services;

import com.project.notreddit.Models.Comment;
import com.project.notreddit.Models.Post;
import com.project.notreddit.Models.User;

import java.util.List;

public interface UserService {

    public List<User> listUsers();

    public List<Post> listUserPosts(String username);

    public List<Comment> listUserComments(String username);

    public User getUser(String username);


}
