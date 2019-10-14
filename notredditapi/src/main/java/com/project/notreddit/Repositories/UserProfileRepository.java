package com.project.notreddit.Repositories;

import com.project.notreddit.Models.UserProfile;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends CrudRepository<UserProfile,Long> {

}