package com.groupone.lbls.views;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GearDialog {

    public GearDialog(JFrame frame) {
        this.initialize(frame);
    }

    private void initialize(final JFrame frame) {
        final JDialog dialog = new JDialog();

        JPanel gearPanel = new JPanel();

        JPanel logoutPanel = new JPanel();
        logoutPanel.setBounds(10, 47, 324, 163);
        logoutPanel.setBorder(new TitledBorder(null, "Logout",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        logoutPanel.setPreferredSize(new Dimension(390, 80));
        logoutPanel.setLayout(null);
        gearPanel.setLayout(null);
        gearPanel.add(logoutPanel);
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(130, 24, 65, 23);
        logoutPanel.add(logoutButton);
               
        logoutButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent actionEvent) {
	        dialog.dispose();
	        frame.dispose();
	        
	        LoginWindow.main(null);
	        }
        });

        dialog.setTitle("Settings");
        dialog.setSize(new Dimension(350, 250));
        dialog.setResizable(false);
        dialog.setModal(true);
        dialog.setLocationRelativeTo(frame);
        dialog.getContentPane().add(gearPanel);
        
        JLabel lblSettings = new JLabel("Settings");
        lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
        lblSettings.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
        lblSettings.setBounds(10, 11, 324, 25);
        gearPanel.add(lblSettings);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }
}
