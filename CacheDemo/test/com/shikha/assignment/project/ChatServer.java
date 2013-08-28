package com.shikha.assignment.project;

import java.io.IOException;
import java.net.ServerSocket;

public class ChatServer extends AbstractChat {
	
	public ChatServer(String name) {
		super(name);
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ServerSocket s = null;
	
	public void startChat() {
		try {
			s = new ServerSocket(5432);
			s1 = s.accept();
		} catch (IOException e) {
		}

		sender = new SendThread(s1, sendMessageTextArea, chatHistTextArea);

		receiver = new ReceiveThread(s1, chatHistTextArea);

		receiver.start();
		sender.start();

	}
	
	public void close() {
		receiver.setListening(false);
		sender.setListening(false);
		super.close();
	}
	

	public static void main(String args[]) {

		ChatServer cs = new ChatServer("Server");
		cs.startChat();

	}

}
