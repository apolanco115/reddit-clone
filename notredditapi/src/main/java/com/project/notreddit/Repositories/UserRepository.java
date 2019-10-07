package com.project.notreddit.Repositories;

import com.project.notreddit.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    public User findByUsername(String username);

}
