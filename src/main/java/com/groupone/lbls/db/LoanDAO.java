package com.groupone.lbls.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.groupone.lbls.model.Book;
import com.groupone.lbls.model.Loan;

/*
 * Loan Database Access Object
 * Enables application to create a connection with 'loan' table of database
 */
public class LoanDAO {
	private ArrayList<Loan> loans = new ArrayList<>();

	private Object[][] booksInformation;	
	
	private final String table = "loan";
	private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/*
	 * Saves every book that user currently owns to "loans" list.
	 */
	public void getUserBooks(int userId)
	{
		PreparedStatement statement;
        String query = String.format("SELECT * FROM %s WHERE borrower_id = ? AND return_date IS NULL", table);
        
        try
        {
        	statement = MySQL.getInstance().getConnection().prepareStatement(query);
        	statement.setInt(1, userId);
            
        	ResultSet resultSet = statement.executeQuery();
        	      	
        	while (resultSet.next()) {
            	int t_id = resultSet.getInt("id");
            	int t_borrower_id = resultSet.getInt("borrower_id");
            	int t_book_id 	 = resultSet.getInt("book_id");
            	String t_taken_date  = resultSet.getString("taken_date");
            	//String t_return_date = resultSet.getString("return_date");
            	
            	Date taken_date = dateFormat.parse(t_taken_date);          	
                loans.add(new Loan(t_id, t_borrower_id, t_book_id, taken_date, null));
            }
        	
        	if(!loans.isEmpty())
            {
            	booksInformation = GetLoanedBooks();
            }
        	   
        }catch (SQLException e) {
        	e.printStackTrace();
            return;
		} catch (ParseException e) {
			e.printStackTrace();
			return;
		}
	}
	
	/*
	 * Gets previously owned book by user with userId and saves them to "loans" list
	 */
	public void getUserHistory(int userId)
	{
		PreparedStatement statement;
        String query = String.format("SELECT * FROM %s WHERE borrower_id = ? AND return_date IS NOT NULL", table);
        
        try
        {
        	statement = MySQL.getInstance().getConnection().prepareStatement(query);
        	statement.setInt(1, userId);
            
        	ResultSet resultSet = statement.executeQuery();
        	      	
        	while (resultSet.next()) {
            	int t_id = resultSet.getInt("id");
            	int t_borrower_id = resultSet.getInt("borrower_id");
            	int t_book_id 	 = resultSet.getInt("book_id");
            	String t_taken_date  = resultSet.getString("taken_date");
            	String t_return_date = resultSet.getString("return_date");
            	
            	Date taken_date = dateFormat.parse(t_taken_date);
            	Date return_date = dateFormat.parse(t_return_date);
                loans.add(new Loan(t_id, t_borrower_id, t_book_id, taken_date, return_date));
            }
        	
        	if(!loans.isEmpty())
            {
            	booksInformation = GetHistoryBooks();
            }
        	   
        }catch (SQLException e) {
        	e.printStackTrace();
            return;
		} catch (ParseException e) {
			e.printStackTrace();
			return;
		}
	}
	
	/*
	 * Converts loaned books to string to display them in tables.
	 */
	private Object[][] GetLoanedBooks()
	{
		Object[][] tempRowData = new Object[loans.size()][6];
		BookDAO tempAccessor = new BookDAO();
		for(int i = 0; i < loans.size(); i++)
		{
			Book tempBook = tempAccessor.getBookByID(loans.get(i).getBook_id());
			if(tempBook == null)
			{
				tempRowData[i] = new Object[]{"Book deleted.","NULL","NULL","NULL","NULL","NULL"};
			}
			else
			{
				tempRowData[i][0] = tempBook.getTitle();
				tempRowData[i][1] = tempBook.getISBN();
				tempRowData[i][2] = tempBook.getAuthor();
				tempRowData[i][3] = tempBook.getGenre();
				tempRowData[i][4] = dateFormat.format(loans.get(i).getTaken_date()); 
				
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(loans.get(i).getTaken_date());
				cal.add(Calendar.MONTH, 1);
				Date nextMonth = cal.getTime();
				
				tempRowData[i][5] = dateFormat.format(nextMonth);
			}

		}
		
		return tempRowData;
	}

	/*
	 * Converts previously owned books to string to display them in tables.
	 */
	private Object[][] GetHistoryBooks()
	{
		Object[][] tempRowData = new Object[loans.size()][6];
		BookDAO tempAccessor = new BookDAO();
		for(int i = 0; i < loans.size(); i++)
		{
			Book tempBook = tempAccessor.getBookByID(loans.get(i).getBook_id());
			if(tempBook == null)
			{
				tempRowData[i] = new Object[]{"Book deleted.","NULL","NULL","NULL","NULL","NULL"};
			}
			else
			{
				tempRowData[i][0] = tempBook.getTitle();
				tempRowData[i][1] = tempBook.getISBN();
				tempRowData[i][2] = tempBook.getAuthor();
				tempRowData[i][3] = tempBook.getGenre();
				tempRowData[i][4] = dateFormat.format(loans.get(i).getTaken_date()); 		
				tempRowData[i][5] = dateFormat.format(loans.get(i).getReturn_date());
			}
		}
		return tempRowData;
	}
	
	public Object[][] getRowData() {
		return booksInformation;
	}

	public int getUsersBookCount() {
        return this.loans.size();
    }

}
