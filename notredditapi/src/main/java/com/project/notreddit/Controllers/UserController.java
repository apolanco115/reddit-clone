package com.project.notreddit.Controllers;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.project.notreddit.Models.Comment;
import com.project.notreddit.Models.Post;
import com.project.notreddit.Models.User;
import com.project.notreddit.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public String helloWorld(){ return "Hello World!"; }

    @GetMapping("/user/list")
    public List<User> listUsers(){ return userService.listUsers(); }

    @GetMapping("/user/{username}/posts")
    public List<Post> listUserPosts(@PathVariable String username){ return userService.listUserPosts(username); }

    @GetMapping("/user/{username}/comments")
    public List<Comment> listUserComments(@PathVariable String username){ return userService.listUserComments(username); }
}
