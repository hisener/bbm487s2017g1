package com.groupone.lbls.controller;

import com.groupone.lbls.api.Payment;
import com.groupone.lbls.db.Query;
import com.groupone.lbls.db.UserDAO;
import com.groupone.lbls.db.impl.UserDAOImpl;
import com.groupone.lbls.model.Book;
import com.groupone.lbls.model.User;
import com.groupone.lbls.model.UserRole;

import java.security.MessageDigest;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    public List<User> getAllUsers() {
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.getAllUsers();
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

    public int selfCheckout(int userId, String ISBN) throws Exception {
        Book book = BookController.getBook(ISBN);
        if (book == null) {
            throw new Exception("The book could not find.");
        }

        if (Query.getTakenDate(userId, book.getId()) != null) {
            throw new Exception("You have already taken the book.");
        }

        int book_count=book.getQuantity();
        int waitList_book_count=BookController.getWaitListBookCount(book.getId());

        //check not available of book
        if (!book.isBookAvailable()) {
            return 2;
        }
        //or whether there are books in waiting list
        //and whether user has got book in waiting list.
        if ((waitList_book_count>=book_count&&!(BookController.getWaitListBook(userId,book.getId())))) {
            return 2;
        }

        // TODO: If the actor tries to borrow more than 5 books at once,
        // the system will not allow the user to do that.

        return Query.selfCheckout(userId, book);
    }

    public static boolean getTakenDate(int userId, int book_id){
        if (Query.getTakenDate(userId, book_id) != null){
            return false;
        }
        return true;
    }
    public boolean selfReturn(int userId, String ISBN) throws Exception {
        Book book = BookController.getBook(ISBN);
        if (book == null) {
            throw new Exception("The book could not find.");
        }

        Date takenDate = Query.getTakenDate(userId, book.getId());
        if (takenDate == null) {
            throw new Exception("You have not taken the book.");
        }

        long diffInMillis = new Date().getTime() - takenDate.getTime();
        long diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);

        if (diffInDays > 30) {
            Query.issueLateFine(userId, (int) (diffInDays - 30) * Constants.FINE_PER_DAY);
        }

        return Query.selfReturn(userId, book);
    }

    public boolean payFine(int userId, String cardNumber) throws Exception {
        if (!Payment.process(cardNumber)) {
            throw new Exception("Payment failed");
        }

        if (!Query.payFine(userId)) {
            throw new Exception("Database error.");
        }

        return true;
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
