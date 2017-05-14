package com.groupone.lbls.views;

import com.groupone.lbls.db.WaitlistDAO;
import com.groupone.lbls.controller.UserController;
import com.groupone.lbls.model.User;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import javax.swing.border.TitledBorder;

import java.util.Timer;
import java.util.TimerTask;

public class MainCustomerWindow extends MainWindow {
	
	private ArrayList<Integer> bookIDs = new ArrayList<>();
	private JLabel notificationIcon = new JLabel("New label");
	private JLabel lblNotifications = new JLabel("Notifications: 0");
	private JLabel label_fine;
	private JLabel label_bookCount;
	
    /**
     * Create the application.
     */
    public MainCustomerWindow(User user) {
        this.setUser(user);
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
    	
    	final String username = this.getUser().getUsername();

        frame = new JFrame("Library Book Loan System - User: " + username);
        frame.setBounds(100, 100, 550, 276);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
        JLabel label = new JLabel("Library Book Loan System");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
        label.setBounds(165, 11, 202, 25);
        panel.add(label);
        
        JLabel lblWelcomeusername = new JLabel("Welcome, " + username);

        lblWelcomeusername.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcomeusername.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
        lblWelcomeusername.setBounds(10, 40, 514, 22);
        panel.add(lblWelcomeusername);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
                "Operations", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_1.setBounds(10, 73, 334, 153);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JButton btnSearchBooks = new JButton("Search Books");
        btnSearchBooks.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent actionEvent) {
        		SearchBooksWindow searchWindow = new SearchBooksWindow(getUser().getId());
        		searchWindow.getFrame().setVisible(true);
        	}
        });      
        
        btnSearchBooks.setBounds(10, 24, 138, 23);
        panel_1.add(btnSearchBooks);
        
        /*****************/
        /* NOTIFICATIONS */
        /*****************/
        
        /* Get user's book on waitlist.
         * Check user's books availability.
         * If available then put a list of them.
         */
              
        notificationIcon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("notification.png")) );
        notificationIcon.setBounds(150, 118, 20, 20);
        panel_1.add(notificationIcon);
        
        lblNotifications.setBounds(10, 92, 72, 14);
        panel_1.add(lblNotifications);
        
        /**************/
                
        JButton btnRBook = new JButton("My Books and Reservations");
        btnRBook.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		new CustomerMyBooksAndReservationsWindow(getUser());
        	}
        });
        btnRBook.setBounds(158, 24, 165, 23);
        panel_1.add(btnRBook);
        
        final JButton btnViewMyFines = new JButton("View My Fines");
        btnViewMyFines.putClientProperty("active", false);

        btnViewMyFines.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if ((Boolean) btnViewMyFines.getClientProperty("active")) {
                    // do not open new window
                    return;
                }

                CustomerViewFines customerViewFines = new CustomerViewFines(getUser().getId());
                btnViewMyFines.putClientProperty("active", true);

                customerViewFines.getFrame().addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent windowEvent) {
                        super.windowClosed(windowEvent);
                        btnViewMyFines.putClientProperty("active", false);
                        // TODO: Re-render summary part
                    }
                });
            }
        });
        btnViewMyFines.setBounds(10, 58, 138, 23);
        panel_1.add(btnViewMyFines);
        
        final JButton btnSelfCheckoutOr = new JButton("Self Check-out or Return");
        btnSelfCheckoutOr.putClientProperty("active", false);

        btnSelfCheckoutOr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if ((Boolean) btnSelfCheckoutOr.getClientProperty("active")) {
                    // do not open new window
                    return;
                }

                CustomerSelfCheckOutOrReturnWindow selfCheckOutOrReturnWindow =
                        new CustomerSelfCheckOutOrReturnWindow(getUser().getId());
                btnSelfCheckoutOr.putClientProperty("active", true);

                selfCheckOutOrReturnWindow.getFrame().addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent windowEvent) {
                        super.windowClosed(windowEvent);
                        btnSelfCheckoutOr.putClientProperty("active", false);
                        // TODO: Re-render summary part
                    }
                });
            }
        });
        btnSelfCheckoutOr.setBounds(158, 58, 165, 23);
        panel_1.add(btnSelfCheckoutOr);
               
        JButton btnNotifications = new JButton("Notifications");
        btnNotifications.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		CustomerNotifications notificationWindow = new CustomerNotifications(getUser().getId());
        		notificationWindow.getFrame().setVisible(true);
        	}
        });
        btnNotifications.setBounds(10, 117, 138, 23);
        panel_1.add(btnNotifications);
        
        JLabel gearLabel = new JLabel("New label");
        gearLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("settings.png")));
        gearLabel.setBounds(492, 11, 32, 32);
        panel.add(gearLabel);

        gearLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                new GearDialog(frame);
            }
        });
        
        JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
                "Summary", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_2.setBounds(354, 73, 170, 153);
        panel.add(panel_2);
        
        //JLabel lblMmddyyyy = new JLabel("mm.dd.yyyy");
        JLabel lblMmddyyyy = new JLabel(new SimpleDateFormat("MM.dd.yyyy").format(new Date()).toString());
        lblMmddyyyy.setHorizontalAlignment(SwingConstants.CENTER);
        lblMmddyyyy.setFont(new Font("Tahoma", Font.ITALIC, 11));
        lblMmddyyyy.setBounds(10, 23, 150, 14);
        panel_2.add(lblMmddyyyy);
        
        JLabel lblUsername = new JLabel(username);
        lblUsername.setBounds(55, 48, 105, 14);
        panel_2.add(lblUsername);
        
        JLabel label_4 = new JLabel("Name:");
        label_4.setBounds(13, 47, 35, 14);
        panel_2.add(label_4);
       
        JLabel label_6 = new JLabel("Fine:");
        label_6.setBounds(13, 73, 34, 14);
        panel_2.add(label_6);
        
        JLabel label_7 = new JLabel("Books:");
        label_7.setBounds(13, 98, 37, 14);
        panel_2.add(label_7);
        
        int fine = UserController.getInstance().getUsersFine(this.getUser().getId());
        label_fine = new JLabel(fine + " \u20BA");
        label_fine.setBounds(55, 73, 105, 14);
        panel_2.add(label_fine);
        
        int bookCount = UserController.getInstance().getUsersBookCount(this.getUser().getId());

        label_bookCount = new JLabel(bookCount + "");
        label_bookCount.setBounds(55, 98, 105, 14);
        panel_2.add(label_bookCount);
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
        	  @Override
        	  public void run() {
        		  bookIDs = checkAvailabilityOfWaitlistBooks(lblNotifications, notificationIcon);
        		  int fine = UserController.getInstance().getUsersFine(getUser().getId());
        		  int bookCount = UserController.getInstance().getUsersBookCount(getUser().getId());
        		  label_fine.setText(fine + " \u20BA");
        		  label_bookCount.setText(bookCount + "");
        	  }
        	}, 1000, 2000);

    }
    
    private ArrayList<Integer> checkAvailabilityOfWaitlistBooks(JLabel lblNotifications, JLabel notificationIcon)
    {
    	ArrayList<Integer> bookIDs = new ArrayList<Integer>(); 
    	WaitlistDAO waitlistOfUser = new WaitlistDAO();
        waitlistOfUser.getUserBookOnWaitlist(getUser().getId());
        
        
        for(int i = 0; i < waitlistOfUser.getBooksOnWaitlist().size(); i++)
        {
        	if(waitlistOfUser.getBooksOnWaitlist().get(i).isBookAvailable())
        	{
        		bookIDs.add(waitlistOfUser.getBooksOnWaitlist().get(i).getId());
        	}
        }
        
        lblNotifications.setText("Notifications: "+bookIDs.size());
        
        if(bookIDs.size() > 0) {
        	notificationIcon.setVisible(true);
        }else{
        	notificationIcon.setVisible(false);
        }
        
        return bookIDs;
        
    }
}
