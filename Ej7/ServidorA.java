package Ej7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorA {
	
	private static  OutputStream ostream =null;
	private static ServerSocket serverAddr = null;
	private static Socket sc;
	public static void main ( String [] args) {
		try {
			serverAddr = new ServerSocket(2504);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true) {
			
			
			
			try {
				sc = serverAddr.accept();
			    Servidor s = new Servidor(sc);
				s.start();
			}catch (Exception e) {
				// TODO: handle exception
				
			}
	
		}
	}
	
}
