package Ej1Datagrama;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

	public static void main ( String [] args) {
		DatagramSocket s = null;
		
		
		DatagramPacket in, out;
		InetAddress client_addr = null;
		int client_port;
		byte brecv[] = new byte[100];
		byte bsend[] = new byte[100];
		int res=0;
		String texto;
		try {
		s = new DatagramSocket(2500);
		in = new DatagramPacket(brecv, 100); // paquete para recibir la solicitud 
		
			while (true) {
				s.receive(in); //esperamos a recibir
				// obtener datos
				brecv = in.getData();
				client_addr = in.getAddress();
				client_port = in.getPort();
				
				// desempaquetar los datos
				ByteArrayInputStream bais = new ByteArrayInputStream(brecv);
				ObjectInputStream dis = new ObjectInputStream(bais);
				texto = (String)dis.readObject();
				for(int x=0; x<texto.length(); x++) {
					
				}
				for(int i=0; i<texto.length(); i++) {
					if ((texto.charAt(i)=='a') || (texto.charAt(i)=='e') || 
							(texto.charAt(i)=='i') || (texto.charAt(i)=='o') || 
							(texto.charAt(i)=='u') ) {
					    	res++;
					}
				}
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				DataOutputStream dos = new DataOutputStream(baos);
				dos.writeInt(res);
				bsend = baos.toByteArray();
				out = new DatagramPacket ( bsend,
				bsend.length,client_addr,
				client_port);
			
				s.send(out);
				res=0;
				}
			}
			catch(Exception e) {
			System.err.println("excepcion " + e.toString() );
			e.printStackTrace() ;
			}
	}
}
