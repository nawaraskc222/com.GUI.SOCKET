package com.Server.GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client {

	
	
	private JFrame frame;
	private static JTextField textField;
	private JLabel lblNewLabel;
	private static JTextArea textArea;
	
	public static Socket s;
	public static ServerSocket ss;
	
	public static DataInputStream din;
	public static DataOutputStream dout;
	public static BufferedReader br;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		try {
			s= new Socket("localhost",1717);
			din= new DataInputStream(new DataInputStream(s.getInputStream()));
			dout= new DataOutputStream(new DataOutputStream(s.getOutputStream()));
			br=new BufferedReader(new InputStreamReader(System.in));
			
			String msg="";
			while(!msg.equals("bye")) {
				msg=din.readUTF();
				textArea.append("\nServer: "+msg);
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Mr.Nobody\\Downloads\\4703650.png"));
		frame.setForeground(new Color(0, 0, 139));
		frame.setTitle("Client");
		frame.getContentPane().setBackground(new Color(0, 128, 128));
//		frame.setBounds(X100, Y100, wid420, heihg40);
		frame.setBounds(100, 100, 690, 475);
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		 textArea = new JTextArea();
		 textArea.setBounds(10, 10, 656, 384);
		
		frame.getContentPane().add(textArea);
		
		textField = new JTextField();
		
		textField.setBounds(148, 404, 420, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.setForeground(new Color(0, 0, 153));
		btnNewButton.setFont(new Font("Sitka Small", Font.PLAIN, 18));
		btnNewButton.setBackground(new Color(204, 204, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					String msgout="";
					msgout=textField.getText().trim();
					textArea.append("\nClientMe: "+msgout);
					dout.writeUTF(msgout);
					clear();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(581, 404, 85, 27);
		frame.getContentPane().add(btnNewButton);
		
		lblNewLabel = new JLabel("Enter message..");
		lblNewLabel.setFont(new Font("Sitka Small", Font.PLAIN, 15));
		lblNewLabel.setForeground(new Color(0, 0, 153));
		lblNewLabel.setBounds(new Rectangle(0, 0, 6, 6));
		lblNewLabel.setBackground(new Color(47, 79, 79));
		lblNewLabel.setBounds(20, 397, 128, 41);
		frame.getContentPane().add(lblNewLabel);
	}
	
public static void clear() {
		
		textField.setText(null);
	}

}
