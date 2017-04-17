package com.groupone.lbls.controller;

import com.groupone.lbls.db.Query;
import com.groupone.lbls.model.Book;

import javax.swing.*;
import java.util.ArrayList;


public class BookController {

    public static boolean addBook(String ISBN, String title, String author,
                        String publisher, String genre, String keywords, String quantity){

        return Query.addBook(ISBN, title, author, publisher, genre, keywords, quantity);
    }

    public static boolean updateBook(String id, String ISBN, String title, String author,
                           String publisher, String genre, String keywords, String quantity){

        return Query.updateBook(id, ISBN, title, author, publisher, genre, keywords, quantity);
    }

    public static  boolean deleteBook(String id){
        return Query.deleteBook(id);
    }

    public static Book getBook(String ISBN){
        return Query.getBook(ISBN);
    }

    public static ArrayList<Book> getAllBooks(){
        return Query.getAllBooks();
    }

    public static boolean checkQuantityFormat(String quantity){

        boolean check2=quantity.trim().matches("[0-9]+");

        return check2;
    }

    public static boolean checkFields(String ISBNfield, String titleField, String authorField,
                               String publisherField, String genreField, String keywordsField, String quantityField){

        return !(ISBNfield.isEmpty() ||
                titleField.isEmpty() ||
                authorField.isEmpty() ||
                publisherField.isEmpty() ||
                genreField.isEmpty() ||
                keywordsField.isEmpty() ||
                quantityField.isEmpty());
    }

    public static void setDefaultFieldValues(JTextField ISBNfield, JTextField titleField, JTextField authorField,
                                      JTextField publisherField, JTextField genreField, JTextField keywordsField, JTextField quantityField){
        ISBNfield.setText("");
        titleField.setText("");
        authorField.setText("");
        publisherField.setText("");
        genreField.setText("");
        keywordsField.setText("");
        quantityField.setText("");
    }
}
