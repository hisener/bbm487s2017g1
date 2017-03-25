package lbls.views;
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
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class MainCustomerWindow {

	private JFrame frame;

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

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
					MainCustomerWindow window = new MainCustomerWindow();
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
	public MainCustomerWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Library Book Loan System - User: %username%");
		frame.setBounds(100, 100, 550, 276);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Library Book Loan System");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		label.setBounds(165, 11, 202, 25);
		panel.add(label);
		
		JLabel lblWelcomeusername = new JLabel("Welcome, %username%");
		lblWelcomeusername.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeusername.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblWelcomeusername.setBounds(10, 40, 514, 22);
		panel.add(lblWelcomeusername);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Operations", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 73, 334, 153);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnSearchBooks = new JButton("Search Books");
		btnSearchBooks.setBounds(10, 24, 138, 23);
		panel_1.add(btnSearchBooks);
		
		JButton btnRBook = new JButton("My Books and Reservations");
		btnRBook.setBounds(158, 24, 165, 23);
		panel_1.add(btnRBook);
		
		JButton btnViewMyFines = new JButton("View My Fines");
		btnViewMyFines.setBounds(10, 58, 138, 23);
		panel_1.add(btnViewMyFines);
		
		JButton btnSelfCheckoutOr = new JButton("Self Check-out or Return");
		btnSelfCheckoutOr.setBounds(158, 58, 165, 23);
		panel_1.add(btnSelfCheckoutOr);
		
		JButton btnNotifications = new JButton("Notifications");
		btnNotifications.setBounds(10, 117, 138, 23);
		panel_1.add(btnNotifications);
		
		JLabel lblNotifications = new JLabel("Notifications: 0");
		lblNotifications.setBounds(10, 92, 72, 14);
		panel_1.add(lblNotifications);
		
		JLabel label_1 = new JLabel("New label");
		label_1.setIcon(new ImageIcon("res\\settings.png"));
		label_1.setBounds(492, 11, 32, 32);
		panel.add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Summary", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(354, 73, 170, 153);
		panel.add(panel_2);
		
		JLabel lblMmddyyyy = new JLabel("mm.dd.yyyy");
		lblMmddyyyy.setHorizontalAlignment(SwingConstants.CENTER);
		lblMmddyyyy.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblMmddyyyy.setBounds(10, 23, 150, 14);
		panel_2.add(lblMmddyyyy);
		
		JLabel lblUsername = new JLabel("%username%");
		lblUsername.setBounds(55, 48, 105, 14);
		panel_2.add(lblUsername);
		
		JLabel label_4 = new JLabel("Name:");
		label_4.setBounds(13, 47, 35, 14);
		panel_2.add(label_4);
		
		JLabel label_5 = new JLabel("5\u20BA");
		label_5.setBounds(55, 73, 105, 14);
		panel_2.add(label_5);
		
		JLabel label_6 = new JLabel("Fine:");
		label_6.setBounds(13, 73, 34, 14);
		panel_2.add(label_6);
		
		JLabel label_7 = new JLabel("Books:");
		label_7.setBounds(13, 98, 37, 14);
		panel_2.add(label_7);
		
		JLabel label_8 = new JLabel("5");
		label_8.setBounds(55, 98, 105, 14);
		panel_2.add(label_8);
	}
}
