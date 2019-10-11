package com.project.notreddit.Controllers;


import com.project.notreddit.Models.UserProfile;
import com.project.notreddit.Services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(maxAge = 12*60*60)
@RestController
public class UserProfileController {
    @Autowired
    UserProfileService userProfileService;

    @Autowired
    public void setUserProfileService(UserProfileService userProfileService){
        this.userProfileService = userProfileService;
    }

    @GetMapping("/profile")
    public UserProfile getUserProfile() {
        return userProfileService.getUserProfile();
    }

    @PutMapping("/profile")
    public UserProfile updateUserProfile(@RequestBody UserProfile userProfile) {
        return userProfileService.updateUserProfile(userProfile);
    }
}