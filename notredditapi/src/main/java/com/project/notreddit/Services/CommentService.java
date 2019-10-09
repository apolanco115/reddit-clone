package com.project.notreddit.Services;

import com.project.notreddit.Models.Comment;
import org.springframework.http.ResponseEntity;

public interface CommentService {

    public Iterable<Comment> listAllComments();

    public Comment createComment(Comment comment);

    public ResponseEntity deleteCommentById(Long commentId);

}
