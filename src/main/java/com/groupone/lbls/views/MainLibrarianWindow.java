package com.groupone.lbls.views;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class MainLibrarianWindow extends MainWindow {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
          java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainLibrarianWindow window = new MainLibrarianWindow();
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
	public MainLibrarianWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Library Book Loan System - Librarian: %username%");
		frame.setBounds(100, 100, 441, 224);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("settings.png")));
		lblNewLabel.setBounds(383, 11, 32, 32);
		panel.add(lblNewLabel);
		
		JLabel lblWelcomeusername = new JLabel("Welcome, %username%");
		lblWelcomeusername.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeusername.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblWelcomeusername.setBounds(10, 40, 426, 22);
		panel.add(lblWelcomeusername);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Operations", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 73, 405, 101);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnUserOperations = new JButton("User Operations");
		btnUserOperations.setBounds(55, 26, 124, 23);
		panel_1.add(btnUserOperations);
		
		JButton btnBookOperations = new JButton("Book Operations");
		btnBookOperations.setBounds(227, 26, 124, 23);
		panel_1.add(btnBookOperations);
		
		JButton btnViewAllBooks = new JButton("View All Books");
		btnViewAllBooks.setBounds(55, 60, 124, 23);
		panel_1.add(btnViewAllBooks);
		
		JButton btnViewAllUsers = new JButton("View All Users");
		btnViewAllUsers.setBounds(227, 60, 124, 23);
		panel_1.add(btnViewAllUsers);
		
		JLabel label = new JLabel("Library Book Loan System");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		label.setBounds(10, 11, 415, 25);
		panel.add(label);
	}
}
