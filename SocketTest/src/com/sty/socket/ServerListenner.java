package com.sty.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerListenner extends Thread {

	
	public void run() {
		try {
			
			
			ServerSocket SockerListenner = new ServerSocket(12345);
			int i = 0;
			
			while (true) {
				Socket socket = SockerListenner.accept();
				System.out.println("---"+ ++i);
				
				TransferSocket ts  =new TransferSocket(socket);
				ts.start();
				TransferManager.getIntance().Add(ts);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
