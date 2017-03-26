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
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

public class CustomerViewFines {

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
					CustomerViewFines window = new CustomerViewFines();
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
	public CustomerViewFines() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Library Book Loan System - View My Fines");
		frame.setBounds(100, 100, 550, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Library Book Loan System");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		label.setBounds(10, 11, 514, 25);
		panel.add(label);
		
		JLabel lblViewMyFines = new JLabel("View My Fines");
		lblViewMyFines.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewMyFines.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblViewMyFines.setBounds(10, 40, 514, 22);
		panel.add(lblViewMyFines);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Fines", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 73, 514, 327);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 29, 494, 235);
		panel_1.add(scrollPane);
		
		Object columnNames[] = { "Title", "ISBN", "Author", "Fine (₺)"};
		Object rowData[][] = { {"Example Book One", "111", "Writer 1", "5"} };
		
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
		
		JButton btnPayFine = new JButton("Pay Fine");
		btnPayFine.setBounds(415, 293, 89, 23);
		panel_1.add(btnPayFine);
		
		JLabel lblBooks = new JLabel("1 Book(s)");
		lblBooks.setBounds(458, 268, 46, 14);
		panel_1.add(lblBooks);
	}

}
