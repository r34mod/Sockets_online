package Ej10;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorRecibirMandar extends Thread {
	private Socket socketCliente;
	private String mensaje;
	private static Socket sc= null;
	private static ArrayList<Socket> arraySocket = null;
    private MonitorSockets monitor;
	private ObjectOutputStream out;
	public ServidorRecibirMandar(Socket socket,MonitorSockets m) {
		this.socketCliente=socket;
		this.monitor=m;
	}
	
	
	
	@Override
	public void run() {
		
		ObjectInputStream in;
		InputStream istream;
		try {
			istream = socketCliente.getInputStream();
			in = new ObjectInputStream(istream);
			
			boolean desconectado=false;
			//se lee el mensaje y se manda al otro hilo del servidor para q actualice la ventana
			while (!desconectado) {
				
				mensaje=in.readUTF();
				
				System.out.println("Mensaje "+mensaje);
				if (!mensaje.contentEquals("desconectar")) {
					devolverDatos();
				    System.out.println("Datos devueltos");
				}else {
					desconectado=true;
					monitor.getArraySocket().remove(this.socketCliente);
					
				}
				
				
				
				
				
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

	public void devolverDatos() {
		ServidorRecibirMandar.arraySocket=monitor.getArraySocket();
		
		for (Socket socket : arraySocket) {
			try {
				out=new ObjectOutputStream (socket.getOutputStream());
				out.writeUTF(mensaje);
				System.out.println("MANDADO "+mensaje);
	            out.flush();
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//madno el mensaje al server
			
          
		}
	}	
	
}
