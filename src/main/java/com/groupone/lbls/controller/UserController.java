package com.groupone.lbls.controller;

import com.groupone.lbls.api.Payment;
import com.groupone.lbls.db.FineDAO;
import com.groupone.lbls.db.LoanDAO;
import com.groupone.lbls.db.Query;
import com.groupone.lbls.db.UserDAO;
import com.groupone.lbls.db.impl.FineDAOImpl;
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
    private UserDAO userDAO;
    private FineDAO fineDAO;

    /**
     * Constructor
     * Creates instances of Data Access Objects
     */
    public UserController() {
        this.userDAO = new UserDAOImpl();
        this.fineDAO = new FineDAOImpl();
    }

    /**
     * Get the single instance of the UserController class.
     * @return UserController object
     */
    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    /**
     * Authenticate the user who wants to log in.
     * @param username username field
     * @param password password field
     * @return User object
     * @throws Exception Throws Exception if encryption failed or user fields
     * are wrong.
     */
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

    /**
     * Get all users as a List.
     * @return List of User objects.
     * @see java.util.List
     * @see User
     */
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    /**
     * Get a User object with username.
     * @param username username field
     * @return User object.
     * @see Query#getUser(String)
     */
    public User getUser(String username) {
        return Query.getUser(username);
    }

    /**
     * Add a new user
     * @param email email field
     * @param username username field
     * @param password password field
     * @param userRole UserRole object
     * @return Returns true if the user is created correctly, otherwise false.
     * @see Query#addUser(String, String, String, String)
     */
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
        Book book = BookController.getInstance().getBook(ISBN);
        if (book == null) {
            throw new Exception("The book could not find.");
        }

        if (Query.getTakenDate(userId, book.getId()) != null) {
            throw new Exception("You have already taken the book.");
        }

        int book_count=book.getQuantity();
        int waitList_book_count=BookController.getInstance().getWaitListBookCount(book.getId());

        //check not available of book
        if (!book.isBookAvailable()) {
            return 2;
        }
        //or whether there are books in waiting list
        //and whether user has got book in waiting list.
        if (waitList_book_count>=book_count&&!(BookController.getInstance().getWaitListBook(userId,book.getId()))) {
            return 2;
        }

        if (UserController.getInstance().getUsersBookCount(userId) >= 5) {
            throw new Exception("You cannot take more books before return.");
        }

        //if book is waiting list
        if(BookController.getInstance().getWaitListBook(userId,book.getId())){
            //if book is deleted from waiting list
            if(Query.deleteWaitListBook(userId, book.getId())){
                return Query.selfCheckout(userId, book);
            }
            else{
                throw new Exception("The self-checkout operation" +
                        "could not be performed.");
            }
        }

        return Query.selfCheckout(userId, book);
    }

    public boolean getTakenDate(int userId, int book_id){
        return Query.getTakenDate(userId, book_id) == null;
    }

    public int getUsersBookCount(int userId) {
        LoanDAO loans = new LoanDAO();
        loans.getUserBooks(userId);
        return loans.getUsersBookCount();
    }

    public boolean selfReturn(int userId, String ISBN) throws Exception {
        Book book = BookController.getInstance().getBook(ISBN);
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

    public int getUsersFine(int userId) {
        return fineDAO.getUsersFine(userId);
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
