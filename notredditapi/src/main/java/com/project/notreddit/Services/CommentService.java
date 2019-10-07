package com.project.notreddit.Services;

import com.project.notreddit.Models.Comment;

public interface CommentService {

    public Iterable<Comment> listAllComments();

    public Comment createComment(Comment comment);

    public void deleteCommentById(Long commentId);

}
