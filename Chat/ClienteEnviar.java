package Chat;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.SynchronousQueue;

public class ClienteEnviar extends Thread{
	private String introducirTexto;
	private String nombreUsuario;
	private Socket socket;
	private  ObjectOutputStream out;
	public ClienteEnviar(String nombreUsuario, String introducirTexto,ObjectOutputStream out) {
		this.nombreUsuario=nombreUsuario;
		this.introducirTexto=introducirTexto;
		this.out=out;
	}
	@Override
	public void run() {
	   
		
		try {
			
			//madno el mensaje al server
			if(introducirTexto.contentEquals("desconectar")) {
				out.writeUTF(introducirTexto);
				out.flush();
				
			}else {
				out.writeUTF(nombreUsuario+" --> "+introducirTexto);
				out.flush();
				System.out.println("MAndado");
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
