package Ej7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Servidor extends Thread {
	 private  String s1="";
	 private  String s2="";
	 private  String s3="";
	 private Socket sc;
	 private boolean terminar;
	 private  int vidas;
	 
	 
	  Servidor (Socket sc) {
		 this.sc=sc;
	 }
	// (Socket sc) 
	 public void run() {
		 terminar=false;
			
			ObjectOutputStream out=null; 
			
			s1=generarPalabra();
			InputStream istream=null ;
			ObjectInput in=null;
			
			String letra="";
			
			String palabraAsteriscos="";
			
			
			try {
				 istream = sc.getInputStream();
				 in = new ObjectInputStream(istream);
			
				int conexion=in.readInt();
				out=new ObjectOutputStream (sc.getOutputStream());
				if (conexion==101) {
					 for (int i = 0; i < s1.length(); i++) {
							s3=s3+"*";
						}
					 System.out.println("recibido");
					out.writeUTF(s3);
					out.flush();
					System.out.println("Enviado");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vidas=10;
			
					
			
			while (!terminar) {
			//Verificar la letra recibida
		
			try {
				//fallo en input
				
				
				letra=in.readUTF();
				
			    palabraAsteriscos=in.readUTF();
				
				s2=palabraAsteriscos;
				verificarLetra(letra);
				
				//se devuelve la palabra
				
				out.writeUTF(s2);
				
				//abrir ventana has ganado
				boolean ganado=verificarFrase();
				out.writeBoolean(ganado);
				out.flush();
				if(ganado) {
					terminar=true;	
				}
				//o as perdido
				boolean perdido=false;
				if(vidas==0) {
					perdido=true;
					
					terminar=true;				
				}
				out.writeBoolean(perdido);
				if (perdido) {
					out.writeUTF(s1);
				}
				out.writeInt(vidas);
				out.flush();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	 }
	 
	 
	 
	 public  String verificarLetra(String let){
			let=let.toUpperCase();
			String fraseTemporal="";
			boolean encontrada=false;
			
		 	
		 		for (int i = 0; i < s1.length(); i++) {
					
		 			if(s1.charAt(i)==let.charAt(0)) {
		 				
		 				fraseTemporal=fraseTemporal+""+let.charAt(0);
		 				encontrada=true;
		 			}else {
		 				
		 				fraseTemporal=fraseTemporal+""+s2.charAt(i);
		 			}
				}
		 		
		 		
		 	if(encontrada==false) {
		 		vidas--;
		 		//la letra no esta	
		 	}
		 		
		 	
		 	
		 	s2=fraseTemporal;
			
		return s2;}
		
		public  boolean verificarFrase(){
			boolean verif=true;
			int contador=0;
			while (contador<s2.length()&& verif==true) {
				if (s2.charAt(contador)>='A'&& s2.charAt(contador)<='Z' )	{
					verif=true;
				}else {verif=false;}
			contador++;}
			
			
		return verif;}
		
		public static String generarPalabra() {
			String palabra="";
			int numeroPalabra=(int)(Math.random()*12);
			File f = new File("C:\\eclipse\\workspace\\Comunicaciones\\palabras\\listaPalabras");
			try {
				BufferedReader bf = new BufferedReader(new FileReader(f));
				for (int i = 0; i < numeroPalabra; i++) {
					bf.readLine();
				}
				palabra=bf.readLine();
				
				if(bf!=null) {
					bf.close();
					}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Error al cargar fichero");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error al Leer");
			}
			
			
			return palabra;
		}
		
		
}


