package TresEnRayaOnline;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.Semaphore;

import javax.sound.midi.spi.MidiFileReader;
import javax.swing.JButton;
import javax.swing.JLabel;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class EsperaTablero extends Thread{
	private JLabel lblNewLabel;
	private Jugador j;
	private char[][] plantillaHilo;
	private String plantillaTexto;
	private char[][] plantillaHilo11;
	
	private Socket sc ;
	private JButton btnNewButton;
    private JButton button;
    private JButton button_1;
    private JButton button_2;
    private JButton button_3;
    private JButton button_4;
    private JButton button_5;
    private JButton button_6;
    private JButton button_7;
    private int turno;
    private static  InputStream istream;
  
   private  boolean realizado=false;
    private Semaphore espera;
	private static ObjectInputStream in ;
	
	
	public EsperaTablero(ObjectInputStream in ,Socket sc,char[][]plantilla,JButton btnNewButton,JButton b1,JButton b2,JButton b3,JButton b4,JButton b5,JButton b6,JButton b7,JButton b8,Semaphore espera, JLabel lblNewLabel,int turno) {
		this.turno=turno;
		this.lblNewLabel=lblNewLabel;
		this.espera=espera;
		
		
		
		this.btnNewButton=btnNewButton;
		this.button=b1;
		this.button_1=b2;
		this.button_2=b3;
		this.button_3=b4;
		this.button_4=b5;
		this.button_5=b6;
		this.button_6=b7;
		this.button_7=b8;
		
	}

	@Override
	public void run() {
		plantillaHilo11=new char[3][3];
		plantillaTexto="";
		try {
			sc = new Socket(InetAddress.getLocalHost(), 2508);
			istream=sc.getInputStream();
			in=new ObjectInputStream(istream);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		while (true) {
			realizado=false;
			espera.acquireUninterruptibly();
			try {
				//leo el string y lo cambio a plantilla
				plantillaTexto=in.readUTF();
				//plantillaHilo11=(char[][]) in.readUnshared();
				cambiarTextoAPlantilla(plantillaHilo11);
				EsperaTablero.mostrarPlantilla(plantillaHilo11);
				if(turno==1) {
					lblNewLabel.setText(""+2);	
					turno=2;
				}else {
					lblNewLabel.setText(""+1);
					turno=1;
				}
				
				refrescarTablero(plantillaHilo11);
				realizado=true;
				espera.release();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	public boolean isRealizado() {
		return realizado;
	}
public void cambiarTextoAPlantilla(char[][] a) {
	//plantillaTexto
	int i=-1;
	for (int contadorFilas=0;contadorFilas<a.length;contadorFilas++)
	{
		for (int contadorColumnas=0;contadorColumnas<a[contadorFilas].length;contadorColumnas++) {
		i++;
			a[contadorFilas][contadorColumnas]=plantillaTexto.charAt(i);
		}
	}
	
}
	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}
	public static void mostrarPlantilla(char [][]a) {
		for (int contadorFilas=0;contadorFilas<a.length;contadorFilas++)
		{
			for (int contadorColumnas=0;contadorColumnas<a[contadorFilas].length;contadorColumnas++) {
			
				System.out.println(a[contadorFilas][contadorColumnas]);
			}
		}
		
	}
	public void refrescarTablero(char[][] a) {
		btnNewButton.setText(""+a[0][0]);
	      button.setText(""+a[0][1]);
	      button_1.setText(""+a[0][2]);
	      button_2.setText(""+a[1][0]);
	      button_3.setText(""+a[1][1]);
	      button_4.setText(""+a[1][2]);
	      button_5.setText(""+a[2][0]);
	      button_6.setText(""+a[2][1]);
	      button_7.setText(""+a[2][2]);
    
	
		    
	}

}
