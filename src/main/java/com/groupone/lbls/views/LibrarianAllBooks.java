package com.groupone.lbls.views;
import com.groupone.lbls.controller.BookController;
import com.groupone.lbls.model.Book;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LibrarianAllBooks {

    private JFrame frame;
    private JTable table;

    /**
     * Create the application.
     */
    public LibrarianAllBooks() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame("Library Book Loan System - View All Books");
        frame.setResizable(false);
        frame.setBounds(100, 100, 588, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
        JLabel lblAllBooks = new JLabel("All Books");
        lblAllBooks.setHorizontalAlignment(SwingConstants.CENTER);
        lblAllBooks.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
        lblAllBooks.setBounds(10, 40, 564, 22);
        panel.add(lblAllBooks);
        
        JLabel label_1 = new JLabel("Library Book Loan System");
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
        label_1.setBounds(10, 11, 564, 25);
        panel.add(label_1);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 73, 564, 257);
        panel.add(scrollPane);

        String[] columnNames= { "Title", "ISBN", "Author", "Genre"};
        DefaultTableModel model = new DefaultTableModel(1, columnNames.length);
        model.setColumnIdentifiers(columnNames);

        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        scrollPane.setViewportView(table);

        ArrayList<Book> books= BookController.getInstance().getAllBooks();

        ((DefaultTableModel) table.getModel()).removeRow(0);

        for (Book book : books) {
            ((DefaultTableModel) table.getModel()).addRow(
                    new Object[]{book.getTitle(), book.getISBN(), book.getAuthor(), book.getGenre()});
        }


        JLabel lblBooks = new JLabel(books.size()+" Book(s)");
        lblBooks.setBounds(528, 336, 46, 14);
        panel.add(lblBooks);
    }

    public JFrame getFrame() {
        return frame;
    }

}
