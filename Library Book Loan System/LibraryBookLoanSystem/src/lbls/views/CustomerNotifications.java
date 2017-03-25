package lbls.views;
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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CustomerNotifications {

	private JFrame frame;
	private JTable table;

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
					CustomerNotifications window = new CustomerNotifications();
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
	public CustomerNotifications() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Library Book Loan System - Notifications");
		frame.setBounds(100, 100, 450, 300);
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
		
		Object columnNames[] = { "Title", "ISBN", "Author", "Genre" };
		Object rowData[][] = { {"Example Book One", "111", "Writer 1", "Sci-fi"},
							   {"Example Book Two", "222", "Writer 2", "Action"}};
		
		table = new JTable(rowData, columnNames)
		{
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;//super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
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


		scrollPane.setViewportView(table);
	}

}
