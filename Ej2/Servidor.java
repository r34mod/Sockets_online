package Ej2;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main ( String [] args)
	{
	ServerSocket serverAddr = null;
	Socket sc = null;
	//Scanner scn = new Scanner(System.in);
	String texto;
	String txt;
		try {
			serverAddr = new ServerSocket(2500);
		}
		catch (Exception e){
			System.err.println("Error creando socket");
		}
		while (true){
			try {
				
			sc = serverAddr.accept(); // esperando conexión
			InputStream istream = sc.getInputStream();
			ObjectInput in = new ObjectInputStream(istream);
			texto = (String) in.readObject();
			txt = texto;
			
			DataOutputStream ostream = new DataOutputStream(sc.getOutputStream());
			ostream.writeUTF(txt);
			ostream.flush();
			sc.close();
			}
			catch(Exception e) {
			System.err.println("excepcion " + e.toString() );
			e.printStackTrace() ;
			}
			}
		
	}
}
