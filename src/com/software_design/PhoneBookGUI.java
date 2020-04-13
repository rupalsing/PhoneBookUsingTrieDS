package com.software_design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class PhoneBookGUI {

	private JFrame frame;
	private JTextField txtSearch;
	private JTable table;
	private DefaultTableModel model;
	private JTextField enteredName;
	private JTextField enteredNumber;
	
	private PhoneBook phoneBook = new PhoneBook();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhoneBookGUI window = new PhoneBookGUI();
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
	public PhoneBookGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setToolTipText("Search");
		
		txtSearch.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				showResult(phoneBook.search(txtSearch.getText()));
			}
			
		});
		txtSearch.setBounds(98, 37, 354, 26);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(98, 106, 354, 378);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable(
				new DefaultTableModel(new Object[][] {}, new String[] { "Name", "Phone No."}));
		scrollPane.setViewportView(table);
		
		
		enteredName = new JTextField();
		enteredName.setToolTipText("Enter Name");
		enteredName.setBounds(98, 525, 167, 26);
		frame.getContentPane().add(enteredName);
		enteredName.setColumns(10);
		
		
		enteredNumber = new JTextField();
		enteredNumber.setToolTipText("Enter Contact");
		enteredNumber.setBounds(280, 525, 172, 26);
		frame.getContentPane().add(enteredNumber);
		enteredNumber.setColumns(10);
		
		JButton btnInsert = new JButton("Save new contact");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				phoneBook.insert(enteredName.getText(),Long.parseLong(enteredNumber.getText()));
				showResult(phoneBook.search(txtSearch.getText()));
			}
		});
		btnInsert.setBounds(202, 563, 167, 29);
		frame.getContentPane().add(btnInsert);
		
	}
	
	public void showResult(LinkedList<String> contacts){
		model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		try {
			for (String data : contacts) {
				model.addRow(new Object[] { data, phoneBook.getNumber(data) });
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
