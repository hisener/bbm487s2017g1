package lbls.views;
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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LibrarianUserOperations {

	private JFrame frame;
	private JTextField txtExampledomaimedutr;
	private JTable table;
	private JTable table_1;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
          java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibrarianUserOperations window = new LibrarianUserOperations();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LibrarianUserOperations() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Library Book Loan System - Librarian: %username% ");
		frame.setBounds(100, 100, 550, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Library Book Loan System");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		label.setBounds(10, 11, 514, 25);
		panel.add(label);
		
		JLabel lblBookOperations = new JLabel("User Operations");
		lblBookOperations.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookOperations.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblBookOperations.setBounds(10, 40, 514, 22);
		panel.add(lblBookOperations);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		tabbedPane.setBounds(10, 73, 514, 377);
		panel.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Create", null, panel_1, null);
		panel_1.setLayout(null);
		panel_1.setBackground(SystemColor.menu);
		
		JLabel label_7 = new JLabel("Username:");
		label_7.setBounds(41, 32, 100, 14);
		panel_1.add(label_7);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(151, 29, 321, 20);
		panel_1.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(151, 57, 321, 20);
		panel_1.add(textField_1);
		
		JLabel label_8 = new JLabel("E-mail:");
		label_8.setBounds(41, 60, 100, 14);
		panel_1.add(label_8);
		
		JLabel label_9 = new JLabel("Password:");
		label_9.setBounds(41, 88, 100, 14);
		panel_1.add(label_9);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(151, 85, 321, 20);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(151, 113, 321, 20);
		panel_1.add(textField_3);
		
		JLabel label_10 = new JLabel("Confirm Password:");
		label_10.setBounds(41, 116, 100, 14);
		panel_1.add(label_10);
		
		JButton button = new JButton("Add User");
		button.setBounds(151, 159, 100, 23);
		panel_1.add(button);
		
		JButton button_1 = new JButton("Reset Fields");
		button_1.setBounds(261, 159, 100, 23);
		panel_1.add(button_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		tabbedPane.addTab("Update", null, panel_2, null);
		
		JButton btnUpdateUser = new JButton("Update User");
		btnUpdateUser.setBounds(261, 159, 100, 23);
		panel_2.add(btnUpdateUser);
		
		JButton btnGetUser = new JButton("Get User");
		btnGetUser.setBounds(151, 159, 100, 23);
		panel_2.add(btnGetUser);
		
		JLabel label_1 = new JLabel("Username:");
		label_1.setBounds(41, 32, 100, 14);
		panel_2.add(label_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(151, 29, 321, 20);
		panel_2.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(151, 57, 321, 20);
		panel_2.add(textField_5);
		
		JLabel label_2 = new JLabel("E-mail:");
		label_2.setBounds(41, 60, 100, 14);
		panel_2.add(label_2);
		
		JLabel label_3 = new JLabel("Password:");
		label_3.setBounds(41, 88, 100, 14);
		panel_2.add(label_3);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(151, 85, 321, 20);
		panel_2.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(151, 113, 321, 20);
		panel_2.add(textField_7);
		
		JLabel label_4 = new JLabel("Confirm Password:");
		label_4.setBounds(41, 116, 100, 14);
		panel_2.add(label_4);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		tabbedPane.addTab("Delete", null, panel_4, null);
		
		JLabel lblEmail_1 = new JLabel("Email:");
		lblEmail_1.setBounds(10, 14, 33, 14);
		panel_4.add(lblEmail_1);
		
		txtExampledomaimedutr = new JTextField();
		txtExampledomaimedutr.setText("example@domaim.edu.tr");
		txtExampledomaimedutr.setColumns(10);
		txtExampledomaimedutr.setBounds(125, 11, 374, 20);
		panel_4.add(txtExampledomaimedutr);
		
		JButton btnGetBook = new JButton("Get User");
		btnGetBook.setBounds(126, 128, 89, 23);
		panel_4.add(btnGetBook);
		
		JButton btnDeleteBook = new JButton("Delete User");
		btnDeleteBook.setBounds(303, 128, 89, 23);
		panel_4.add(btnDeleteBook);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 489, 43);
		panel_4.add(scrollPane);
		
		Object columnNames[] = { "Username", "Email"};
		Object rowData[][] = { {"Example Username", "example@domain.edu.tr"} };
		
		table = new JTable(rowData, columnNames)
		{
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		
		table.getTableHeader().setReorderingAllowed(false);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);

		List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
		scrollPane.setViewportView(table);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("View", null, panel_3, null);
		panel_3.setLayout(null);
		
		Object columnNames_1[] = { "Title", "ISBN", "Author", "Genre"};
		Object rowData_1[][] = { {"Example Book One", "111", "Writer 1", "Sci-fi"}, {"Example Book Two", "222", "Writer 2", "History"},
				{"Example Book Three", "333", "Writer 3", "Crime"}};

		List<RowSorter.SortKey> sortKeys_2 = new ArrayList<>(25);
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys_2);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "User", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(10, 11, 489, 125);
		panel_3.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel label_5 = new JLabel("E-mail:");
		label_5.setBounds(34, 54, 100, 14);
		panel_5.add(label_5);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(144, 51, 314, 20);
		panel_5.add(textField_8);
		
		JLabel label_6 = new JLabel("Username:");
		label_6.setBounds(34, 26, 100, 14);
		panel_5.add(label_6);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(144, 23, 314, 20);
		panel_5.add(textField_9);
		
		JButton btnGetUser_1 = new JButton("Get User");
		btnGetUser_1.setBounds(369, 82, 89, 23);
		panel_5.add(btnGetUser_1);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Books", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.setBounds(10, 148, 489, 190);
		panel_3.add(panel_6);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 25, 469, 134);
		panel_6.add(scrollPane_1);
		
		table_1 = new JTable(rowData_1, columnNames_1)
		{
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		
		table_1.getTableHeader().setReorderingAllowed(false);
		TableRowSorter<TableModel> sorter_2 = new TableRowSorter<TableModel>(table_1.getModel());
		table_1.setRowSorter(sorter_2);

		
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblNewLabel = new JLabel("3 Book(s)");
		lblNewLabel.setBounds(433, 165, 46, 14);
		panel_6.add(lblNewLabel);
		
		
	}
}
