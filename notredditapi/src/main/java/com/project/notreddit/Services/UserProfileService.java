package com.project.notreddit.Services;

import com.project.notreddit.Models.UserProfile;

public interface UserProfileService {
    public UserProfile updateUserProfile(UserProfile newProfile);

    public UserProfile getUserProfile(String username);
}