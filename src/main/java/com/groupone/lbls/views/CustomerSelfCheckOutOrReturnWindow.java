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
import javax.swing.JTextField;
import javax.swing.JButton;

public class CustomerSelfCheckOutOrReturnWindow {

	private JFrame frame;
	private JTextField textField;

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
					CustomerSelfCheckOutOrReturnWindow window = new CustomerSelfCheckOutOrReturnWindow();
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
	public CustomerSelfCheckOutOrReturnWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Library Book Loan System - Self Check-out or Return");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Library Book Loan System");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		label.setBounds(10, 11, 414, 25);
		panel.add(label);
		
		JLabel lblSelf = new JLabel("Self Check-out or Return");
		lblSelf.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelf.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblSelf.setBounds(10, 40, 414, 22);
		panel.add(lblSelf);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Barcode Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 73, 414, 177);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblBarcode = new JLabel("Barcode:");
		lblBarcode.setBounds(21, 27, 55, 14);
		panel_1.add(lblBarcode);
		
		textField = new JTextField();
		textField.setBounds(110, 25, 196, 17);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnCheckout = new JButton("Check-out");
		btnCheckout.setBounds(110, 67, 89, 23);
		panel_1.add(btnCheckout);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setBounds(217, 67, 89, 23);
		panel_1.add(btnReturn);
		
		JLabel lblActionIsCompleted = new JLabel("Action is completed");
		lblActionIsCompleted.setHorizontalAlignment(SwingConstants.CENTER);
		lblActionIsCompleted.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblActionIsCompleted.setBounds(10, 113, 394, 22);
		panel_1.add(lblActionIsCompleted);
	}

}
