package Ej10;

import java.net.Socket;
import java.util.ArrayList;

public class MonitorSockets {
	private static ArrayList<Socket> arraySocket = null;
	public MonitorSockets(ArrayList<Socket> arraySocket) {
		this.arraySocket=arraySocket;
	}
	public  synchronized ArrayList<Socket> getArraySocket() {
		return arraySocket;
	}
	public  synchronized void setArraySocket(ArrayList<Socket> arraySocket) {
		MonitorSockets.arraySocket = arraySocket;
	}

	
	
}
