package com.groupone.lbls.views;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LibrarianBookOperations {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_14;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTable table;
	private JTextField textField_16;
	private JTable table_1;

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
					LibrarianBookOperations window = new LibrarianBookOperations();
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
	public LibrarianBookOperations() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Library Book Loan System - Librarian: %username% ");
		frame.setBounds(100, 100, 600, 418);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Library Book Loan System");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		label.setBounds(10, 11, 564, 25);
		panel.add(label);
		
		JLabel lblBookOperations = new JLabel("Book Operations");
		lblBookOperations.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookOperations.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblBookOperations.setBounds(10, 40, 564, 22);
		panel.add(lblBookOperations);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		tabbedPane.setBounds(10, 73, 564, 295);
		panel.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Create", null, panel_1, null);
		panel_1.setLayout(null);
		panel_1.setBackground(SystemColor.menu);
		
		JLabel label_1 = new JLabel("ISBN:");
		label_1.setBounds(10, 14, 46, 14);
		panel_1.add(label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(66, 11, 181, 20);
		panel_1.add(textField);
		
		JLabel label_2 = new JLabel("Title:");
		label_2.setBounds(10, 44, 46, 14);
		panel_1.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(66, 41, 181, 20);
		panel_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(66, 72, 181, 20);
		panel_1.add(textField_2);
		
		JLabel label_3 = new JLabel("Author:");
		label_3.setBounds(10, 75, 46, 14);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("Publisher:");
		label_4.setBounds(274, 14, 60, 14);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel("Genre:");
		label_5.setBounds(273, 44, 46, 14);
		panel_1.add(label_5);
		
		JLabel label_6 = new JLabel("Keywords:");
		label_6.setBounds(273, 75, 61, 14);
		panel_1.add(label_6);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(344, 11, 194, 20);
		panel_1.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(344, 41, 194, 20);
		panel_1.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(344, 72, 194, 20);
		panel_1.add(textField_5);
		
		JButton button = new JButton("Create Book");
		button.setBounds(224, 144, 110, 23);
		panel_1.add(button);
		
		JLabel label_7 = new JLabel("Quantity:");
		label_7.setBounds(10, 106, 46, 14);
		panel_1.add(label_7);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(66, 103, 181, 20);
		panel_1.add(textField_6);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		tabbedPane.addTab("Update", null, panel_2, null);
		
		JButton button_1 = new JButton("Get Book");
		button_1.setBounds(105, 134, 110, 23);
		panel_2.add(button_1);
		
		JButton button_2 = new JButton("Update Book");
		button_2.setBounds(386, 134, 110, 23);
		panel_2.add(button_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "Book Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 204, 539, 52);
		panel_2.add(panel_3);
		
		JLabel label_14 = new JLabel("Book is not taken by any customer");
		label_14.setBounds(10, 23, 449, 14);
		panel_3.add(label_14);
		
		JLabel label_8 = new JLabel("ISBN:");
		label_8.setBounds(10, 14, 46, 14);
		panel_2.add(label_8);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(66, 11, 181, 20);
		panel_2.add(textField_7);
		
		JLabel label_9 = new JLabel("Title:");
		label_9.setBounds(10, 44, 46, 14);
		panel_2.add(label_9);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(66, 41, 181, 20);
		panel_2.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(66, 72, 181, 20);
		panel_2.add(textField_9);
		
		JLabel label_10 = new JLabel("Author:");
		label_10.setBounds(10, 75, 46, 14);
		panel_2.add(label_10);
		
		JLabel label_11 = new JLabel("Publisher:");
		label_11.setBounds(274, 14, 60, 14);
		panel_2.add(label_11);
		
		JLabel label_12 = new JLabel("Genre:");
		label_12.setBounds(273, 44, 46, 14);
		panel_2.add(label_12);
		
		JLabel label_13 = new JLabel("Keywords:");
		label_13.setBounds(273, 75, 61, 14);
		panel_2.add(label_13);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(344, 11, 194, 20);
		panel_2.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(344, 41, 194, 20);
		panel_2.add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(344, 72, 194, 20);
		panel_2.add(textField_12);
		
		JLabel label_15 = new JLabel("Quantity:");
		label_15.setBounds(10, 106, 46, 14);
		panel_2.add(label_15);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(66, 103, 181, 20);
		panel_2.add(textField_13);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		tabbedPane.addTab("Delete", null, panel_4, null);
		
		JLabel label_16 = new JLabel("ISBN:");
		label_16.setBounds(164, 14, 33, 14);
		panel_4.add(label_16);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(207, 11, 163, 20);
		panel_4.add(textField_14);
		
		JButton btnGetBook = new JButton("Get Book");
		btnGetBook.setBounds(164, 128, 89, 23);
		panel_4.add(btnGetBook);
		
		JButton btnDeleteBook = new JButton("Delete Book");
		btnDeleteBook.setBounds(281, 128, 89, 23);
		panel_4.add(btnDeleteBook);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 539, 43);
		panel_4.add(scrollPane);
		
		Object columnNames[] = { "Title", "ISBN", "Author", "Genre"};
		Object rowData[][] = { {"Example Book One", "111", "Writer 1", "Sci-fi"} };
		
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
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("View", null, panel_5, null);
		panel_5.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Book", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.setBounds(10, 11, 539, 89);
		panel_5.add(panel_6);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setBounds(34, 26, 100, 14);
		panel_6.add(lblIsbn);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(144, 23, 314, 20);
		panel_6.add(textField_16);
		
		JButton btnGetBook_1 = new JButton("Get Book");
		btnGetBook_1.setBounds(369, 54, 89, 23);
		panel_6.add(btnGetBook_1);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_7.setBounds(10, 111, 539, 145);
		panel_5.add(panel_7);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 21, 519, 113);
		panel_7.add(scrollPane_1);
		
		Object columnNames_1[] = { "Title", "Author", "Genre", "Owner Name", "Owner Email"};
		Object rowData_1[][] = { {"Example Book One", "Writer 1", "Sci-fi", "Example User One", "example@domain.edu.tr"} };
		
		table_1 = new JTable(rowData_1, columnNames_1)
		{
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		
		table_1.getTableHeader().setReorderingAllowed(false);
		TableRowSorter<TableModel> sorter_2 = new TableRowSorter<TableModel>(table_1.getModel());
		table_1.setRowSorter(sorter_2);

		
		scrollPane_1.setViewportView(table_1);
		
		
	}
}
