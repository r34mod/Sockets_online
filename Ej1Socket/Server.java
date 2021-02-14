package Ej1Socket;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main ( String [] args)
	{
	ServerSocket serverAddr = null;
	Socket sc = null;
	
	int num[] ; // petición
	int res;
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
			num = (int[]) in.readObject();
			res = num[0] + num[1];
			
			DataOutputStream ostream = new DataOutputStream(sc.getOutputStream());
			ostream.writeInt(res);
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
