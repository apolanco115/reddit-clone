package com.project.notreddit.Controllers;

import com.project.notreddit.Config.IAuthentication;
import com.project.notreddit.Models.Post;
import com.project.notreddit.Models.User;
import com.project.notreddit.Repositories.PostRepository;
import com.project.notreddit.Repositories.UserRepository;
import com.project.notreddit.Services.PostService;
import com.project.notreddit.Services.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    IAuthentication authImpl;

    @GetMapping("/post/list")
    public Iterable<Post> listAllPosts(){ return postService.listAllPosts(); }

    @PostMapping("/post")
    public Post createPost(@RequestBody Post post){
        return postService.createPost(post);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity deletePostById(@PathVariable Long postId) {
        return postService.deletePostById(postId);
    }


}
