package com.project.notreddit.Services;

import com.project.notreddit.Models.User;
import com.project.notreddit.Models.UserProfile;
import com.project.notreddit.Repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileImpl implements UserProfileService{
    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserService userService;

    @Override
    public UserProfile createUserProfile(String username, UserProfile newProfile){
        User user = userService.getUser(username);
        user.setUserProfile(newProfile);
        return userService.createUser(user).getUserProfile();
    }

}
