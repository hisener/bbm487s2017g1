package com.groupone.lbls.model;

public class Book {

    private int id;
    private String ISBN;
    private String title;
    private String author;
    private String genre;
    private String publisher;
    private String publish_year;
    private int quantity;
    private String keywords;

    public int getId() {
        return id;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getKeywords() {
        return keywords;
    }

    public Book(int id, String ISBN, String title, String author, int quantity, String publisher, String genre, String keywords){
        this.id=id;
        this.ISBN=ISBN;
        this.title=title;
        this.author=author;
        this.quantity=quantity;
        this.publisher=publisher;
        this.genre=genre;
        this.keywords=keywords;
    }
}
