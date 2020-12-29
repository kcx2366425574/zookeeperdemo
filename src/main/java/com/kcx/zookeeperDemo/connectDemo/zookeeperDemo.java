package com.kcx.zookeeperDemo.connectDemo;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.apache.zookeeper.ClientCnxn;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class zookeeperDemo {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					zookeeperDemo window = new zookeeperDemo();
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
	public zookeeperDemo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 977, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("新建");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ZooThread().run();
			}
		});
		btnNewButton.setBounds(373, 232, 105, 28);
		frame.getContentPane().add(btnNewButton);
	}
}

class ZooThread extends Thread{
	@Override
	public void run() {
		new ZooThreadDemo();
	}
}
