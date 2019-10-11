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
            user.getUserProfile().setEmail(newProfile.getEmail().toString());
        }
        if(newProfile.getMobile()!=null){
            user.getUserProfile().setMobile(newProfile.getMobile().toString());
        }
        return userProfileRepository.save(user.getUserProfile());
    }

    @Override
    public UserProfile getUserProfile() {
        System.out.println("before auth");
        Authentication auth = authImpl.getAuthentication();
        System.out.println("after auth");
        User user = userService.getUser(auth.getName());
        System.out.println(user.getUsername());
        return userProfileRepository.findProfileByUsername(user.getUsername());
    }

}
