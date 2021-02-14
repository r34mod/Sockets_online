package Ej2;

import java.io.DataInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	
	public static void main ( String [] args)
	{
	Scanner scn = new Scanner(System.in);
	String texto;
	String txt;
	//int num[] = new int[2];
	
	System.out.println("Escribe un texto");
	texto = scn.nextLine();

	if (args.length != 1) {
	System.out.println("Uso: cliente <host>");
	System.exit(0);
	}
	try {
	// se crea la conexión
	String host = args[0];
	Socket sc = new Socket(host, 2500); // conexión
	
	OutputStream ostream = sc.getOutputStream();
	ObjectOutput s = new ObjectOutputStream(ostream);
	DataInputStream istream = new DataInputStream(sc.getInputStream());
	//num[0] = 5; num[1] = 2; //prepara la petición
	s.writeObject(texto);
	s.flush();
	txt = istream.readUTF();
	
	sc.close();
	System.out.println("La palabra es " + txt);
	}
	catch (Exception e){
	System.err.println("excepcion " + e.toString() );
	e.printStackTrace() ;
	}
	}

}
