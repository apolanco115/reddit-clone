package com.project.notreddit.Controllers;

import com.project.notreddit.Models.Comment;
import com.project.notreddit.Models.Post;
import com.project.notreddit.Repositories.UserRepository;
import com.project.notreddit.Services.CommentService;
import com.project.notreddit.Services.PostService;
import com.project.notreddit.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/comment/list")
    public Iterable<Comment> listAllPosts(){ return commentService.listAllComments(); }

    @PostMapping("/comment")
    public Comment createPost(@RequestBody Comment comment){
        return commentService.createComment(comment);
    }

}
