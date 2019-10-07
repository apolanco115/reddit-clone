package com.project.notreddit.Services;

import com.project.notreddit.Models.Comment;
import com.project.notreddit.Models.User;
import com.project.notreddit.Repositories.CommentRepository;
import com.project.notreddit.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<Comment> listAllComments(){
        return commentRepository.findAll();
    }

    @Override
    public Comment createComment(Comment comment){
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteCommentById(Long commentId){
        Comment comment = commentRepository.findById(commentId).get();
        User user = userRepository.findById(comment.getUser().getId()).get();
        user.getComments().remove(comment);
    }

}
