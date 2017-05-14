package com.groupone.lbls.views;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.groupone.lbls.controller.UserController;
import com.groupone.lbls.db.LoanDAO;
import com.groupone.lbls.db.WaitlistDAO;
import com.groupone.lbls.model.User;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.io.ObjectInputStream.GetField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerMyBooksAndReservationsWindow {

	private User user;
	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	/*
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
                	CustomerMyBooksAndReservationsWindow window = new CustomerMyBooksAndReservationsWindow();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}
	*/
	/*
	public CustomerMyBooksAndReservationsWindow() {
		initialize();
	}
	*/
	
	/**
	 * Create the application.
	 * @param user 
	 * @wbp.parser.entryPoint
	 */
	public CustomerMyBooksAndReservationsWindow(User user) {
		this.setUser(user);
		initialize();
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	}

	private void setUser(User user) {
		this.user = user;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Library Book Loan System - My Books and Reservations");
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Library Book Loan System");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		label.setBounds(10, 11, 674, 30);
		frame.getContentPane().add(label);
		
		JLabel lblBookReservations = new JLabel("My Books and Reservations");
		lblBookReservations.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookReservations.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblBookReservations.setBounds(10, 42, 674, 22);
		frame.getContentPane().add(lblBookReservations);
			
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.text);
		tabbedPane.setBounds(10, 75, 674, 228);
		frame.getContentPane().add(tabbedPane);
		
		initializeCurrentBooks(tabbedPane);
		initializeWaitlist(tabbedPane);
		initializeHistory(tabbedPane);
		
		frame.setVisible(true);
	}
	
	private void initializeCurrentBooks(JTabbedPane tabbedPane)
	{
		Object columnNames[] = { "Title", "ISBN", "Author", "Genre", "Reservation Date", "Return Date"};
//		Object rowData[][] = { {"Example Book One", "111", "Writer 1", "Sci-fi", "01.03.2017", "01.04.2017"},
//							   {"Example Book Two", "222", "Writer 2", "Action", "22.03.2017", "22.04.2017"} };
		
		LoanDAO loans = new LoanDAO();
		loans.getUserBooks(user.getId());
		Object rowData[][] = loans.getRowData();
			
		JPanel panel = new JPanel();
		tabbedPane.addTab("Current Books", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(SystemColor.window);
		scrollPane.setBounds(10, 11, 649, 178);
		panel.add(scrollPane);
		
		
		if(rowData == null)
		{
			DefaultTableModel dm = new DefaultTableModel(columnNames, 0);					
			table = new JTable(dm);
		}
		else
		{
			table = new JTable(rowData, columnNames)
			{
				@Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			    }
			};
			table.setBackground(Color.white);
			table.setOpaque(true);
			
			if(rowData != null)
			{
				table.getTableHeader().setReorderingAllowed(false);
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
				table.setRowSorter(sorter);

				List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
				sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
				sorter.setSortKeys(sortKeys);
			}
			
		}
		
		scrollPane.setViewportView(table);
	}
	
	private void initializeWaitlist(JTabbedPane tabbedPane)
	{
		Object columnNames[] = { "Title", "ISBN", "Date Added"};
		
		WaitlistDAO waitlistOfUser = new WaitlistDAO();
		waitlistOfUser.getUserBookOnWaitlist(user.getId());
		Object rowData[][] = waitlistOfUser.getRowData();
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("Wait List", null, panel_1, null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 649, 144);
		panel_1.add(scrollPane_1);

	
		if(rowData == null)
		{
			DefaultTableModel dm = new DefaultTableModel(columnNames, 0);					
			table_1 = new JTable(dm);
		}
		else
		{
			table_1 = new JTable(rowData, columnNames)
			{
				@Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			    }
			};
			table_1.setBackground(Color.white);
			table_1.setOpaque(true);
			
			if(rowData != null)
			{
				table_1.getTableHeader().setReorderingAllowed(false);
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table_1.getModel());
				table_1.setRowSorter(sorter);

				List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
				sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
				sorter.setSortKeys(sortKeys);
			}
			
		}
		
		scrollPane_1.setViewportView(table_1);
		
		JButton btnDeleteWish = new JButton("Delete Wish");
		btnDeleteWish.setBounds(549, 166, 110, 23);
		panel_1.add(btnDeleteWish);
		btnDeleteWish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table_1.getSelectedRow() >= 0)
				{
					int row = table_1.getSelectedRow();
					String tempISBN = table_1.getValueAt(row, 1).toString();
					WaitlistDAO waitlist = new WaitlistDAO();
					boolean result = waitlist.deleteWish(tempISBN);
					
					if(result){
						JOptionPane.showMessageDialog(frame, "Book with ISBN: " + tempISBN + " is deleted from your wishlist.");
					} else {
						JOptionPane.showMessageDialog(frame, "Cannot delete book from wishlist.");
					}
					
					frame.dispose();
					
				}
			}
		});
	}
	
	private void initializeHistory(JTabbedPane tabbedPane)
	{
		Object columnNames[] = { "Title", "ISBN", "Author", "Genre", "Reservation Date", "Return Date"};
		
		LoanDAO history = new LoanDAO();
		history.getUserHistory(user.getId());
		Object rowData[][] = history.getRowData();
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		tabbedPane.addTab("History", null, panel_2, null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 11, 649, 178);
		panel_2.add(scrollPane_2);
		
		if(rowData == null)
		{
			DefaultTableModel dm = new DefaultTableModel(columnNames, 0);					
			table_2 = new JTable(dm);
		}
		else
		{
			table_2 = new JTable(rowData, columnNames)
			{
				@Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			    }
			};
			table_2.setBackground(Color.white);
			table_2.setOpaque(true);
			
			if(rowData != null)
			{
				table_2.getTableHeader().setReorderingAllowed(false);
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table_2.getModel());
				table_2.setRowSorter(sorter);

				List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
				sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
				sorter.setSortKeys(sortKeys);
			}
			
		}
				
		scrollPane_2.setViewportView(table_2);
	}
}
