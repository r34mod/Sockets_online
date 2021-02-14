package Ej5;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente5 {
	public static void main ( String [] args) {
		String [] res;
		byte [] res2;
		int fichero[] = new int[2];
		
       Scanner teclado=new Scanner(System.in);

        try {
        	// se crea la conexi�n
        	Socket sc = new Socket(InetAddress.getLocalHost(), 2503);
        	 //  conexi�n
        	OutputStream ostream    = sc.getOutputStream();
        	ObjectOutput s  = new ObjectOutputStream(ostream);
        	
        	fichero[0]=1;
        	//prepara la petici�n
        	s.writeObject(fichero);
        	s.flush();
        	InputStream leer=sc.getInputStream();
        	ObjectInputStream istream = new ObjectInputStream(sc.getInputStream());
        	res = (String[]) istream.readObject();
        	
        	
        	if(res!=null) {
        		for (String i : res) {
    				System.out.println(i);
    			}
            
        	}else {
        		System.out.println("Numero Incorrecto");
        	}
        	System.out.println("Seleciona fichero");
        	String eleccionFichero= teclado.nextLine();
        	
        	//Ahora se manda el nombre de fichero
// se crea la conexi�n
        	
        	
        	s.writeUTF(eleccionFichero);
        	s.flush();
        	//falla aqui
        	
        	long tamano = (long) istream.readObject();
        	File f = new File("recibidos/"+eleccionFichero);
        	
        	FileOutputStream fos=new FileOutputStream(f);
        	byte[] BUFFER = new byte[1024];
//		    ByteArrayInputStream baos ;
//		    long tama�oFichero=f.length();
		    int leido;
        	while((leido=leer.read(BUFFER, 0,1024))>0) {
        		
        		
        	     
        		
        			
        			fos.write(BUFFER,0,leido);
        			//System.out.println("Dato recibido");
            		          		
        			//tama�oFichero=f.length();
                
            	
        	}
        	fos.close();
        	
        	System.out.println("COPIADO");
        	sc.close();
        	
        	
        	}catch (Exception e){
        		System.err.println("excepcion " + e.toString() );e.printStackTrace() ;
        		}
       
        }
}
