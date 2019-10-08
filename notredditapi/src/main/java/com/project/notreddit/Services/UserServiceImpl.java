package com.project.notreddit.Services;

import com.project.notreddit.Config.JwtUtil;
import com.project.notreddit.Models.Comment;
import com.project.notreddit.Models.Post;
import com.project.notreddit.Models.User;
import com.project.notreddit.Repositories.UserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserProfileService userProfileService;


    @Autowired
    @Qualifier("encoder")
    PasswordEncoder bCryptPasswordEncoder;

    @Override
    public Iterable<User> listUsers(){
        return userRepository.findAll();
    }

    @Override
    public Iterable<Post> listUserPosts(String username){
        User user = getUser(username);
        return user.getPosts();
    }

    @Override
    public Iterable<Comment> listUserComments(String username){
        User user = getUser(username);
        return user.getComments();
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);

        if(user==null)
            throw new UsernameNotFoundException("User null");

        return new org.springframework.security.core.userdetails.User(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()),
                true, true, true, true, new ArrayList<>());
    }

    @Override
    public String createUser(User newUser){

        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

        if(userRepository.save(newUser) != null){
            UserDetails userDetails = loadUserByUsername(newUser.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        return null;
    }

    @Override
    public String login(User user){
        User newUser = userRepository.findByUsername(user.getUsername());

        if(newUser != null && bCryptPasswordEncoder.matches(user.getPassword(), newUser.getPassword())){
            UserDetails userDetails = loadUserByUsername(newUser.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        return null;
    }

}
