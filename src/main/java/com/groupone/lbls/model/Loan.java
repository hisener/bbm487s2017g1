package com.groupone.lbls.model;

import java.util.Date;
import java.sql.Timestamp;

public class Loan {
    private int id;
    private int borrower_id;
    private int book_id;
    private Date taken_date;
    private Date return_date;
    
    
	public Loan(int id, int borrower_id, int book_id, Date taken_date, Date return_date) {
		this.id = id;
		this.borrower_id = borrower_id;
		this.book_id = book_id;
		this.taken_date = taken_date;
		this.return_date = return_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBorrower_id() {
		return borrower_id;
	}

	public void setBorrower_id(int borrower_id) {
		this.borrower_id = borrower_id;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public Date getTaken_date() {
		return taken_date;
	}

	public void setTaken_date(Date taken_date) {
		this.taken_date = taken_date;
	}

	public Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
    
}

