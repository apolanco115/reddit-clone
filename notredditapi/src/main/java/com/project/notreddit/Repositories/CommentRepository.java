package com.project.notreddit.Repositories;

import com.project.notreddit.Models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
