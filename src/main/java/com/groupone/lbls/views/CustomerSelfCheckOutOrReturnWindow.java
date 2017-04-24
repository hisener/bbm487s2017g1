package com.groupone.lbls.views;

import com.groupone.lbls.controller.UserController;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class CustomerSelfCheckOutOrReturnWindow {

    private JFrame frame;
    /**
     * Create the application.
     */
    public CustomerSelfCheckOutOrReturnWindow(int userId) {
        initialize(userId);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(final int userId) {
        frame = new JFrame("Library Book Loan System - Self Check-out or Return");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final JTextField textField;

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
        panel_1.setBorder(new TitledBorder(null, "Barcode Information",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
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

        btnCheckout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (textField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(getFrame(),
                            "ISBN is mandatory.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    boolean result = UserController.getInstance().selfCheckout(
                            userId, textField.getText());
                    if (!result) {
                        throw new Exception("The self-checkout operation" +
                                "could not be performed.");
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(getFrame(),
                            e.getMessage(),
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(getFrame(), "Done.",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton btnReturn = new JButton("Return");
        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (textField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(getFrame(),
                            "ISBN is mandatory.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    boolean result = UserController.getInstance().selfReturn(
                            userId, textField.getText());
                    if (!result) {
                        throw new Exception("The self-return operation" +
                                "could not be performed.");
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(getFrame(),
                            e.getMessage(),
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(getFrame(), "Done.",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btnReturn.setBounds(217, 67, 89, 23);
        panel_1.add(btnReturn);

        getFrame().setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
