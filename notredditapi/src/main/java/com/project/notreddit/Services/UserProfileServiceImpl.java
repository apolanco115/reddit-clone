package com.project.notreddit.Services;


import com.project.notreddit.Config.IAuthentication;
import com.project.notreddit.Models.User;
import com.project.notreddit.Models.UserProfile;
import com.project.notreddit.Repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserService userService;
    @Autowired
    IAuthentication authImpl;

    @Override
    public UserProfile updateUserProfile(UserProfile newProfile) {
        Authentication auth = authImpl.getAuthentication();
        User user = userService.getUser(auth.getName());
        if(newProfile.getEmail()!=null){
            user.getUserProfile().setEmail(newProfile.getEmail());
        }
        if(newProfile.getMobile()!=null){
            user.getUserProfile().setMobile(newProfile.getMobile());
        }
        return userProfileRepository.save(user.getUserProfile());
    }

    @Override
    public UserProfile getUserProfile(String username) {
        return userProfileRepository.findProfileByUsername(username);
    }

}
