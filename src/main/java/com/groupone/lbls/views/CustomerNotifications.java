package com.groupone.lbls.views;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.groupone.lbls.db.WaitlistDAO;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class CustomerNotifications {

    private JFrame frame;
    private JTable table;
    private int userID;

    /**
     * Create the application.
     */
    public CustomerNotifications(int id) {
        userID = id;
        initialize();
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame("Library Book Loan System - Notifications");
        frame.setResizable(false);
        frame.setBounds(100, 100, 438, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
        JLabel lblNotifications = new JLabel("Notifications");
        lblNotifications.setHorizontalAlignment(SwingConstants.CENTER);
        lblNotifications.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
        lblNotifications.setBounds(10, 40, 414, 22);
        panel.add(lblNotifications);
        
        JLabel label_1 = new JLabel("Library Book Loan System");
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
        label_1.setBounds(10, 11, 414, 25);
        panel.add(label_1);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Available Books", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_1.setBounds(10, 73, 414, 177);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 22, 394, 144);
        panel_1.add(scrollPane);
        
        Object columnNames[] = { "No", "Message" };
        Object rowData[][] = getNotifications();
        
        table = new JTable(rowData, columnNames)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table.setBackground(Color.white);
        table.setOpaque(true);
        
        
        table.getTableHeader().setReorderingAllowed(false);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        
        table.getColumnModel().getColumn(0).setMaxWidth(40);
        scrollPane.setViewportView(table);
        
    }
    
    private Object[][] getNotifications()
    {
        ArrayList<String> bookInfos = new ArrayList<String>(); 
        WaitlistDAO waitlistOfUser = new WaitlistDAO();
        waitlistOfUser.getUserBookOnWaitlist(userID);
        
        
        for(int i = 0; i < waitlistOfUser.getBooksOnWaitlist().size(); i++)
        {
            if(waitlistOfUser.getBooksOnWaitlist().get(i).isBookAvailable())
            {
                bookInfos.add("The book \"" + waitlistOfUser.getBooksOnWaitlist().get(i).getTitle()
                        +"\" with ISBN: "+waitlistOfUser.getBooksOnWaitlist().get(i).getISBN()+" is available.");
            }
        }
        
        Object[][] rowData = new Object[bookInfos.size()][2];
        
        for(int i = 0; i < bookInfos.size(); i++)
        {
            rowData[i][0] = i+1;
            rowData[i][1] = bookInfos.get(i);
        }
                
        return rowData;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }
}
