package com.shikha.assignment.project;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class AbstractChat extends JFrame implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Button send;
	protected TextArea sendMessageTextArea;
	protected TextArea chatHistTextArea;
	protected Socket s1 = null;
	protected SendThread sender = null;
	protected ReceiveThread receiver = null;
	protected String name = null;

	public AbstractChat(String name) {
		this.name = name;

		initComponents();

		int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(screen_width / 3, screen_height / 3);

		this.setLayout(new BorderLayout(0,0));
		this.getContentPane().add(chatHistTextArea, BorderLayout.PAGE_START);
		this.getContentPane().add(sendMessageTextArea, BorderLayout.PAGE_END);

		setTitle("..:: Easy Chat "+ name+" ::..");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(416, 438));
		this.pack();
		setLocationRelativeTo(null);

		setVisible(true);
		resize(this);
		repaint();

	}// constructor ends
	
	public abstract void startChat();
	
	public void actionPerformed(ActionEvent ae) {
		this.sender.setWait(false);

	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			Toolkit.getDefaultToolkit().beep();
			this.sender.setWait(false);
		}
	}

	class WindowClose extends WindowAdapter {
		public void windowClosing(WindowEvent we) {
			close();
			
		}

		
	}
	
	public  void close() {
		try {
			close();
			s1.close();
		} catch (Exception e) {
		}
		System.exit(0);
	}
	
	private void initComponents()
	{
		chatHistTextArea = new TextArea("",50,50,TextArea.SCROLLBARS_VERTICAL_ONLY);
		chatHistTextArea.setFont(new Font("Times New roman", Font.BOLD, 12));
		chatHistTextArea.setForeground(Color.black);
		chatHistTextArea.setBackground(Color.white);
		chatHistTextArea.setEditable(false);
		
		sendMessageTextArea = new TextArea("",5,5,TextArea.SCROLLBARS_NONE);
		sendMessageTextArea.setFont(new Font("Times New roman", Font.BOLD, 12));
		sendMessageTextArea.setForeground(Color.black);
		sendMessageTextArea.setBackground(Color.white);
		sendMessageTextArea.setEditable(true);
		sendMessageTextArea.setEnabled(true);
		sendMessageTextArea.addKeyListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {

				resize(e.getComponent());

			}
		});
	}
	
	private void resize(Component e)
	{
		int width = e.getSize().width;
		int height = e.getSize().height;
		chatHistTextArea.setSize(width, height * 7 / 10);
		sendMessageTextArea.setSize(width, height * 3 / 10);
		e.repaint();
	}

}

