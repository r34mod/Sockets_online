package Ej3;

import java.io.DataInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientFile {

	public static void main(String [] args) {
		Scanner scn = new Scanner(System.in);
		
		String texto;
		
		System.out.println("Nombre del fichero");
		texto = scn.nextLine();
		
		if(args.length != 1) {
			System.out.println("Uso: cliente<host>");
			System.exit(0);
		}
		try {
			String host = args[0];
			Socket sc = new Socket(host, 2500);
			
			OutputStream out = sc.getOutputStream();
			ObjectOutput s = new ObjectOutputStream(out);
			DataInputStream in = new DataInputStream(sc.getInputStream());
			
			s.writeObject(texto);
			s.flush();
			
			
			sc.close();
			System.out.println(in.readUTF());
		}catch(Exception e) {
			e.getCause();
		}
	}
}
