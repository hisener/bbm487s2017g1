package com.groupone.lbls.views;
import com.groupone.lbls.controller.BookController;
import com.groupone.lbls.model.Book;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

public class LibrarianAllBooks {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
          java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibrarianAllBooks window = new LibrarianAllBooks();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LibrarianAllBooks() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Library Book Loan System - View All Books");
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblAllBooks = new JLabel("All Books");
		lblAllBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblAllBooks.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblAllBooks.setBounds(10, 40, 564, 22);
		panel.add(lblAllBooks);
		
		JLabel label_1 = new JLabel("Library Book Loan System");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		label_1.setBounds(10, 11, 564, 25);
		panel.add(label_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 73, 564, 257);
		panel.add(scrollPane);

		String[] columnNames= { "Title", "ISBN", "Author", "Genre"};
		DefaultTableModel model = new DefaultTableModel(1, columnNames.length) ;
		model.setColumnIdentifiers(columnNames);

		table = new JTable(model)
		{
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};

		scrollPane.setViewportView(table);

		ArrayList<Book> books= BookController.getAllBooks();

		((DefaultTableModel) table.getModel()).removeRow(0);

		for (int i = 0; i < books.size(); i++) {
			Book book = books.get(i);
			((DefaultTableModel) table.getModel()).addRow(
					new Object[]{book.getTitle(), book.getISBN(), book.getAuthor(),book.getGenre()});
		}


		JLabel lblBooks = new JLabel(books.size()+" Book(s)");
		lblBooks.setBounds(528, 336, 46, 14);
		panel.add(lblBooks);
	}

	public JFrame getFrame() {
		return frame;
	}

}
