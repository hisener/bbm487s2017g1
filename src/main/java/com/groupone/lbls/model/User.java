package com.groupone.lbls.model;

public class User {

    private int id;
    private String email;
    private String username;
    private UserRole role;

    public User(int id, String email, String username, UserRole role) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.role = role;
    }

    public UserRole getRole() {
        return role;
    }
}
