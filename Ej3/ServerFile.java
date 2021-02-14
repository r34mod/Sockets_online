package Ej3;

import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFile {

	public static void main(String [] args) {
		ServerSocket serverAddr = null;
		
		Socket sc = null;
		String texto;
		String txt;
		try {
			serverAddr= new ServerSocket(2500);
		}catch(Exception e) {
			e.getCause();
		}
		while (true){
			try {
				
			sc = serverAddr.accept(); // esperando conexión
			InputStream istream = sc.getInputStream();
			ObjectInput in = new ObjectInputStream(istream);
			texto = (String) in.readObject();
			File f = new File(texto);
			DataOutputStream ostream = new DataOutputStream(sc.getOutputStream());
			if(f.exists() && !f.isDirectory()) { 
				
				ostream.writeUTF("Existe");
				ostream.flush();
				sc.close();
			}else {
				ostream.writeUTF("No existe");
				ostream.flush();
				sc.close();
			}
			
			
			}
			catch(Exception e) {
			System.err.println("excepcion " + e.toString() );
			e.printStackTrace() ;
			}
			}
	}
}
