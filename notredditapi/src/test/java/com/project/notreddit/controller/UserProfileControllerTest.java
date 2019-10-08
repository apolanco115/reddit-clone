package com.project.notreddit.controller;

import com.project.notreddit.Controllers.UserProfileController;
import com.project.notreddit.Models.UserProfile;
import com.project.notreddit.service.UserProfileServiceStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserProfileControllerTest {

    private UserProfileController userProfileController;

    @Before
    public void initializeUserProfileController(){
        userProfileController = new UserProfileController();
        userProfileController.setUserProfileService(new UserProfileServiceStub());
    }

    @Test
    public void createUserProfile_SaveUserProfileSuccess(){
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail("hey@heythere.com");
        userProfile.setMobile("7294092344");

        UserProfile newProfile = userProfileController.createUserProfile("hey", userProfile);

        Assert.assertNotNull(newProfile);
        Assert.assertEquals(newProfile.getEmail(),userProfile.getEmail());
        Assert.assertEquals(newProfile.getMobile(),userProfile.getMobile());
    }
}
