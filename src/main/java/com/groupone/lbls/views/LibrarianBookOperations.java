package com.groupone.lbls.views;
import com.groupone.lbls.controller.BookController;
import com.groupone.lbls.model.Book;
import com.groupone.lbls.utils.Validation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LibrarianBookOperations {

    private JFrame frame;

    /**
     * Create the application.
     */
    public LibrarianBookOperations(String username) {
        initialize(username);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(String username) {

        frame = new JFrame("Library Book Loan System - Librarian: " + username);
        frame.setResizable(false);
        frame.setBounds(100, 100, 586, 418);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
        JLabel label = new JLabel("Library Book Loan System");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
        label.setBounds(10, 11, 564, 25);
        panel.add(label);
        
        JLabel lblBookOperations = new JLabel("Book Operations");
        lblBookOperations.setHorizontalAlignment(SwingConstants.CENTER);
        lblBookOperations.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
        lblBookOperations.setBounds(10, 40, 564, 22);
        panel.add(lblBookOperations);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBackground(Color.LIGHT_GRAY);
        tabbedPane.setBounds(10, 73, 564, 295);
        panel.add(tabbedPane);


        initCreateTab(tabbedPane);
        initUpdateTab(tabbedPane);
        initDeleteTab(tabbedPane);
        initViewTab(tabbedPane);
    }

    private void initCreateTab(JTabbedPane tabbedPane) {

        final JTextField ISBNfield, titleField, authorField, publisherField;
        final JTextField genreField, keywordsField, quantityField;

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("Create", null, panel_1, null);
        panel_1.setLayout(null);
        panel_1.setBackground(SystemColor.menu);

        JLabel label_1 = new JLabel("ISBN:");
        label_1.setBounds(10, 14, 46, 14);
        panel_1.add(label_1);

        ISBNfield = new JTextField();
        ISBNfield.setColumns(10);
        ISBNfield.setBounds(66, 11, 181, 20);
        panel_1.add(ISBNfield);

        JLabel label_2 = new JLabel("Title:");
        label_2.setBounds(10, 44, 46, 14);
        panel_1.add(label_2);

        titleField = new JTextField();
        titleField.setColumns(10);
        titleField.setBounds(66, 41, 181, 20);
        panel_1.add(titleField);

        authorField = new JTextField();
        authorField.setColumns(10);
        authorField.setBounds(66, 72, 181, 20);
        panel_1.add(authorField);

        JLabel label_3 = new JLabel("Author:");
        label_3.setBounds(10, 75, 46, 14);
        panel_1.add(label_3);

        JLabel label_4 = new JLabel("Publisher:");
        label_4.setBounds(274, 14, 60, 14);
        panel_1.add(label_4);

        JLabel label_5 = new JLabel("Genre:");
        label_5.setBounds(273, 44, 46, 14);
        panel_1.add(label_5);

        JLabel label_6 = new JLabel("Keywords:");
        label_6.setBounds(273, 75, 61, 14);
        panel_1.add(label_6);

        publisherField = new JTextField();
        publisherField.setColumns(10);
        publisherField.setBounds(344, 11, 194, 20);
        panel_1.add(publisherField);

        genreField = new JTextField();
        genreField.setColumns(10);
        genreField.setBounds(344, 41, 194, 20);
        panel_1.add(genreField);

        keywordsField = new JTextField();
        keywordsField.setColumns(10);
        keywordsField.setBounds(344, 72, 194, 20);
        panel_1.add(keywordsField);

        JButton createBookButton = new JButton("Create Book");
        createBookButton.setBounds(224, 144, 110, 23);
        panel_1.add(createBookButton);

        JLabel label_7 = new JLabel("Quantity:");
        label_7.setBounds(10, 106, 46, 14);
        panel_1.add(label_7);

        quantityField = new JTextField();
        quantityField.setColumns(10);
        quantityField.setBounds(66, 103, 181, 20);
        panel_1.add(quantityField);

        createBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String ISBN=ISBNfield.getText().trim();
                String title=titleField.getText().trim();
                String author=authorField.getText().trim();
                String publisher=publisherField.getText().trim();
                String genre=genreField.getText().trim();
                String keywords=keywordsField.getText().trim();
                String quantity=quantityField.getText().trim();

                try {
                    if(!(BookController.getInstance().checkFields(ISBN, title, author, publisher,
                            genre, keywords, quantity)))
                        throw new Exception("You should fill the field(s).");

                    if(!Validation.isISBNValid(ISBN))
                        throw new Exception("ISBN is wrong format.");

                    if(!(BookController.getInstance().checkQuantityFormat(quantity)))
                        throw new Exception("Quantity is wrong format.");

                    if(BookController.getInstance().getBook(ISBN)!=null)
                        throw new Exception("There is a book for this ISBN number.");

                    if(!(BookController.getInstance().addBook(ISBN, title, author, publisher, genre, keywords, quantity,"0")))
                        throw new Exception("The book could not be added.");
                    JOptionPane.showMessageDialog(frame,"Book was added.",
                            "Success",JOptionPane.INFORMATION_MESSAGE);
                    BookController.getInstance().setDefaultFieldValues(ISBNfield,titleField,authorField,
                            publisherField,genreField, keywordsField, quantityField);

                }catch (Exception e){
                    JOptionPane.showMessageDialog(frame,
                            e.getMessage(),
                            "Error",
                            JOptionPane.WARNING_MESSAGE);

                    BookController.getInstance().setDefaultFieldValues(ISBNfield,titleField,authorField,
                            publisherField,genreField, keywordsField, quantityField);
                    return;
                }
            }
        });
    }
    private void initUpdateTab(JTabbedPane tabbedPane) {

        final JTextField ISBNfield, titleField, authorField;
        final JTextField publisherField, genreField, keywordsField, quantityField;

        JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        tabbedPane.addTab("Update", null, panel_2, null);

        JButton getBookButton = new JButton("Get Book");
        getBookButton.setBounds(105, 134, 110, 23);
        panel_2.add(getBookButton);

        final JButton updateBookButton = new JButton("Update Book");
        updateBookButton.setBounds(386, 134, 110, 23);
        panel_2.add(updateBookButton);

        JPanel panel_3 = new JPanel();
        panel_3.setLayout(null);
        panel_3.setBorder(new TitledBorder(null, "Book Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_3.setBounds(10, 204, 539, 52);
        panel_2.add(panel_3);

        JLabel label_14 = new JLabel("Book is not taken by any customer");
        label_14.setBounds(10, 23, 449, 14);
        panel_3.add(label_14);

        JLabel label_8 = new JLabel("ISBN:");
        label_8.setBounds(10, 14, 46, 14);
        panel_2.add(label_8);

        ISBNfield = new JTextField();
        ISBNfield.setColumns(10);
        ISBNfield.setBounds(66, 11, 181, 20);
        panel_2.add(ISBNfield);

        JLabel label_9 = new JLabel("Title:");
        label_9.setBounds(10, 44, 46, 14);
        panel_2.add(label_9);

        titleField = new JTextField();
        titleField.setColumns(10);
        titleField.setBounds(66, 41, 181, 20);
        panel_2.add(titleField);

        authorField = new JTextField();
        authorField.setColumns(10);
        authorField.setBounds(66, 72, 181, 20);
        panel_2.add(authorField);

        JLabel label_10 = new JLabel("Author:");
        label_10.setBounds(10, 75, 46, 14);
        panel_2.add(label_10);

        JLabel label_11 = new JLabel("Publisher:");
        label_11.setBounds(274, 14, 60, 14);
        panel_2.add(label_11);

        JLabel label_12 = new JLabel("Genre:");
        label_12.setBounds(273, 44, 46, 14);
        panel_2.add(label_12);

        JLabel label_13 = new JLabel("Keywords:");
        label_13.setBounds(273, 75, 61, 14);
        panel_2.add(label_13);

        publisherField = new JTextField();
        publisherField.setColumns(10);
        publisherField.setBounds(344, 11, 194, 20);
        panel_2.add(publisherField);

        genreField = new JTextField();
        genreField.setColumns(10);
        genreField.setBounds(344, 41, 194, 20);
        panel_2.add(genreField);

        keywordsField = new JTextField();
        keywordsField.setColumns(10);
        keywordsField.setBounds(344, 72, 194, 20);
        panel_2.add(keywordsField);

        JLabel label_15 = new JLabel("Quantity:");
        label_15.setBounds(10, 106, 46, 14);
        panel_2.add(label_15);

        quantityField = new JTextField();
        quantityField.setColumns(10);
        quantityField.setBounds(66, 103, 181, 20);
        panel_2.add(quantityField);

        updateBookButton.setEnabled(false);
        getBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String ISBN=ISBNfield.getText().trim();
                //String title=titleField.getText();
                //String author=authorField.getText();
                //String quantity=quantityField.getText();

                try {
                    if(ISBN.isEmpty())
                        throw new Exception("You should fill the ISBN field.");

                    if(!Validation.isISBNValid(ISBN))
                        throw new Exception("ISBN is wrong format.");

                    Book book=BookController.getInstance().getBook(ISBN);
                    if(book==null)
                        throw new Exception("The book could not find.");

                    titleField.setText(book.getTitle());
                    authorField.setText(book.getAuthor());
                    publisherField.setText(book.getPublisher());
                    genreField.setText(book.getGenre());
                    keywordsField.setText(book.getKeywords());
                    quantityField.setText(String.valueOf(book.getQuantity()));

                    updateBookButton.putClientProperty("id", book.getId());
                    updateBookButton.setEnabled(true);

                }catch (Exception e2){
                    JOptionPane.showMessageDialog(frame,
                            e2.getMessage(),
                            "Error",
                            JOptionPane.WARNING_MESSAGE);

                    BookController.getInstance().setDefaultFieldValues(ISBNfield,titleField,authorField,
                            publisherField,genreField, keywordsField, quantityField);
                    return;
                }
            }
        });

        updateBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String ISBN=ISBNfield.getText().trim();
                String title=titleField.getText().trim();
                String author=authorField.getText().trim();
                String publisher=publisherField.getText().trim();
                String genre=genreField.getText().trim();
                String keywords=keywordsField.getText().trim();
                String quantity=quantityField.getText().trim();

                try {
                    if(!(BookController.getInstance().checkFields(ISBN, title, author, publisher,
                            genre, keywords, quantity)))
                        throw new Exception("You should fill the field(s).");

                    if(!Validation.isISBNValid(ISBN))
                        throw new Exception("ISBN is wrong format.");

                    if(!(BookController.getInstance().checkQuantityFormat(quantity)))
                        throw new Exception("Quantity is wrong format.");

                    if(!(BookController.getInstance().updateBook((String.valueOf(updateBookButton.getClientProperty("id")))
                            ,ISBN, title, author, publisher, genre, keywords, quantity,"0")))
                        throw new Exception("The book could not be updated.");

                    JOptionPane.showMessageDialog(frame,"Book was updated.",
                            "Success",JOptionPane.INFORMATION_MESSAGE);

                    BookController.getInstance().setDefaultFieldValues(ISBNfield,titleField,authorField,
                            publisherField,genreField, keywordsField, quantityField);

                }catch (Exception e3){
                    JOptionPane.showMessageDialog(frame,
                            e3.getMessage(),
                            "Error",
                            JOptionPane.WARNING_MESSAGE);

                    BookController.getInstance().setDefaultFieldValues(ISBNfield,titleField,authorField,
                            publisherField,genreField, keywordsField, quantityField);
                    return;
                }
            }
        });

    }
    private void initDeleteTab(JTabbedPane tabbedPane) {

        final JTable table;
        final JTextField ISBNfield;

        JPanel panel_4 = new JPanel();
        panel_4.setLayout(null);
        tabbedPane.addTab("Delete", null, panel_4, null);

        JLabel label_16 = new JLabel("ISBN:");
        label_16.setBounds(164, 14, 33, 14);
        panel_4.add(label_16);

        ISBNfield = new JTextField();
        ISBNfield.setColumns(10);
        ISBNfield.setBounds(207, 11, 163, 20);
        panel_4.add(ISBNfield);

        JButton getBookButton = new JButton("Get Book");
        getBookButton.setBounds(164, 128, 89, 23);
        panel_4.add(getBookButton);

        final JButton deleteBookButton = new JButton("Delete Book");
        deleteBookButton.setBounds(281, 128, 89, 23);
        panel_4.add(deleteBookButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 57, 539, 43);
        panel_4.add(scrollPane);

        String[] columnNames = { "Title", "ISBN", "Author", "Genre"};
        DefaultTableModel model = new DefaultTableModel(1, columnNames.length) ;
        model.setColumnIdentifiers(columnNames);

        table = new JTable(model)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        scrollPane.setViewportView(table);


        deleteBookButton.setEnabled(false);
        getBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try{
                    String ISBN=ISBNfield.getText().trim();

                    if(ISBN.isEmpty())
                        throw new Exception("You should fill the ISBN.");

                    if(!Validation.isISBNValid(ISBN))
                        throw new Exception("ISBN is wrong format.");

                    Book book=BookController.getInstance().getBook(ISBN);
                    if(book==null)
                        throw new Exception("The book could not find.");

                    if(table.getModel().getRowCount()==1)
                        ((DefaultTableModel) table.getModel()).removeRow(0);
                    ((DefaultTableModel) table.getModel()).addRow(
                            new Object[]{book.getTitle(), book.getISBN(), book.getAuthor(), book.getGenre()});

                    deleteBookButton.putClientProperty("id", book.getId());
                    deleteBookButton.setEnabled(true);

                }catch (Exception e1){
                    JOptionPane.showMessageDialog(frame,
                            e1.getMessage(),
                            "Error",
                            JOptionPane.WARNING_MESSAGE);

                    ISBNfield.setText("");
                    return;
                }
            }
        });

        deleteBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try{
                    String ISBN=ISBNfield.getText().trim();
                    Book book=BookController.getInstance().getBook(ISBN);

                    if(BookController.getInstance().getTakenBookCount(book.getId())>0){
                        throw new Exception("A user has got this book.");
                    }
                    if(!(BookController.getInstance().deleteBook(String.valueOf(deleteBookButton.getClientProperty("id")))))
                        throw new Exception("The book could not be deleted.");

                    JOptionPane.showMessageDialog(frame,"Book was deleted.",
                            "Success",JOptionPane.INFORMATION_MESSAGE);
                    ISBNfield.setText("");
                    ((DefaultTableModel) table.getModel()).removeRow(0);
                    deleteBookButton.setEnabled(false);

                }catch (Exception e){
                    JOptionPane.showMessageDialog(frame,
                            e.getMessage(),
                            "Error",
                            JOptionPane.WARNING_MESSAGE);

                    ISBNfield.setText("");
                    ((DefaultTableModel) table.getModel()).removeRow(0);
                    deleteBookButton.setEnabled(false);
                    return;
                }

            }
        });

    }
    private void initViewTab(JTabbedPane tabbedPane) {

        final JTable table;
        final JTextField ISBNfield;

        JPanel panel_5 = new JPanel();
        tabbedPane.addTab("View", null, panel_5, null);
        panel_5.setLayout(null);

        JPanel panel_6 = new JPanel();
        panel_6.setLayout(null);
        panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Book", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_6.setBounds(10, 11, 539, 89);
        panel_5.add(panel_6);

        JLabel lblIsbn = new JLabel("ISBN:");
        lblIsbn.setBounds(34, 26, 100, 14);
        panel_6.add(lblIsbn);

        ISBNfield = new JTextField();
        ISBNfield.setColumns(10);
        ISBNfield.setBounds(144, 23, 314, 20);
        panel_6.add(ISBNfield);

        JButton getBookButton = new JButton("Get Book");
        getBookButton.setBounds(369, 54, 89, 23);
        panel_6.add(getBookButton);

        JPanel panel_7 = new JPanel();
        panel_7.setLayout(null);
        panel_7.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_7.setBounds(10, 111, 539, 145);
        panel_5.add(panel_7);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 21, 519, 113);
        panel_7.add(scrollPane_1);

        //String[] columnNames_1 = { "Title", "Author", "Genre", "Owner Name", "Owner Email"};
        String[] columnNames_1 = { "ISBN", "Author", "Quantity", "Publisher", "Genre"};
        DefaultTableModel model2 = new DefaultTableModel(1, columnNames_1.length) ;
        model2.setColumnIdentifiers(columnNames_1);

        table = new JTable(model2)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        scrollPane_1.setViewportView(table);


        getBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try{
                    String ISBN=ISBNfield.getText().trim();

                    if(ISBN.isEmpty())
                        throw new Exception("You should fill the ISBN.");

                    if(!Validation.isISBNValid(ISBN))
                        throw new Exception("ISBN is wrong format.");

                    Book book=BookController.getInstance().getBook(ISBN);
                    if(book==null)
                        throw new Exception("The book could not find.");

                    ((DefaultTableModel) table.getModel()).removeRow(0);
                    ((DefaultTableModel) table.getModel()).addRow(
                            new Object[]{book.getISBN(), book.getAuthor(), book.getQuantity(), book.getPublisher(),
                                    book.getGenre(), book.getKeywords()});

                }catch (Exception e1){
                    JOptionPane.showMessageDialog(frame,
                            e1.getMessage(),
                            "Error",
                            JOptionPane.WARNING_MESSAGE);

                    ISBNfield.setText("");
                    return;
                }
            }
        });
    }

    public JFrame getFrame() {
        return frame;
    }
}
