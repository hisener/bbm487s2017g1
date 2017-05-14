package com.groupone.lbls.db;

import com.groupone.lbls.model.User;
import com.groupone.lbls.model.UserRole;
import com.groupone.lbls.model.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Query {

    private static final String userTable = "user";
    private static final String bookTable = "book";
    private static final String loanTable = "loan";
    private static final String fineTable = "fine";
    private static final String waitListTable = "waitlist";

    /*
     * Gets user with given username
     */
    public static User getUser(String username) {
        PreparedStatement statement;
        String query = String.format("SELECT * FROM %s WHERE BINARY " +
                "username = ?", userTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            // check isEmpty
            if (!resultSet.next()) {
                return null;
            }

            int id = resultSet.getInt("id");
            String email = resultSet.getString("email");
            UserRole userRole = UserRole.fromInt(resultSet.getInt("role"));
            return new User(id, email, username, userRole);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * Gets user with given username and password. 
     */
    public static User getUser(String username, String password) {
        PreparedStatement statement;
        String query = String.format("SELECT * FROM %s WHERE BINARY " +
                "username = ? AND password = ?", userTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            // check isEmpty
            if (!resultSet.next()) {
                return null;
            }

            int id = resultSet.getInt("id");
            String email = resultSet.getString("email");
            UserRole userRole = UserRole.fromInt(resultSet.getInt("role"));
            return new User(id, email, username, userRole);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * Creates book with given information
     */
    public static boolean addBook(String ISBN, String title, String author,
                               String publisher, String genre, String keywords, String quantity,String publisherYear){

        PreparedStatement statement;
        String query = String.format("INSERT INTO %s " +
                "(ISBN, title, author, publisher, genre, keywords, quantity, publish_year) "+
                "values(?, ?, ?, ?, ?, ?, ?,?)", bookTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setString(1,ISBN);
            statement.setString(2,title);
            statement.setString(3,author);
            statement.setString(4,publisher);
            statement.setString(5,genre);
            statement.setString(6,keywords);
            statement.setString(7,quantity);
            statement.setString(8,publisherYear);

            statement.execute();
            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /*
     * Changes book information with given fields. ID cannot be changed. ID is used
     * to update fields.
     */
    public static boolean updateBook(String id, String ISBN, String title, String author,
                                  String publisher, String genre, String keywords, String quantity,String publisherYear){

        PreparedStatement statement;
        String query = String.format("UPDATE %s SET " +
                "ISBN = IFNULL(?, ISBN), " +
                "title = IFNULL(?, title), " +
                "author= IFNULL(?, author), " +
                "publisher = IFNULL(?, publisher), " +
                "genre = IFNULL(?, genre), " +
                "keywords = IFNULL(?, keywords), " +
                "quantity = IFNULL(?, quantity) " +
                "WHERE id = ?", bookTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setString(1,ISBN);
            statement.setString(2,title);
            statement.setString(3,author);
            statement.setString(4,publisher);
            statement.setString(5,genre);
            statement.setString(6,keywords);
            statement.setString(7,quantity);
            statement.setString(8,id);

            statement.execute();
            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /*
     * Deletes book with given ID
     */
    public static boolean deleteBook(String id){
        PreparedStatement statement;
        String query = String.format("DELETE FROM %s WHERE id = ?", bookTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setString(1,id);

            statement.execute();
            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * Gets all books in the library and returns a list.
     */
    public static ArrayList<Book> getAllBooks(){

        PreparedStatement statement;
        String query = String.format("SELECT * FROM %s", bookTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            int id, quantity;
            String ISBN, title, author, publisher, publisherYear, genre, keywords;
            Book book;
            ArrayList<Book> books = new ArrayList<>();

            while(resultSet.next()){
                id=resultSet.getInt("id");
                ISBN=resultSet.getString("ISBN");
                title=resultSet.getString("title");
                author=resultSet.getString("author");
                quantity=resultSet.getInt("quantity");
                publisher=resultSet.getString("publisher");
                genre=resultSet.getString("genre");
                keywords=resultSet.getString("keywords");
                publisherYear=resultSet.getString("publish_year");

                book = new Book(id, ISBN, title, author, quantity, publisher, genre, keywords,publisherYear);

                books.add(book);
            }

            return books;

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * Returns book with given ISBN
     */
    public static Book getBook(String ISBN){

        PreparedStatement statement;
        String query = String.format("SELECT * FROM %s WHERE ISBN=?", bookTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setString(1,ISBN);
            ResultSet resultSet= statement.executeQuery();

            // check isEmpty
            if (!resultSet.next()) {
                return null;
            }


            int id=resultSet.getInt("id");
            String title=resultSet.getString("title");
            String author=resultSet.getString("author");
            int quantity=resultSet.getInt("quantity");
            String publisher=resultSet.getString("publisher");
            String genre=resultSet.getString("genre");
            String keywords=resultSet.getString("keywords");
            String publisherYear=resultSet.getString("publish_year");

            return new Book(id, ISBN, title, author, quantity, publisher, genre, keywords,publisherYear);

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * Creates user with given information.
     */
    public static boolean addUser(String email, String username,
                                  String password, String userRole) {
        PreparedStatement statement;
        String query = String.format("INSERT INTO %s " +
                "(email, username, password, role) " +
                "values (?, ?, ?, ?)", userTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, userRole);

            statement.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * Updates user fields with given id. ID cannot be changed.
     */
    public static boolean updateUser(String id, String email, String username,
                                     String password, String userRole) {
        PreparedStatement statement;
        String query = String.format("UPDATE %s SET " +
                "email = IFNULL(?, email), " +
                "username = IFNULL(?, username), " +
                "password = IFNULL(?, password), " +
                "role = IFNULL(?, role) " +
                "WHERE id = ?", userTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, userRole);
            statement.setString(5, id);

            statement.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * Deletes user with given id. Returns true if deletion is completed successfully.
     */
    public static boolean deleteUser(String id) {
        PreparedStatement statement, statement_deleteBook;
        String query = String.format("DELETE FROM %s WHERE id = ?", userTable);
        String query_deleteBook = String.format("DELETE FROM %s WHERE borrower_id = ? AND return_date IS NULL", loanTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setString(1, id);
            
            statement_deleteBook = MySQL.getInstance().getConnection().prepareStatement(query_deleteBook);
            statement_deleteBook.setString(1, id);
            
            statement.execute();
            statement_deleteBook.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    /*
     * Adds book to user with userId.
     */
    public static int selfCheckout(int userId, Book book) {
        PreparedStatement statement;
        String query = String.format("INSERT INTO %s " +
                "(borrower_id, book_id, taken_date) " +
                "values (?, ?, ?)", loanTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, book.getId());
            statement.setObject(3, new Timestamp(new Date().getTime()));

            statement.execute();
            return 1;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /*
     * Returns book to library from user with userId.
     */
    public static boolean selfReturn(int userId, Book book) {
        PreparedStatement statement;
        String query = String.format("UPDATE %s SET " +
                "return_date = ? " +
                "WHERE borrower_id = ? AND book_id = ? " +
                "AND return_date IS NULL", loanTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setObject(1, new Timestamp(new Date().getTime()));
            statement.setInt(2, userId);
            statement.setInt(3, book.getId());

            statement.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * Returns the taken date of book by user with userId
     */
    public static Date getTakenDate(int userId, int bookId) {
        PreparedStatement statement;
        String query = String.format("SELECT taken_date FROM %s " +
                "WHERE borrower_id = ? AND book_id = ? AND return_date IS NULL",
                loanTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, bookId);

            statement.execute();
            ResultSet resultSet = statement.executeQuery();

            // check isEmpty
            if (!resultSet.next()) {
                return null;
            }

            return resultSet.getDate("taken_date");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * Returns how many copies of the book is taken
     */
    public static int getTakenBookCount(int bookId) {
        PreparedStatement statement;
        String query = String.format("SELECT COUNT(*) FROM %s " +
                "WHERE book_id = ? AND return_date IS NULL " +
                "GROUP BY borrower_id", loanTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, bookId);

            statement.execute();
            ResultSet resultSet = statement.executeQuery();

            // check isEmpty
            if (!resultSet.next()) {
                return 0;
            }

            return resultSet.getInt("COUNT(*)");

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    /*
     * Allows user with userId to pay fine.
     */
    public static boolean payFine(int userId) {
        PreparedStatement statement;
        String query = String.format("UPDATE %s SET " +
                "amount = 0 " +
                "WHERE user_id = ?", fineTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, userId);

            statement.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * Issues user fine with given amount
     */
    public static boolean issueLateFine(int userId, int amount) {
        PreparedStatement statement;
        String query = String.format("INSERT INTO %s " +
                "(user_id, amount) " +
                "values (?, ?)", fineTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, amount);

            statement.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * Adds user to book's waitlist
     */
    public static boolean addWaitList(int user_id, int book_id){

        PreparedStatement statement;
        String query = String.format("INSERT INTO %s " +
                "(user_id, book_id, added_date) "+
                "values(?, ?, ?)", waitListTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1,user_id);
            statement.setInt(2,book_id);
            statement.setObject(3,new Timestamp(new Date().getTime()));

            statement.execute();
            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /*
     * Removes user with userID from waitlist of book with book_id 
     */
    public static boolean deleteWaitListBook(int userId, int book_id){
        PreparedStatement statement;
        String query = String.format("DELETE FROM %s WHERE user_id=? AND book_id=?", waitListTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1,userId);
            statement.setInt(2,book_id);

            statement.execute();
            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public static boolean getWaitListBook(int user_id, int book_id){

        PreparedStatement statement;
        String query = String.format("SELECT * FROM %s WHERE user_id=? AND book_id=?", waitListTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1,user_id);
            statement.setInt(2,book_id);
            ResultSet resultSet= statement.executeQuery();

            // check isEmpty
            if (!resultSet.next()) {
                return false;
            }

            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getWaitListBookCount(int bookId) {
        PreparedStatement statement;
        String query = String.format("SELECT COUNT(*) FROM %s " +
                "WHERE book_id = ? " +
                "GROUP BY user_id", waitListTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, bookId);

            statement.execute();
            ResultSet resultSet = statement.executeQuery();

            // check isEmpty
            if (!resultSet.next()) {
                return 0;
            }

            return resultSet.getInt("COUNT(*)");

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
