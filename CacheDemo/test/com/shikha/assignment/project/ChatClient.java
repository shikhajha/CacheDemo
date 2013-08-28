package com.shikha.assignment.project;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ChatClient extends AbstractChat

{

	public ChatClient(String name) {
		super(name);
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void startChat() {
		Socket s1 = null;
		String ipAddress = null;
		InetAddress inet = null;

		// Open your connection to remote host at port 5432

		try {
			ipAddress = JOptionPane
					.showInputDialog("Enter IP Address of remote chat host OR if local then type localhost");
			inet = InetAddress.getByName(ipAddress);
			if (!inet.isReachable(5 * 1000)) {
				JOptionPane.showMessageDialog(null, "Could not connect to " + ipAddress);
				return;
			}
			
			s1 = new Socket(inet, 5432);
			sender = new SendThread(s1, sendMessageTextArea, chatHistTextArea);

			receiver = new ReceiveThread(s1, chatHistTextArea);

			receiver.start();
			sender.start();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void close() {
		receiver.setListening(false);
		sender.setListening(false);
		super.close();
	}

	public static void main(String args[]) throws IOException {
		ChatClient cc = new ChatClient("Client");
		cc.startChat();

	}

}
