package com.groupone.lbls.model;

public class Book {
	private int id;
    private String ISBN;
    private String title;
    private String author;
    private String genre;
    private String publisher;
    private String publisherYear;
    private int quantity;
    private String keywords;

	public Book(int id, String ISBN, String title, String author, String genre, String publisher, String publisherYear,
			int quantity, String keywords) {
		super();
		this.id = id;
		this.ISBN = ISBN;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.publisher = publisher;
		this.publisherYear = publisherYear;
		this.quantity = quantity;
		this.keywords = keywords;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublisherYear() {
		return publisherYear;
	}
	public void setPublisherYear(String publisherYear) {
		this.publisherYear = publisherYear;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}


}
