package com.project.notreddit.Controllers;

import com.project.notreddit.Config.IAuthentication;
import com.project.notreddit.Models.Comment;
import com.project.notreddit.Models.Post;
import com.project.notreddit.Repositories.CommentRepository;
import com.project.notreddit.Repositories.UserRepository;
import com.project.notreddit.Services.CommentService;
import com.project.notreddit.Services.PostService;
import com.project.notreddit.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    private IAuthentication authImpl;

    @GetMapping("/comment/list")
    public Iterable<Comment> listAllPosts(){ return commentService.listAllComments(); }

    @PostMapping("/comment")
    public Comment createComment(@RequestBody Comment comment){
        return commentService.createComment(comment);
    }


    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity deleteCommentById(@PathVariable Long commentId){
        Comment comment = commentRepository.findById(commentId).get();
        Authentication auth = authImpl.getAuthentication();
        Long currUserId = userService.getUser(auth.getName()).getId();
        Long commentUserId = comment.getUser().getId();

        if(currUserId == commentUserId) {
            commentService.deleteCommentById(commentId);
        }else {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }




}
