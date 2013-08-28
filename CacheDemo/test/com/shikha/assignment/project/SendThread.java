package com.shikha.assignment.project;

import java.awt.TextArea;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SendThread extends Thread {

	Socket s1;
	OutputStream slout;
	DataOutputStream dos;
	String sendString;
	TextArea ChatHist;
	TextArea SndMsg;
	private volatile boolean wait = true;
	private volatile boolean keepListening = true;
	
	String myName;

	public SendThread(Socket s, TextArea sm, TextArea ch) {
		this.s1 = s;
		this.SndMsg = sm;
		this.ChatHist = ch;
	}

	public void run() {
		while (keepListening) {
			while (wait) {
				// wait till send button is pressed
			}
			try {
				wait = true;
				sendString = SndMsg.getText();
				// Get a communication stream associate with the socket
				slout = s1.getOutputStream();

				dos = new DataOutputStream(slout);
				// send your string!
				try {
					myName = System.getProperty("user.name", "Jha");
				} catch (Exception e) {
				}
				dos.writeUTF(myName + ": " + sendString);
				ChatHist.append(myName + ": " + sendString + "\n");
				SndMsg.setText("");

			} catch (Exception e) {
				try {
					slout.close();
					dos.close();
				} catch (Exception e1) {
					System.out.println("Server: Cant Send, Some Problem");
				}
				ChatHist.append("Connection refused. Failed to send: "+sendString);
			}// catch ends

		}// loop ends
	}// method run ends

	public void setWait(boolean b) {
		wait = b;
	}
	
	public void setListening(boolean b)
	{
		keepListening = b;
	}

}
