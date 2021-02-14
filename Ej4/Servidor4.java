package Ej4;

import java.io.File;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor4 {

	public static void main ( String [] args) {
		ServerSocket serverAddr = null;
		Socket sc= null;
		int[] num ;  
		// petici�n
		int res;
		String []mandar;
		try {
			serverAddr = new ServerSocket(2502);
			}   catch (Exception e){
				System.err.println("Error creando socket");
				}
		while (true){
			try {
				sc = serverAddr.accept();
				// esperando conexi�n
				System.out.println("Conectado");
				InputStream istream = sc.getInputStream();
				ObjectInput in = new ObjectInputStream(istream);
				//recibe la palabra
				num =(int[]) in.readObject();
				res=num[0];
				//verificar si el fichero existe
				if (res==1) {
					File fichero=new File("datos");
					mandar=fichero.list();
				}else {
					mandar=null;
				}
				
				
				ObjectOutputStream ostream = new ObjectOutputStream(sc.getOutputStream());
				ostream.writeObject(mandar);
				ostream.flush();
				sc.close();
				} catch(Exception e) {
					System.err.println("excepcion " + e.toString() );
					e.printStackTrace() ;}
			}
		}
}
