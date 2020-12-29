package com.kcx.zookeeperDemo.connectDemo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

import org.apache.zookeeper.KeeperException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class ZooThreadDemo {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnSetdata;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZooThreadDemo window = new ZooThreadDemo();
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
	public ZooThreadDemo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 912, 513);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnGetdata = new JButton("getData");
		btnGetdata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGetdata.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ZKGetData getData = new ZKGetData();
				String path = textField_1.getText();
				try {
					String data = getData.get(path);
					textField.setText(data);
				} catch (KeeperException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnGetdata.setBounds(128, 181, 105, 28);
		frame.getContentPane().add(btnGetdata);
		
		textField = new JTextField();
		textField.setBounds(245, 178, 151, 35);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPath = new JLabel("path:");
		lblPath.setBounds(291, 61, 59, 18);
		frame.getContentPane().add(lblPath);
		
		textField_1 = new JTextField();
		textField_1.setBounds(334, 59, 144, 28);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		btnSetdata = new JButton("setData");
		btnSetdata.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String path = textField_1.getText();
				String value = textField_2.getText();
				ZKSetData setData = new ZKSetData();
				try {
					setData.set(path, value);
				} catch (KeeperException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSetdata.setBounds(128, 262, 105, 28);
		frame.getContentPane().add(btnSetdata);
		
		textField_2 = new JTextField();
		textField_2.setBounds(245, 259, 151, 35);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
	}
}
