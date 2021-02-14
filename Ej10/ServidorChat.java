package Ej10;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorChat {
	private static ServerSocket serverAddr = null;
	private static ServerSocket serverAddr1 = null;
	private static Socket sc= null;
	private static Socket sc2= null;
	private static ArrayList<Socket> arraySocket = null;
	
	
	public static void main ( String [] args) {
		arraySocket=new ArrayList<Socket>();
		MonitorSockets monitor= new MonitorSockets(arraySocket);
		try {
			//Crear un hilo el cual espera a que alguien se desconecte
			serverAddr = new ServerSocket(2510);
			serverAddr1 = new ServerSocket(2515);
			while (true) {
				
				sc=serverAddr.accept();
				sc2=serverAddr1.accept();
				monitor.getArraySocket().add(sc2);
				ServidorRecibirMandar c=new ServidorRecibirMandar(sc,monitor);
		        c.start();
			}
		
			//cuando se manda un mensaje se ejecuta un hilo el cual manda los mensajes
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}


	
	
	
}
