package com.project.notreddit.Services;

import com.project.notreddit.Models.UserProfile;

public interface UserProfileService {
    public UserProfile createUserProfile(String username, UserProfile newProfile);

    public UserProfile getUserProfile(String username);
}