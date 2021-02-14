package Ej4;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente4 {

	public static void main ( String [] args) {
		String [] res;
		int fichero[] = new int[2];
       

        try {
        	// se crea la conexi�n
        	
        	Socket sc = new Socket(InetAddress.getLocalHost(), 2502); //  conexi�n
        	OutputStream ostream    = sc.getOutputStream();
        	ObjectOutput s  = new ObjectOutputStream(ostream);
        	
        	fichero[0]=1;
        	//prepara la petici�n
        	s.writeObject(fichero);
        	s.flush();
        	ObjectInputStream istream = new ObjectInputStream(sc.getInputStream());
        	res = (String[]) istream.readObject();
        	
        	sc.close();
        	if(res!=null) {
        		for (String i : res) {
    				System.out.println(i);
    			}
            
        	}else {
        		System.out.println("Numero Incorrecto");
        	}
        	
        	}catch (Exception e){
        		System.err.println("excepcion " + e.toString() );e.printStackTrace() ;
        		}
        }
}
