package com.groupone.lbls.controller;

import com.groupone.lbls.db.Query;
import com.groupone.lbls.model.User;

import java.security.MessageDigest;

public class UserController {

    private static UserController instance;

    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    public User authenticate(String username, String password) throws Exception {

        String encodedPassword;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes("UTF-8"));
            byte[] digest = messageDigest.digest();

            encodedPassword = String.format("%064x", new java.math.BigInteger(1, digest));
        } catch (Exception e) {
            throw new Exception("Internal error.");
        }

        User user = Query.getUser(username, encodedPassword);

        if (user == null) {
            throw new Exception("Username/password is wrong.");
        }

        return user;
    }

}
