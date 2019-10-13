//package com.project.notreddit.controller;
//
//import com.project.notreddit.Config.IAuthentication;
//import com.project.notreddit.Controllers.PostController;
//import com.project.notreddit.Models.Post;
//import com.project.notreddit.Repositories.PostRepository;
//import com.project.notreddit.Repositories.UserRepository;
//import com.project.notreddit.Services.PostService;
//import com.project.notreddit.Services.UserService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(PostController.class)
//public class PostControllerTest {
//    @MockBean
//    PostService postService;
//
//    @MockBean
//    UserService userService;
//
//    @MockBean
//    UserRepository userRepository;
//
//    @MockBean
//    PostRepository postRepository;
//
//    @MockBean
//    IAuthentication authImpl;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void post_success() throws Exception{
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/post")
////                .content(createPostInJson("test me", "testing"));
//                .content(asJsonString(new Post("posting", "posting")))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.postsId").exists());
//    }
//
////        when(postService.createPost(any())).thenReturn("123456");
//////        when(postService.createPost(any())).thenReturn("123456");
////
////        MvcResult result = mockMvc.perform(requestBuilder)
////                .andExpect(status().isCreated())
////                .andExpect(content().json("{\"token\":\"123456\"}"))
////                .andReturn();
////
////        System.out.println(result.getResponse().getContentAsString());
////    }
//
//    private static String createPostInJson (String postTitle, String postBody) {
//        return "{ \"postTitle\": \"" + postTitle + "\", " +
//                "\"postBody\":\"" + postBody + "\"}";
//    }
//
//}
