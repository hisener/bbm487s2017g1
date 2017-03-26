package com.groupone.lbls.views;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SearchBooksWindow {

	private JFrame frame;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

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
					SearchBooksWindow window = new SearchBooksWindow();
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
	public SearchBooksWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Library Book Loan System - Search Books");
		frame.setBounds(100, 100, 592, 474);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Library Book Loan System");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		label.setBounds(10, 11, 556, 30);
		frame.getContentPane().add(label);
		
		JLabel lblSearchBooks = new JLabel("Search Books");
		lblSearchBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchBooks.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblSearchBooks.setBounds(10, 42, 556, 22);
		frame.getContentPane().add(lblSearchBooks);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 238, 499, 145);
		frame.getContentPane().add(scrollPane);
		
		Object columnNames[] = { "Title", "ISBN", "Author", "Genre"};
		Object rowData[][] = { {"Example Book One", "111", "Writer 1", "Sci-fi"}, {"Example Book Two", "222", "Writer 2", "Action"}};
		
		table = new JTable(rowData, columnNames)
		{
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;//super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
		    }
		};
		
		table.getTableHeader().setReorderingAllowed(false);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);

		List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);


		scrollPane.setViewportView(table);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(240, 196, 89, 23);
		frame.getContentPane().add(btnSearch);
		
		JLabel label_1 = new JLabel("ISBN:");
		label_1.setBounds(57, 95, 46, 14);
		frame.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(113, 92, 150, 20);
		frame.getContentPane().add(textField);
		
		JLabel label_2 = new JLabel("Title:");
		label_2.setBounds(57, 125, 46, 14);
		frame.getContentPane().add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(113, 122, 150, 20);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(113, 153, 150, 20);
		frame.getContentPane().add(textField_2);
		
		JLabel label_3 = new JLabel("Author");
		label_3.setBounds(57, 156, 46, 14);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Publisher:");
		label_4.setBounds(286, 95, 60, 14);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Genre:");
		label_5.setBounds(285, 125, 46, 14);
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("Keywords:");
		label_6.setBounds(285, 156, 61, 14);
		frame.getContentPane().add(label_6);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(356, 92, 158, 20);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(356, 122, 158, 20);
		frame.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(356, 153, 158, 20);
		frame.getContentPane().add(textField_5);
		
		
		
//		Object columnNames[] = { "Book Name", "ISBN", "Writer", "Year"};
//		Object rowData[][] = { {"Example Book One", "111", "Writer 1", "2017"}, {"Example Book Two", "222", "Writer 2", "2016"}};
//		table = new JTable(rowData, columnNames);
//		
//		table.getTableHeader().setReorderingAllowed(false);
//		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
//		table.setRowSorter(sorter);
//
//		List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
//		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
//		sorter.setSortKeys(sortKeys);
//
//		JScrollPane scrollPane = new JScrollPane(table);
//		
//
//		this.frame.add(scrollPane, BorderLayout.CENTER);
//		this.frame.setMinimumSize(new Dimension(600, 300));
//		jTable.setBorder(javax.swing.BorderFactory.createCompoundBorder());
//
//		
//		table.setModel(new javax.swing.table.DefaultTableModel(rowData,columnNames));
//
//		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
//
//		
//		table.setBounds(33, 113, 521, 236);
//		frame.getContentPane().add(table);
		
		

	}
}
