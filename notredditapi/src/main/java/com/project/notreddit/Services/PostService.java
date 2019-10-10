package com.project.notreddit.Services;

import com.project.notreddit.Models.Post;
import org.springframework.http.ResponseEntity;

public interface    PostService {

    public Iterable<Post> listAllPosts();

    public Post createPost(Post post);

    public ResponseEntity deletePostById(Long postId);
}
