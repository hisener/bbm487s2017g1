package com.groupone.lbls.views;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView.TableRow;

import com.groupone.lbls.controller.BookController;
import com.groupone.lbls.controller.UserController;
import com.groupone.lbls.db.BookDAO;
import com.groupone.lbls.model.Book;
import com.groupone.lbls.model.UserRole;
import com.groupone.lbls.utils.Validation;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SearchBooksWindow {
    
    private int owner;
    private JFrame frame;
    private JTable table;
    private JTextField textField_ISBN;
    private JTextField textField_Title;
    private JTextField textField_Author;
    private JTextField textField_Publisher;
    private JTextField textField_Genre;
    private JTextField textField_Keywords;
    
    private JScrollPane scrollPane;
    
    private JButton btnSearch;
    
    private Object columnNames[] = { "ISBN", "Title", "Author", "Genre", "Publisher", "Keywords"};
    private Object rowData[][]; // = { {"", "", "", "", "", ""} };

    /**
     * Create the application.
     */
    public SearchBooksWindow(int userId) {
        initialize(userId);
        frame.setResizable(false);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(final int userId) {
        frame = new JFrame("Library Book Loan System - Search Books");
        frame.setBounds(100, 100, 592, 474);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel label = new JLabel("Library Book Loan System");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
        label.setBounds(10, 11, 566, 30);
        frame.getContentPane().add(label);
        
        JLabel lblSearchBooks = new JLabel("Search Books");
        lblSearchBooks.setHorizontalAlignment(SwingConstants.CENTER);
        lblSearchBooks.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
        lblSearchBooks.setBounds(10, 42, 566, 22);
        frame.getContentPane().add(lblSearchBooks);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(39, 238, 503, 145);
        frame.getContentPane().add(scrollPane);
            
        btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent arg0) {
                
                //BookDAO bookSearcher = new BookDAO();
                
                //bookSearcher.getListOfBooks(textField_ISBN.getText(), textField_Title.getText(), textField_Author.getText(),
                //        textField_Publisher.getText(), textField_Genre.getText(), textField_Keywords.getText());
                        
                //rowData = bookSearcher.getRowData();

                if(!textField_ISBN.getText().isEmpty() &&
                        !Validation.isISBNValid(textField_ISBN.getText())) {
                    JOptionPane.showMessageDialog(getFrame(),
                            "ISBN is not valid.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                rowData = BookController.getInstance().searchBook(textField_ISBN.getText(), textField_Title.getText(), textField_Author.getText(),
                        textField_Publisher.getText(), textField_Genre.getText(), textField_Keywords.getText());
                
                if(rowData == null)
                {        
                    DefaultTableModel dm = new DefaultTableModel(columnNames, 0);                    
                    table = new JTable(dm);
                    JOptionPane.showMessageDialog(getFrame(),
                            "No book found.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    table = new JTable(rowData, columnNames)
                    {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                }

                table.getTableHeader().setReorderingAllowed(false);
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
                table.setRowSorter(sorter);

                List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
                sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
                sorter.setSortKeys(sortKeys);
                scrollPane.setViewportView(table);
                
                table.repaint();

                //if a user logins
                if(userId!=-1){
                    /*This block is used to add chosen book to the waiting list of the user.*/
                    table.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            if (e.getClickCount() == 2) {
                                JTable target = (JTable)e.getSource();
                                int row = target.getSelectedRow();
                                String ISBN=(String)table.getValueAt(row, 0);

                                Book book=BookController.getInstance().getBook(ISBN);

                                int borrowed_book_count=BookController.getInstance().getTakenBookCount(book.getId());
                                int book_count=book.getQuantity();

                                //check not available of book
                                if(borrowed_book_count==book_count&&
                                        //check that user has borrowed.
                                        UserController.getInstance().getTakenDate(userId, book.getId())&&
                                        //check that user added the wait list before
                                        !(BookController.getInstance().getWaitListBook(userId,book.getId()))){

                                    JFrame frame = new JFrame();
                                    String[] options = new String[2];
                                    options[0] = new String("Agree");
                                    options[1] = new String("Disagree");

                                    int res = JOptionPane.showOptionDialog(frame.getContentPane(),
                                            "Do you want to add the book to the waiting list!",
                                            "", 0,JOptionPane.INFORMATION_MESSAGE,
                                            null,options,null);

                                    switch (res) {
                                        case JOptionPane.YES_OPTION:
                                            BookController.getInstance().addWaitList(userId, book.getId());
                                            JOptionPane.showMessageDialog(null, "Process Successfully");
                                            break;
                                        case JOptionPane.NO_OPTION:
                                            JOptionPane.showMessageDialog(null, "Process is Canceled");
                                            break;
                                    }
                                }

                            }
                        }
                    });
                }

                resetFields();
            }
            
        });
        btnSearch.setBounds(240, 196, 89, 23);
        frame.getContentPane().add(btnSearch);
        
        JLabel label_1 = new JLabel("ISBN:");
        label_1.setBounds(57, 95, 46, 14);
        frame.getContentPane().add(label_1);
        
        JLabel label_2 = new JLabel("Title:");
        label_2.setBounds(57, 125, 46, 14);
        frame.getContentPane().add(label_2);
        
        JLabel label_3 = new JLabel("Author");
        label_3.setBounds(57, 156, 46, 14);
        frame.getContentPane().add(label_3);
        
        JLabel label_4 = new JLabel("Publisher:");
        label_4.setBounds(286, 95, 60, 14);
        frame.getContentPane().add(label_4);
        
        JLabel label_5 = new JLabel("Genre:");
        label_5.setBounds(285, 125, 46, 14);
        frame.getContentPane().add(label_5);
        
        JLabel label_6 = new JLabel("Keywords:");
        label_6.setBounds(285, 156, 61, 14);
        frame.getContentPane().add(label_6);
        
        textField_ISBN = new JTextField();
        textField_ISBN.addKeyListener(enterKeyListener);        // New Key Listener        
        textField_ISBN.setColumns(10);
        textField_ISBN.setBounds(113, 92, 150, 20);
        frame.getContentPane().add(textField_ISBN);
        
        textField_Title = new JTextField();
        textField_Title.addKeyListener(enterKeyListener);
        textField_Title.setColumns(10);
        textField_Title.setBounds(113, 122, 150, 20);
        frame.getContentPane().add(textField_Title);
        
        textField_Author = new JTextField();
        textField_Author.addKeyListener(enterKeyListener);
        textField_Author.setColumns(10);
        textField_Author.setBounds(113, 153, 150, 20);
        frame.getContentPane().add(textField_Author);
        
        textField_Publisher = new JTextField();
        textField_Publisher.addKeyListener(enterKeyListener);
        textField_Publisher.setColumns(10);
        textField_Publisher.setBounds(356, 92, 158, 20);
        frame.getContentPane().add(textField_Publisher);
        
        textField_Genre = new JTextField();
        textField_Genre.addKeyListener(enterKeyListener);
        textField_Genre.setColumns(10);
        textField_Genre.setBounds(356, 122, 158, 20);
        frame.getContentPane().add(textField_Genre);
        
        textField_Keywords = new JTextField();
        textField_Keywords.addKeyListener(enterKeyListener);
        textField_Keywords.setColumns(10);
        textField_Keywords.setBounds(356, 153, 158, 20);
        frame.getContentPane().add(textField_Keywords);
        
        
        
