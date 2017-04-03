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

        JPanel otherSettingsPanel = new JPanel();
        otherSettingsPanel.setBorder(new TitledBorder(null, "Settings",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        otherSettingsPanel.setPreferredSize(new Dimension(390, 100));

        JPanel logoutPanel = new JPanel();
        logoutPanel.setBorder(new TitledBorder(null, "Logout",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        logoutPanel.setPreferredSize(new Dimension(390, 80));

        JButton logoutButton = new JButton("Logout");
        logoutPanel.add(logoutButton);

        gearPanel.add(otherSettingsPanel);
        gearPanel.add(logoutPanel);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dialog.dispose();
                frame.dispose();

                LoginWindow.main(null);
            }
        });

        dialog.setTitle("Options");
        dialog.setSize(new Dimension(400, 250));
        dialog.setResizable(false);
        dialog.setModal(true);
        dialog.setLocationRelativeTo(frame);
        dialog.add(gearPanel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }
}
