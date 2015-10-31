package com.sty.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import javax.swing.text.AbstractDocument.BranchElement;

public class TransferSocket extends Thread{
	
	Socket mSocket = null;
	public TransferSocket(Socket s){
		this.mSocket = s;
		
	}
	public void WriteSocket(String s) {
		try {
			mSocket.getOutputStream().write((s+"\n").getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TransferManager.getIntance().Remove(this);
		}
	}
	
	public String ReadSocket() {

		String string = null;
		try {
			BufferedReader br = new BufferedReader(
												new InputStreamReader(
														mSocket.getInputStream(),"UTF-8" ));
		
			string = br.readLine();
			br.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return string;
	}
	
	@Override
	public void run() {
		int count = 0;
		while (true) {

//			WriteSocket("LOOP+"+count+"\n");
			
			try {
				BufferedReader br = new BufferedReader(
													new InputStreamReader(
															mSocket.getInputStream(),"UTF-8" ));
				String line  =	null;			
				while ((line = br.readLine())!=null) {
					TransferManager.getIntance().Publish(this, line);
				}
				br.close();
			//	TransferManager.getIntance().Remove(this);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			

		}

	}

}