//        Object columnNames[] = { "Book Name", "ISBN", "Writer", "Year"};
//        Object rowData[][] = { {"Example Book One", "111", "Writer 1", "2017"}, {"Example Book Two", "222", "Writer 2", "2016"}};
//        table = new JTable(rowData, columnNames);
//        
//        table.getTableHeader().setReorderingAllowed(false);
//        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
//        table.setRowSorter(sorter);
//
//        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
//        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
//        sorter.setSortKeys(sortKeys);
//
//        JScrollPane scrollPane = new JScrollPane(table);
//        
//
//        this.frame.add(scrollPane, BorderLayout.CENTER);
//        this.frame.setMinimumSize(new Dimension(600, 300));
//        jTable.setBorder(javax.swing.BorderFactory.createCompoundBorder());
//
//        
//        table.setModel(new javax.swing.table.DefaultTableModel(rowData,columnNames));
//
//        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
//
//        
//        table.setBounds(33, 113, 521, 236);
//        frame.getContentPane().add(table);
        
        

    }
    
    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    
    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
    
    private KeyListener enterKeyListener = new KeyListener() {
        @Override
        public void keyReleased(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                btnSearch.doClick();
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            
        }
    };
    
    private void resetFields()
    {
        textField_Author.setText("");
        textField_Genre.setText("");
        textField_ISBN.setText("");
        textField_Keywords.setText("");
        textField_Publisher.setText("");
        textField_Title.setText("");
        
        textField_ISBN.requestFocus();
    }

}
