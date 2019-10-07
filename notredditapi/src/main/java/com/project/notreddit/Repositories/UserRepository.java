package com.project.notreddit.Repositories;

import com.project.notreddit.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    public User findByUsername(String username);

    @Query("FROM User u WHERE u.username = ?1 AND u.password=?2")
    public User login(String username, String password);

}
