package com.groupone.lbls.views;
import com.groupone.lbls.controller.UserController;
import com.groupone.lbls.model.User;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.Color;

public class LoginWindow {

    private JFrame frmLibraryBookLoan;
    private JTextField textField;
    private JPasswordField passwordField;

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
                    LoginWindow window = new LoginWindow();
                    window.frmLibraryBookLoan.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public LoginWindow() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmLibraryBookLoan = new JFrame();
        frmLibraryBookLoan.setTitle("Library Book Loan System - Login");
        frmLibraryBookLoan.setResizable(false);
        frmLibraryBookLoan.setBounds(100, 100, 450, 289);
        frmLibraryBookLoan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        frmLibraryBookLoan.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
        JLabel lblLibraryBookLoan = new JLabel("Library Book Loan System");
        lblLibraryBookLoan.setHorizontalAlignment(SwingConstants.CENTER);
        lblLibraryBookLoan.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        lblLibraryBookLoan.setBounds(10, 11, 424, 22);
        panel.add(lblLibraryBookLoan);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "User Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(10, 36, 424, 135);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel label = new JLabel("Username");
        label.setBounds(53, 30, 60, 14);
        panel_1.add(label);
        
        JLabel label_1 = new JLabel("Password");
        label_1.setBounds(53, 58, 60, 14);
        panel_1.add(label_1);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(145, 27, 224, 20);
        panel_1.add(textField);
        
        passwordField = new JPasswordField();
        passwordField.setColumns(10);
        passwordField.setBounds(145, 55, 224, 20);
        panel_1.add(passwordField);
        
        JButton btnNewButton = new JButton("Login");
        btnNewButton.setBounds(269, 86, 100, 24);
        panel_1.add(btnNewButton);
        
        JButton btnForgotPassword = new JButton("Forgot Password");
        btnForgotPassword.setBounds(145, 87, 114, 23);
        panel_1.add(btnForgotPassword);
        
        JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Other", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_2.setBounds(10, 180, 424, 69);
        panel.add(panel_2);
        
        JButton button = new JButton("Search Books");
        button.setBounds(155, 25, 115, 23);
        panel_2.add(button);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String username = textField.getText();
                String password = String.valueOf(passwordField.getPassword());
                // TODO: validate username and password. ie: is empty etc.

                User user;
                MainWindow mainWindow;
                try {
                    user = UserController.getInstance().authenticate(username, password);
                    switch (user.getRole()) {
                        case LIBRARIAN:
                            mainWindow = new MainLibrarianWindow();
                            break;
                        case CUSTOMER:
                            mainWindow = new MainCustomerWindow();
                            break;
                        default:
                            throw new Error("Username/password is wrong.");
                    }
                } catch (Error e) {
                    JOptionPane.showMessageDialog(frmLibraryBookLoan,
                            e.getMessage(),
                            "Authentication Error",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                frmLibraryBookLoan.dispose();
                mainWindow.setUser(user);
                mainWindow.getFrame().setVisible(true);
            }
        });
    }
}
