package com.project.notreddit.Services;

import com.project.notreddit.Config.IAuthentication;
import com.project.notreddit.Models.Comment;
import com.project.notreddit.Models.User;
import com.project.notreddit.Repositories.CommentRepository;
import com.project.notreddit.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private IAuthentication authImpl;

    @Autowired
    UserService userService;

    @Override
    public Iterable<Comment> listAllComments(){
        return commentRepository.findAll();
    }

    @Override
    public Comment createComment(Comment comment){
        Authentication auth = authImpl.getAuthentication();
        User user = userService.getUser(auth.getName());
        comment.setUser(user);
        user.addComment(comment);

        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public ResponseEntity deleteCommentById(Long commentId){
        Comment comment = commentRepository.findById(commentId).get();
        Authentication auth = authImpl.getAuthentication();
        Long currUserId = userService.getUser(auth.getName()).getId();
        Long commentUserId = comment.getUser().getId();
        User user = userRepository.findById(comment.getUser().getId()).get();

        if(currUserId == commentUserId) {
            user.getComments().remove(comment);
        }else {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
