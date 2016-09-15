package com.example.jq.setupapirequest.models;

/**
 * Created by ctmanalo on 9/15/16.
 */
public class AuthBody {

    private String username;
    private String password;

    public AuthBody(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
