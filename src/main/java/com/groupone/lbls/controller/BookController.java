package com.groupone.lbls.controller;

import com.groupone.lbls.db.BookDAO;
import com.groupone.lbls.db.Query;
import com.groupone.lbls.model.Book;

import javax.swing.*;
import java.util.ArrayList;

public class BookController {

    private static BookController instance;

    public static BookController getInstance() {
        if (instance == null) {
            instance = new BookController();
        }
        return instance;
    }

    public boolean addBook(String ISBN, String title, String author,
                           String publisher, String genre, String keywords,
                           String quantity,String publisherYear){

        return Query.addBook(ISBN, title, author, publisher, genre, keywords,
                quantity, publisherYear);
    }

    public boolean updateBook(String id, String ISBN, String title, String author,
                              String publisher, String genre, String keywords,
                              String quantity, String publisherYear){

        return Query.updateBook(id, ISBN, title, author, publisher, genre,
                keywords, quantity, publisherYear);
    }

    public boolean deleteBook(String id) {
        return Query.deleteBook(id);
    }

    public Book getBook(String ISBN) {
        return Query.getBook(ISBN);
    }

    public ArrayList<Book> getAllBooks() {
        return Query.getAllBooks();
    }

    public boolean checkQuantityFormat(String quantity) {

        return quantity.trim().matches("[0-9]+");
    }

    public boolean checkFields(String ISBNfield, String titleField, String authorField,
                               String publisherField, String genreField,
                               String keywordsField, String quantityField) {

        return !(ISBNfield.isEmpty() ||
                titleField.isEmpty() ||
                authorField.isEmpty() ||
                publisherField.isEmpty() ||
                genreField.isEmpty() ||
                keywordsField.isEmpty() ||
                quantityField.isEmpty());
    }

    public void setDefaultFieldValues(JTextField ISBNfield, JTextField titleField,
                                      JTextField authorField, JTextField publisherField,
                                      JTextField genreField, JTextField keywordsField,
                                      JTextField quantityField) {
        ISBNfield.setText("");
        titleField.setText("");
        authorField.setText("");
        publisherField.setText("");
        genreField.setText("");
        keywordsField.setText("");
        quantityField.setText("");
    }

    public Object[][] searchBook(String ISBN, String title, String author,
                                 String publisher, String genre, String keywords) {
        BookDAO bookSearcher = new BookDAO();
        bookSearcher.getListOfBooks(ISBN, title, author, publisher, genre, keywords);

        return bookSearcher.getRowData();
    }

    public boolean addWaitList(int user_id, int book_id) {

        return Query.addWaitList(user_id, book_id);
    }

    public int getTakenBookCount(int book_id) {

        return Query.getTakenBookCount(book_id);
    }

    public boolean getWaitListBook(int user_id, int book_id) {
        return Query.getWaitListBook(user_id, book_id);
    }

    public int getWaitListBookCount(int book_id) {

        return Query.getWaitListBookCount(book_id);
    }
}
