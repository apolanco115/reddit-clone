package com.project.notreddit.Controllers;

import com.project.notreddit.Models.Post;
import com.project.notreddit.Models.User;
import com.project.notreddit.Repositories.UserRepository;
import com.project.notreddit.Services.PostService;
import com.project.notreddit.Services.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/post/list")
    public Iterable<Post> listAllPosts(){ return postService.listAllPosts(); }

    @PostMapping("/post")
    public Post createPost(@RequestBody Post post){
        return postService.createPost(post);
    }

}
