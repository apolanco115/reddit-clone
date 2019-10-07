package com.project.notreddit.Config;

public class JwtResponse {
    private String jwt;

    public JwtResponse(String jwt){
        this.jwt = jwt;
    }

    public String getToken(){
        return this.jwt;
    }
}