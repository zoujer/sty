package com.sty.socket;

import java.util.Vector;

public class TransferManager {
	private TransferManager(){}
	private static TransferManager sTransferManager = new TransferManager();
	public static TransferManager getIntance() {
		return sTransferManager;
	}
	
	Vector<TransferSocket> vector = new Vector<TransferSocket>();
	
	public void Add(TransferSocket ts) {
		vector.add(ts);
	}
	
	public void Remove(TransferSocket ts) {
		vector.remove(ts);
	}
	
	
	public void Publish(TransferSocket ts,String s) {
		for (int i = 0; i < vector.size(); i++) {
			TransferSocket temp = vector.get(i);
			if (ts.equals(temp)) {
				ts.WriteSocket(s);
			}
		}
	}
}
