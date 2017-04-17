package com.groupone.lbls.db;

import com.groupone.lbls.model.User;
import com.groupone.lbls.model.UserRole;
import com.groupone.lbls.model.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Query {

    private static final String userTable = "user";
    private static final String bookTable = "book";

    public static User getUser(String username, String password) {
        PreparedStatement statement;
        String query = String.format("SELECT * FROM %s WHERE username=? AND password=?", userTable);

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

    public static boolean addBook(String ISBN, String title, String author,
                               String publisher, String genre, String keywords, String quantity){

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
            statement.setString(8,"");

            statement.execute();
            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean updateBook(String id, String ISBN, String title, String author,
                                  String publisher, String genre, String keywords, String quantity){

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

    public static ArrayList<Book> getAllBooks(){

        PreparedStatement statement;
        String query = String.format("SELECT * FROM %s", bookTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            int id, quantity;
            String ISBN, title, author, publisher, genre, keywords;
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
                book=new Book(id, ISBN, title, author, quantity, publisher, genre, keywords);
                books.add(book);
            }

            return books;

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

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

            return new Book(id, ISBN, title, author, quantity, publisher, genre, keywords);

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
