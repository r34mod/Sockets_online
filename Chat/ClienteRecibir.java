package Chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JTextArea;

public class ClienteRecibir extends Thread{
	private JTextArea chat;
	private Socket socket=null;
	private ObjectInputStream in=null;
	public ClienteRecibir (Socket socket,JTextArea chat) {
		this.socket=socket;
		this.chat=chat;
	}
	@Override
	public void run() {
		
		InputStream istream;
		String texto=null;
		try {
			istream = socket.getInputStream();
			
			
			while(true) {
				in = new ObjectInputStream(istream);
				try {
					System.out.println("Espera");
					texto=in.readUTF();
					System.out.println("RECIBIDO"+texto);
					
					this.chat.append("\n"+texto);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
					
					
				}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
}
