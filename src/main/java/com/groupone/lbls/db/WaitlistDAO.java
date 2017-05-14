package com.groupone.lbls.db;

import java.awt.Dialog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.groupone.lbls.model.Book;
import com.groupone.lbls.model.Loan;

public class WaitlistDAO {
	
	private class WaitlistEntry
	{
		public int book_id;
		public String dateAdded;
		
		public WaitlistEntry(int book_id, String dateAdded) {
			this.book_id = book_id;
			this.dateAdded = dateAdded;
		}
	}
	
	private ArrayList<WaitlistEntry> bookEntriesOnWaitlist= new ArrayList<>();
	private ArrayList<Book> books = new ArrayList<>();
	
	private Object[][] rowData;
	
	private final String table = "waitlist";
	private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public boolean deleteWish(String ISBN)
	{
		Book tempBook = Query.getBook(ISBN);
		if(tempBook == null)
		{
			return false;
		}
		
		PreparedStatement statement;
        String query = String.format("DELETE FROM %s WHERE book_id=?;", table); //("SELECT * FROM %s WHERE user_id = ?", table);
        
        try
        {
        	statement = MySQL.getInstance().getConnection().prepareStatement(query);
        	statement.setInt(1, tempBook.getId());
            
        	statement.execute();
        	
        	return true;
        	   
        }catch (SQLException e) {
        	e.printStackTrace();
            return false;
		}        
	}
	
	public void getUserBookOnWaitlist(int userId)
	{
		PreparedStatement statement;
        String query = String.format("SELECT * FROM %s WHERE user_id = ?", table);
        
        try
        {
        	statement = MySQL.getInstance().getConnection().prepareStatement(query);
        	statement.setInt(1, userId);
            
        	ResultSet resultSet = statement.executeQuery();
        	      	
        	while (resultSet.next()) {
            	int t_book_id = resultSet.getInt("book_id");
            	String t_added_date  = resultSet.getString("added_date");
            	
            	Date taken_date = dateFormat.parse(t_added_date);          	
            	bookEntriesOnWaitlist.add(new WaitlistEntry(t_book_id, t_added_date));
            }
        	
        	if(!bookEntriesOnWaitlist.isEmpty())
            {
        		rowData = GetBooksOnWaitlist();
            }
        	   
        }catch (SQLException e) {
        	e.printStackTrace();
            return;
		} catch (ParseException e) {
			e.printStackTrace();
			return;
		}

	}
	
	private Object[][] GetBooksOnWaitlist()
	{
		Object[][] tempRowData = new Object[bookEntriesOnWaitlist.size()][3];
		BookDAO tempAccessor = new BookDAO();
		for(int i = 0; i < bookEntriesOnWaitlist.size(); i++)
		{
			Book tempBook = tempAccessor.getBookByID(bookEntriesOnWaitlist.get(i).book_id);
			if(tempBook == null)
			{
				tempRowData[i] = new Object[]{"Book deleted.","NULL","NULL"};
			}
			else
			{
				tempRowData[i][0] = tempBook.getTitle();
				tempRowData[i][1] = tempBook.getISBN();
				tempRowData[i][2] = bookEntriesOnWaitlist.get(i).dateAdded;
				books.add(tempBook);
			}
		}
		return tempRowData;
	}
	
	public Object[][] getRowData() {
		return rowData;
	}
	
	/** Returns books in the waitlist table **/
	public ArrayList<Book> getBooksOnWaitlist()
	{
		return books;
	}
}
