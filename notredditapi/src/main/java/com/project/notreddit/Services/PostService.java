package com.project.notreddit.Services;

import com.project.notreddit.Models.Post;

public interface PostService {

    public Iterable<Post> listAllPosts();

    public Post createPost(Post post);
}
