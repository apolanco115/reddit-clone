package com.project.notreddit.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String mobile;

    @JsonIgnore
    @OneToOne(mappedBy = "userProfile",
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    private User user;

    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }

    public UserProfile (){}

    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getMobile(){
        return mobile;
    }
    public void setMobile(String mobile){
        this.mobile = mobile;
    }
}
