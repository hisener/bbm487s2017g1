package com.groupone.lbls.controller;

import com.groupone.lbls.db.Query;
import com.groupone.lbls.model.Book;
import com.groupone.lbls.model.User;
import com.groupone.lbls.model.UserRole;

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

        String encryptedPassword;
        try {
            encryptedPassword = this.encryptPassword(password);
        } catch (Exception e) {
            throw new Exception("Internal error.");
        }

        User user = Query.getUser(username, encryptedPassword);

        if (user == null) {
            throw new Exception("Username/password is wrong.");
        }

        return user;
    }

    public User getUser(String username) {
        return Query.getUser(username);
    }

    public boolean addUser(String email, String username, String password,
                           UserRole userRole) {
        String encryptedPassword;
        try {
            encryptedPassword = this.encryptPassword(password);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return Query.addUser(email, username, encryptedPassword,
                String.valueOf(userRole.ordinal()));
    }

    public boolean updateUser(String id, String email, String username,
                              String password, UserRole userRole) {
        String encryptedPassword;
        try {
            encryptedPassword = this.encryptPassword(password);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if (email.isEmpty())    email = null;
        if (username.isEmpty()) username = null;

        return Query.updateUser(id, email, username, encryptedPassword,
                String.valueOf(userRole.ordinal()));
    }

    public boolean deleteUser(String id) {
        return Query.deleteUser(id);
    }

    public boolean selfCheckout(int userId, String ISBN) throws Exception {
        Book book = BookController.getBook(ISBN);
        if (book == null) {
            throw new Exception("The book could not find.");
        }

        if (Query.isUserTookBook(userId, book.getId())) {
            throw new Exception("You have already taken the book.");
        }

        if (!book.isBookAvailable()) {
            throw new Exception("The book is not available at the moment.");
        }

        return Query.selfCheckout(userId, book);
    }

    public boolean selfReturn(int userId, String ISBN) throws Exception {
        Book book = BookController.getBook(ISBN);
        if (book == null) {
            throw new Exception("The book could not find.");
        }

        if (!Query.isUserTookBook(userId, book.getId())) {
            throw new Exception("You have not taken the book.");
        }

        return Query.selfReturn(userId, book);
    }

    private String encryptPassword(String password) throws Exception {
        if (password.isEmpty()) {
            return null;
        }

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(password.getBytes("UTF-8"));
        byte[] digest = messageDigest.digest();

        return String.format("%064x", new java.math.BigInteger(1, digest));
    }
}
