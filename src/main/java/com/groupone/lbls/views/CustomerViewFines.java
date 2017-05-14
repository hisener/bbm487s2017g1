package com.groupone.lbls.views;

import com.groupone.lbls.controller.UserController;
import com.groupone.lbls.db.Query;
import com.groupone.lbls.db.UserDAO;
import com.groupone.lbls.db.impl.UserDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.MaskFormatter;

public class CustomerViewFines {

    private JFrame frame;

    /**
     * Create the application.
     */
    public CustomerViewFines(int userId) {
        initialize(userId);
        getFrame().setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(final int userId) {
        frame = new JFrame("Library Book Loan System - View My Fines");
        frame.setResizable(false);
        frame.setBounds(100, 100, 550, 450);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
        JLabel label = new JLabel("Library Book Loan System");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
        label.setBounds(10, 11, 530, 25);
        panel.add(label);
        
        JLabel lblViewMyFines = new JLabel("View My Fines");
        lblViewMyFines.setHorizontalAlignment(SwingConstants.CENTER);
        lblViewMyFines.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
        lblViewMyFines.setBounds(10, 40, 530, 22);
        panel.add(lblViewMyFines);

        final JPanel panel_1 = new JPanel();
        panel_1.setBounds(10, 73, 530, 90);
        panel.add(panel_1);
        panel_1.setLayout(new BorderLayout(3,3));

        JPanel labels = new JPanel(new GridLayout(0,1));
        JPanel controls = new JPanel(new GridLayout(0,1));
        panel_1.add(labels, BorderLayout.WEST);
        panel_1.add(controls, BorderLayout.CENTER);

        int fine = UserController.getInstance().getUsersFine(userId);

        if (fine == 0) {
            JLabel infoLabel = new JLabel("You do not have any fine.");
            infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panel_1.add(infoLabel);
        } else {
            labels.add(new JLabel("Card Number"));
            final JTextField cardNumberField = new JFormattedTextField(
                    createFormatter("#### #### #### ####"));
            controls.add(cardNumberField);

            JLabel infoLabel = new JLabel("Total fine: " + fine + " TL");
            infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JButton payment = new JButton("Process Payment");

            payment.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if (!isCreditCardNumberValid(cardNumberField.getText())) {
                        JOptionPane.showMessageDialog(getFrame(),
                                "Invalid card number.",
                                "Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    try {
                        boolean result = UserController.getInstance().payFine(
                                userId, cardNumberField.getText());
                        if (!result) {
                            throw new Exception("The payment operation" +
                                    "could not be performed.");
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(getFrame(),
                                e.getMessage(),
                                "Error",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    int input = JOptionPane.showOptionDialog(getFrame(),
                            "Done.", "Success",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null, null, null);

                    if (input == JOptionPane.OK_OPTION) {
                        getFrame().dispose();
                    }
                }
            });

            panel_1.add(infoLabel, BorderLayout.NORTH);
            panel_1.add(payment, BorderLayout.SOUTH);
        }
    }

    private boolean isCreditCardNumberValid(String creditCardNumber) {
        String expression = "^(?:\\d[ ]?){16}$";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(creditCardNumber);

        return matcher.find();
    }

    private MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }

    public JFrame getFrame() {
        return frame;
    }
}
