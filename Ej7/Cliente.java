package Ej7;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

public class Cliente extends Application {
	private static Stage primaryStage;
    private AnchorPane rootLayout;
    private static Socket sc=null;
    private  static OutputStream ostream    =null;
    private static ObjectOutput s;
    private int numeroLetras=0;
    
    
    private static String palabraVac;
    private static  InputStream istream;
	private static ObjectInput in ;
    @FXML
	private Label vidas; 
    @FXML
    private TextField textoLetra;
    @FXML
    private TextField palabraParaAdivinar;
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Ahorcado");

        // Set the application icon.
       // this.primaryStage.getIcons().add(new Image("file:resources/imagenes/icono.png"));
        
        sc = new Socket(InetAddress.getLocalHost(), 2504);
  
//	    ostream = sc.getOutputStream();
        ostream=sc.getOutputStream();
		s  = new ObjectOutputStream(ostream);
		
		s.writeInt(101);
		s.flush();
		System.out.println("enviado 101");
		istream = sc.getInputStream();
		in = new ObjectInputStream(istream);
		palabraVac=in.readUTF();
		
		System.out.println(palabraVac);
        initRootLayout();
        
       
    	
       
		
	}
	public void conexion() {
		
	}
	
	public void initRootLayout() {
        try {
        	
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Cliente.class
                    .getResource("Pantalla.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
           // textoLetra.setText("a");
            //recoger el tamaño de la palabra
            
          
      
       	 
   			
            primaryStage.show();
        
               
        
   		 
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Try to load last opened person file.
       // palabraParaAdivinar.setText(palabraVac);    
       
        
        
    }
	 
	 
	 public void verificarPalabra() {
		 
		   if (numeroLetras==0) {
			   palabraParaAdivinar.setText(palabraVac);  
			   numeroLetras=1;
			   //leer vidas
			   vidas.setText(""+10);
		   }
        //pedir vidas
	
		 try {
			
			//mandar letra al servidor
			String letra=textoLetra.getText();
			String palabra=palabraParaAdivinar.getText();
			System.out.println(letra);
			s.writeUTF(letra);
			
			s.writeUTF(palabra);
			s.flush();
			jugar();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 public void jugar() {
		
     
      boolean ganado=false;
      boolean perdido=false;
      
      
   
     
      	
      	//pedir la palabra
      	try {
				
				String palabraA=in.readUTF();
				palabraParaAdivinar.setText(palabraA);
				ganado=in.readBoolean();
				perdido=in.readBoolean();
				
				if(ganado) {
					 Alert alert = new Alert(AlertType.INFORMATION);
				        alert.setTitle("GANADOR");
				        alert.setHeaderText("has ganado");
				        alert.setContentText("Palabra a buscar era: "+palabraA);
				        alert.showAndWait();
				        primaryStage.close();
				        
				}else {
					if (perdido) {
						String palabraFinal=in.readUTF();
						Alert alert = new Alert(AlertType.INFORMATION);
				        alert.setTitle("PERDEDOR");
				        alert.setHeaderText("has perdido");
				        alert.setContentText("Palabra a buscar era: "+palabraFinal);
				        alert.showAndWait();
				        primaryStage.close();
					}else {
						
					}
				}
				 int vida=in.readInt();
				 vidas.setText(""+vida);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
      	
      
	 }
	 public static void main(String[] args) {
		
	        launch(args);
	        
	        
	        
	        
	    }
	

}
