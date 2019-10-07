package com.project.notreddit.Repositories;

import com.project.notreddit.Models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

}
