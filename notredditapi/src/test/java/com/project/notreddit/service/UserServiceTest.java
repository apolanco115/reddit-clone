package com.project.notreddit.service;

import com.project.notreddit.Config.JwtUtil;
import com.project.notreddit.Models.User;
import com.project.notreddit.Repositories.PostRepository;
import com.project.notreddit.Repositories.UserRepository;
import com.project.notreddit.Services.PostService;
import com.project.notreddit.Services.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private User user;

    @Before
    public void initializeDummyUser(){
        user.setUsername("tom");
        user.setPassword("jerry");
    }

    @Test
    public void getUser_ReturnsUser_Success(){

        when(userRepository.findByUsername(anyString())).thenReturn(user);

        User tempUser = userService.getUser(user.getUsername());

        assertEquals(tempUser.getUsername(), user.getUsername());
    }

    @Test
    public void login_UserNotFound_Error(){

        when(userRepository.findByUsername(anyString())).thenReturn(null);

        String token = userService.login(user);

        assertEquals(token, null);
    }
}