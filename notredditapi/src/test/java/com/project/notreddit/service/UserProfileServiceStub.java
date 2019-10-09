package com.project.notreddit.service;

import com.project.notreddit.Models.UserProfile;
import com.project.notreddit.Services.UserProfileService;

public class UserProfileServiceStub implements UserProfileService {

    @Override
    public UserProfile updateUserProfile(UserProfile newProfile){
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail("hey@heythere.com");
        userProfile.setMobile("7294092344");

        return userProfile;
    }

    @Override
    public UserProfile getUserProfile(String username){
        return null;
    }
}
