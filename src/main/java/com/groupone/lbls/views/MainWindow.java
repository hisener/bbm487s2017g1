package com.groupone.lbls.views;

import com.groupone.lbls.model.User;

import javax.swing.JFrame;

public abstract class MainWindow {

    protected JFrame frame;
    private User user;

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
