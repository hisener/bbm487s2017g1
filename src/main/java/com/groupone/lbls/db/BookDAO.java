package com.groupone.lbls.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.groupone.lbls.model.Book;

/*
 * Book Database Access Object
 * Enables application to create a connection with 'book' table of database
 */
public class BookDAO {
	private ArrayList<Book> books = new ArrayList<>();
	private Object[][] rowData;

	private final String table = "book";
	
	/*
	 * Returns book with given bookId
	 */
	public Book getBookByID(int bookId)
	{
		 PreparedStatement statement;
	     String query = String.format("SELECT * FROM %s WHERE id = ?", table);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1,bookId);
            ResultSet resultSet= statement.executeQuery();

            // check isEmpty
            if (!resultSet.next()) {
                return null;
            }

            int id=resultSet.getInt("id");
            String ISBN = resultSet.getString("ISBN");
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
	 * Takes empty or non-empty fields for books. Creates appropriate search query by
	 * neglecting empty fields. Optimizes query by adding likeliness to fields.
	 * Returns list of books created fields. 
	 * Used in search for books
	 */
    public void getListOfBooks(String isbn, String title, String author, String publisher, 
    		String genre, String keywords)
    {
    	String columnNames[] = {isbn, title, author, publisher, genre, keywords};
    	int validAreas[] = {-1, -1, -1, -1, -1, -1, -1};
    	int currentIndex = 1;
    	
        PreparedStatement statement;
        String query = String.format("SELECT * FROM %s WHERE 1=1 ", table);
        
        try {           
            if(isbn != null && !isbn.equals(""))
            {
            	query += "AND ISBN=? ";
            	validAreas[0] = currentIndex++;
            }
            
            if(title != null && !title.equals(""))
            {
            	//query += "AND title=? ";
            	query += "AND title LIKE ? ";
            	validAreas[1] = currentIndex++;
            }
            
            if(author != null && !author.equals(""))
            {
            	//query += "AND author=? ";
            	query += "AND author LIKE ? ";
            	validAreas[2] = currentIndex++;
            }
            
            if(publisher != null && !publisher.equals(""))
            {
            	//query += "AND publisher=? ";
            	query += "AND publisher LIKE ? ";
            	validAreas[3] = currentIndex++;
            } 
            
            if(genre != null && !genre.equals(""))
            {
            	//query += "AND genre=? ";
            	query += "AND genre LIKE ? ";
            	validAreas[4] = currentIndex++;
            } 
            
            if(keywords != null && !keywords.equals(""))
            {
            	//query += "AND keywords=? ";
            	query += "AND keywords LIKE ? ";
            	validAreas[5] = currentIndex++;
            }
            
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
                        
            if(validAreas[0] != -1)
            {
            	statement.setString(validAreas[0], columnNames[0]);
            }
            
            for(int i=1; i < 6; i++)
            {
            	if(validAreas[i] != -1)
            	{
            		statement.setString(validAreas[i], "%" + columnNames[i] + "%");
            	}
            }
            
            ResultSet resultSet = statement.executeQuery();
                       
            while (resultSet.next()) {
        		
            	String t_ISBN 		= resultSet.getString("ISBN");
            	String t_title 		= resultSet.getString("title");
            	String t_author 	= resultSet.getString("author");
            	String t_publisher 	= resultSet.getString("publisher");
            	String t_genre 		= resultSet.getString("genre");
            	String t_keywords 	= resultSet.getString("keywords");
            	
                books.add(new Book(0, t_ISBN, t_title, t_author, 0, t_publisher, t_genre, t_keywords,"0"));
            }

            if(!books.isEmpty())
            {
            	rowData = ConvertBooksToString();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
    
    /*
     * After taking books, converts them to string to display them in tables.
     */
    private Object[][] ConvertBooksToString()
    {
    	Object[][] tempRowData = new Object[books.size()][6];
    	for(int i = 0; i < books.size(); i++)
    	{
    		tempRowData [i][0] = books.get(i).getISBN();
    		tempRowData [i][1] = books.get(i).getTitle();
    		tempRowData [i][2] = books.get(i).getAuthor();
    		tempRowData [i][3] = books.get(i).getGenre();
    		tempRowData [i][4] = books.get(i).getPublisher();
    		tempRowData [i][5] = books.get(i).getKeywords();
    	}
    	return tempRowData ;
    }
    	
	public ArrayList<Book> getBooks() {
		return books;
	}

	public Object[][] getRowData() {
		return rowData;
	}
		
}
