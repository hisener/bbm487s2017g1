package com.groupone.lbls.views;

import com.groupone.lbls.controller.UserController;
import com.groupone.lbls.model.User;
import com.groupone.lbls.model.UserRole;
import com.groupone.lbls.utils.Validation;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LibrarianUserOperations {

    private JFrame frame;

    /**
     * Create the application.
     */
    public LibrarianUserOperations(String username) {
        initialize(username);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(String username) {
        frame = new JFrame("Library Book Loan System - Librarian: " + username);
        frame.setBounds(100, 100, 550, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
        JLabel label = new JLabel("Library Book Loan System");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
        label.setBounds(10, 11, 514, 25);
        panel.add(label);
        
        JLabel lblBookOperations = new JLabel("User Operations");
        lblBookOperations.setHorizontalAlignment(SwingConstants.CENTER);
        lblBookOperations.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
        lblBookOperations.setBounds(10, 40, 514, 22);
        panel.add(lblBookOperations);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBackground(Color.LIGHT_GRAY);
        tabbedPane.setBounds(10, 73, 514, 377);
        panel.add(tabbedPane);

        initCreateTab(tabbedPane);
        initUpdateTab(tabbedPane);
        initDeleteTab(tabbedPane, username);
        initViewTab(tabbedPane);
    }

    private void initCreateTab(JTabbedPane tabbedPane) {
        JPanel createTabPanel = new JPanel();
        final JTextField usernameField, emailField;
        final JPasswordField passwordField, confirmPasswordField;

        tabbedPane.addTab("Create", null, createTabPanel, null);
        createTabPanel.setLayout(null);

        JLabel label_7 = new JLabel("Username:");
        label_7.setBounds(41, 32, 100, 14);
        createTabPanel.add(label_7);

        usernameField = new JTextField();
        usernameField.setColumns(10);
        usernameField.setBounds(151, 29, 321, 20);
        createTabPanel.add(usernameField);

        JLabel label_8 = new JLabel("E-mail:");
        label_8.setBounds(41, 60, 100, 14);
        createTabPanel.add(label_8);

        emailField = new JTextField();
        emailField.setColumns(10);
        emailField.setBounds(151, 57, 321, 20);
        createTabPanel.add(emailField);

        JLabel label_9 = new JLabel("Password:");
        label_9.setBounds(41, 88, 100, 14);
        createTabPanel.add(label_9);

        passwordField = new JPasswordField();
        passwordField.setColumns(10);
        passwordField.setBounds(151, 85, 321, 20);
        createTabPanel.add(passwordField);

        JLabel label_10 = new JLabel("Confirm Password:");
        label_10.setBounds(41, 116, 100, 14);
        createTabPanel.add(label_10);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setColumns(10);
        confirmPasswordField.setBounds(151, 113, 321, 20);
        createTabPanel.add(confirmPasswordField);

        JLabel label_11 = new JLabel("User Role:");
        label_11.setBounds(41, 144, 100, 14);
        createTabPanel.add(label_11);

        String[] userRoleStrings = { "Librarian", "Customer" };
        final JComboBox<String> userRoleList = new JComboBox<>(userRoleStrings);
        userRoleList.setBounds(151, 141, 321, 20);
        userRoleList.setSelectedIndex(0);
        createTabPanel.add(userRoleList);

        JButton addUserButton = new JButton("Add User");
        addUserButton.setBounds(151, 187, 100, 23);
        createTabPanel.add(addUserButton);

        final JButton resetButton = new JButton("Reset Fields");
        resetButton.setBounds(261, 187, 100, 23);
        createTabPanel.add(resetButton);

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (emailField.getText().isEmpty() || usernameField.getText().isEmpty()
                        || passwordField.getPassword().length == 0
                        || confirmPasswordField.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(getFrame(), "The fields are mandatory.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if(!Validation.isEmailValid(emailField.getText())) {
                    JOptionPane.showMessageDialog(getFrame(), "The email is not valid.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!Arrays.equals(passwordField.getPassword(), confirmPasswordField.getPassword())) {
                    JOptionPane.showMessageDialog(getFrame(),
                            "The password and confirmation password do not match.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                UserRole userRole = UserRole.fromInt(userRoleList.getSelectedIndex() + 1);
                boolean result = UserController.getInstance().addUser(emailField.getText(),
                        usernameField.getText(),
                        String.valueOf(passwordField.getPassword()),
                        userRole);

                if (result) {
                    resetButton.doClick();
                    JOptionPane.showMessageDialog(getFrame(), "User was added.",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(getFrame(), "The user could not be added.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                usernameField.setText("");
                emailField.setText("");
                passwordField.setText("");
                confirmPasswordField.setText("");
                userRoleList.setSelectedIndex(0);
            }
        });
    }

    private void initUpdateTab(JTabbedPane tabbedPane) {
        JPanel updateTabPanel = new JPanel();
        final JTextField usernameField, emailField;
        final JPasswordField passwordField, confirmPasswordField;

        updateTabPanel.setLayout(null);
        tabbedPane.addTab("Update", null, updateTabPanel, null);

        JLabel label_7 = new JLabel("Username:");
        label_7.setBounds(41, 32, 100, 14);
        updateTabPanel.add(label_7);

        usernameField = new JTextField();
        usernameField.setColumns(10);
        usernameField.setBounds(151, 29, 321, 20);
        updateTabPanel.add(usernameField);

        JLabel label_8 = new JLabel("E-mail:");
        label_8.setBounds(41, 60, 100, 14);
        updateTabPanel.add(label_8);

        emailField = new JTextField();
        emailField.setColumns(10);
        emailField.setBounds(151, 57, 321, 20);
        updateTabPanel.add(emailField);

        JLabel label_9 = new JLabel("Password:");
        label_9.setBounds(41, 88, 100, 14);
        updateTabPanel.add(label_9);

        passwordField = new JPasswordField();
        passwordField.setColumns(10);
        passwordField.setBounds(151, 85, 321, 20);
        updateTabPanel.add(passwordField);

        JLabel label_10 = new JLabel("Confirm Password:");
        label_10.setBounds(41, 116, 100, 14);
        updateTabPanel.add(label_10);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setColumns(10);
        confirmPasswordField.setBounds(151, 113, 321, 20);
        updateTabPanel.add(confirmPasswordField);

        JLabel label_11 = new JLabel("User Role:");
        label_11.setBounds(41, 144, 100, 14);
        updateTabPanel.add(label_11);

        String[] userRoleStrings = { "Librarian", "Customer" };
        final JComboBox<String> userRoleList = new JComboBox<>(userRoleStrings);
        userRoleList.setBounds(151, 141, 321, 20);
        userRoleList.setSelectedIndex(0);
        updateTabPanel.add(userRoleList);

        JButton getUserButton = new JButton("Get User");
        getUserButton.setBounds(151, 187, 100, 23);
        updateTabPanel.add(getUserButton);

        final JButton updateButton = new JButton("Update User");
        updateButton.setBounds(261, 187, 100, 23);
        updateButton.setEnabled(false);
        updateTabPanel.add(updateButton);

        getUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (usernameField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "You should fill the username.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                User user = UserController.getInstance().getUser(usernameField.getText());
                if (user == null) {
                    JOptionPane.showMessageDialog(frame, "The user could not find.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                usernameField.setText(user.getUsername());
                emailField.setText(user.getEmail());
                updateButton.putClientProperty("id", user.getId());
                updateButton.setEnabled(true);
                userRoleList.setSelectedIndex(user.getRole().ordinal() - 1);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!emailField.getText().isEmpty() && !Validation.isEmailValid(emailField.getText())) {
                    JOptionPane.showMessageDialog(frame, "The email is not valid.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!Arrays.equals(passwordField.getPassword(), confirmPasswordField.getPassword())) {
                    JOptionPane.showMessageDialog(frame,
                            "The password and confirmation password do not match.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                boolean result = UserController.getInstance().updateUser(
                        String.valueOf(updateButton.getClientProperty("id")),
                        emailField.getText(), usernameField.getText(),
                        String.valueOf(passwordField.getPassword()),
                        UserRole.fromInt(userRoleList.getSelectedIndex() + 1));

                if (result) {
                    JOptionPane.showMessageDialog(frame, "User was updated.",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "The user could not be updated.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void initDeleteTab(JTabbedPane tabbedPane, final String username) {
        JPanel deleteTabPanel = new JPanel();
        final JTextField usernameField;
        final JTable table;

        deleteTabPanel.setLayout(null);
        tabbedPane.addTab("Delete", null, deleteTabPanel, null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(10, 14, 100, 14);
        deleteTabPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setColumns(10);
        usernameField.setBounds(125, 11, 374, 20);
        deleteTabPanel.add(usernameField);

        JButton getUserButton = new JButton("Get User");
        getUserButton.setBounds(126, 128, 89, 23);
        deleteTabPanel.add(getUserButton);

        final JButton deleteUserButton = new JButton("Delete User");
        deleteUserButton.setBounds(303, 128, 89, 23);
        deleteUserButton.setEnabled(false);
        deleteTabPanel.add(deleteUserButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 57, 489, 43);
        deleteTabPanel.add(scrollPane);

        String[] columnNames = { "Username", "Email" };
        DefaultTableModel model = new DefaultTableModel(1, columnNames.length) ;
        model.setColumnIdentifiers(columnNames);
        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        scrollPane.setViewportView(table);

        getUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (usernameField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(getFrame(), "You should fill the username.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (usernameField.getText().equals(username)) {
                    JOptionPane.showMessageDialog(getFrame(), "You cannot delete yourself.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                User user = UserController.getInstance().getUser(usernameField.getText());
                if (user == null) {
                    JOptionPane.showMessageDialog(getFrame(), "The user could not find.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ((DefaultTableModel) table.getModel()).removeRow(0);
                ((DefaultTableModel) table.getModel()).addRow(
                        new Object[]{ user.getUsername(), user.getEmail() });

                deleteUserButton.putClientProperty("id", user.getId());
                deleteUserButton.setEnabled(true);
            }
        });

        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boolean result = UserController.getInstance().deleteUser(
                        String.valueOf(deleteUserButton.getClientProperty("id")));

                if (result) {
                    JOptionPane.showMessageDialog(getFrame(), "User was deleted.",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    usernameField.setText("");
                    ((DefaultTableModel) table.getModel()).removeRow(0);
                    deleteUserButton.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(getFrame(), "The user could not be deleted.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void initViewTab(JTabbedPane tabbedPane) {
        JPanel viewTabPanel = new JPanel();
        final JTable booksTable;
        final JTextField usernameField;

        tabbedPane.addTab("View", null, viewTabPanel, null);
        viewTabPanel.setLayout(null);

        JPanel userPanel = new JPanel();
        userPanel.setBorder(new TitledBorder(null, "User", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        userPanel.setBounds(10, 11, 489, 125);
        viewTabPanel.add(userPanel);
        userPanel.setLayout(null);

        JLabel label_6 = new JLabel("Username:");
        label_6.setBounds(34, 26, 100, 14);
        userPanel.add(label_6);

        usernameField = new JTextField();
        usernameField.setColumns(10);
        usernameField.setBounds(144, 23, 314, 20);
        userPanel.add(usernameField);

        JButton getUserButton = new JButton("Get User");
        getUserButton.setBounds(369, 82, 89, 23);
        userPanel.add(getUserButton);

        JPanel booksPanel = new JPanel();
        booksPanel.setLayout(null);
        booksPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
                "Books (Under Construction!) ", TitledBorder.LEADING, TitledBorder.TOP,
                null, new Color(0, 0, 0)));
        booksPanel.setBounds(10, 148, 489, 190);
        viewTabPanel.add(booksPanel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 25, 469, 134);
        booksPanel.add(scrollPane);

        String[] columnNames = { "Title", "ISBN", "Author", "Genre" };
        DefaultTableModel model = new DefaultTableModel(0, columnNames.length) ;
        model.setColumnIdentifiers(columnNames);

        booksTable = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        booksTable.getTableHeader().setReorderingAllowed(false);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(booksTable.getModel());
        booksTable.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);

        scrollPane.setViewportView(booksTable);

        final JLabel bookCountLabel = new JLabel("0 Book(s)");
        bookCountLabel.setBounds(433, 165, 46, 14);
        booksPanel.add(bookCountLabel);

        getUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (usernameField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(getFrame(), "You should fill the username.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                User user = UserController.getInstance().getUser(usernameField.getText());
                if (user == null) {
                    JOptionPane.showMessageDialog(getFrame(), "The user could not find.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // just for update something
                DefaultTableModel tableModel = (DefaultTableModel) booksTable.getModel();
                for (int i = 0; i < tableModel.getRowCount(); ++i) {
                    tableModel.removeRow(i);
                }

                ((DefaultTableModel) booksTable.getModel()).addRow(
                        new Object[]{ "Harry Potter", "123456", "JKR", "Fantasy Fiction" });
                bookCountLabel.setText("1 Book(s)");

                // TODO: get books of the user
            }
        });
    }

    public JFrame getFrame() {
        return frame;
    }
}
