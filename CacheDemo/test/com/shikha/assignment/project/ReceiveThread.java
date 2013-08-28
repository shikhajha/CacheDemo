package com.shikha.assignment.project;

import java.awt.TextArea;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class ReceiveThread extends Thread {

	InputStream sin;
	DataInputStream dis;
	Socket s1 = null;
	TextArea ChatHist;
	private volatile boolean keepListening = true;

	public ReceiveThread(Socket s, TextArea ch) {
		this.s1 = s;
		this.ChatHist = ch;
	}

	public void run() {
		while (keepListening) {
			try {
				sin = s1.getInputStream();
				dis = new DataInputStream(sin);
				String strRecdData = new String(dis.readUTF());

				ChatHist.append(strRecdData + "\n");

			} catch (Exception e) {
				try {
					sin.close();
					dis.close();
				} catch (Exception e1) {
				}
				System.out.println("connection failed");
				keepListening = false;
			}// catch ends
		}// loop ends
	}// method run ends
	
	public void setListening(boolean b)
	{
		keepListening = b;
	}

}
