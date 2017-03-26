package com.groupone.lbls.views;
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
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		
		Object columnNames[] = { "Title", "ISBN", "Author", "Genre"};
		Object rowData[][] = { {"Example Book One", "111", "Writer 1", "Sci-fi"},
				{"Example Book Two", "222", "Writer 2", "Sci-fi"},
				{"Example Book Three", "333", "Writer 3", "Art"},
				{"Example Book Four", "444", "Writer 4", "Crime"},};
		
		table = new JTable(rowData, columnNames)
		{
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		
		table.getTableHeader().setReorderingAllowed(false);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);

		List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
		scrollPane.setViewportView(table);
		
		JLabel lblBooks = new JLabel("4 Book(s)");
		lblBooks.setBounds(528, 336, 46, 14);
		panel.add(lblBooks);
	}

}
