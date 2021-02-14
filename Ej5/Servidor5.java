package Ej5;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor5 {
	public static void main ( String [] args) {
		ObjectOutputStream ostream ;
		InputStream istream ;
		ObjectInput in ;
		ServerSocket serverAddr = null;
		Socket sc= null;
		int[] num ;  
		// petici�n
		int res;
		String []mandar;
		ArrayList<String>mandar2=null;
		BufferedReader lector=null ;
		try {
			serverAddr = new ServerSocket(2503);
			}   catch (Exception e){
				System.err.println("Error creando socket");
				}
		while (true){
			//Mostrar ficheros
			try {
				sc = serverAddr.accept();
				// esperando conexi�n
				System.out.println("Conectado");
				 istream = sc.getInputStream();
				 in = new ObjectInputStream(istream);
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
				OutputStream enviar=sc.getOutputStream();
				
				ostream = new ObjectOutputStream(enviar);
				ostream.writeObject(mandar);
				ostream.flush();
				
				
				//leer los ficheros
				try {
					
					// esperando conexi�n
					System.out.println("Conectado");
					 
					//recibe la palabra
					String ficheroLeer =   in.readUTF();
					
					//verificar si el fichero existe
					File fichero=new File("datos");
					mandar=fichero.list();
					
					String ficheroExiste=null;
					for (String i : mandar) {
						if (ficheroLeer.contentEquals(i)) {
							ficheroExiste=i;
						}
					}
					File fich2=new File ("datos/"+ficheroExiste);
					ostream.writeObject(fich2.length());
					InputStream inputStream = new FileInputStream(fich2);
				    ByteArrayOutputStream baos = new ByteArrayOutputStream();
				    final byte[] BUFFER = new byte[1024];
				    int readed = -1;
				    System.out.println("Leer fichero");
				   
				    
				    while ((readed = inputStream.read(BUFFER)) != -1) {
				        enviar.write(BUFFER, 0, readed);
                        
				        /*
				         * Cuando se haya leido 1 MiB del archivo, o se la lectura actual haya 
				         * leido menos del tama�o del buffer de lectura, enviarlo al servidor
				         */
				      //  if (baos.size() == 1024000 || readed < 1024) {
				        	//ostream.writeObject(BUFFER);
				        	//System.out.println("Dato mandado");
				            //baos.reset();
				       // }
				    }
				   
					
					
//						String fila=lector.readLine();
//						System.out.println(fila);
//						mandar2.add(fila);
					
						ostream.flush();
						sc.close();
						if (lector!=null) {
							lector.close();
						}
					
					} catch(Exception e) {
						 
					
						
						}
				
				
				
				
				} catch(Exception e) {
					System.err.println("excepcion " + e.toString() );
					e.printStackTrace() ;}
			
			
			}
		}
}
