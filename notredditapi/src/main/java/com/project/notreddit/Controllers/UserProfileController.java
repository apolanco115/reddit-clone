package com.project.notreddit.Controllers;


import com.project.notreddit.Models.UserProfile;
import com.project.notreddit.Services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserProfileController {
    @Autowired
    UserProfileService userProfileService;

    @Autowired
    public void setUserProfileService(UserProfileService userProfileService){
        this.userProfileService = userProfileService;
    }

    @GetMapping("/profile/{username}")
    public UserProfile getUserProfile(@PathVariable String username) {
        return userProfileService.getUserProfile(username);
    }

    @PutMapping("/profile")
    public UserProfile updateUserProfile(@RequestBody UserProfile userProfile) {
        return userProfileService.updateUserProfile(userProfile);
    }
}