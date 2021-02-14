package Ej10;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Panel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;

public class Chat {
	private ClienteRecibir c1;
    private ObjectOutputStream out;
	private JFrame frame;
	private JTextField introducirTexto;
	private JTextField nombreUsuario;
	private JScrollPane scrollPane;
	private JTextArea chat;
	private JLabel lblChat;
	private JButton btnConectar;
	private JButton btnDesconectar;
	private JLabel lblNombreUsuario;
	private String nombreDeUsuario;
	private Socket socket=null;
	private Socket socketRecibir=null;
    private  JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					Chat window = new Chat();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Chat() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.setBounds(100, 100, 781, 397);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 87, 745, 199);
		frame.getContentPane().add(scrollPane);
		
		chat = new JTextArea();
		chat.setBackground(Color.LIGHT_GRAY);
		chat.setForeground(Color.RED);
		scrollPane.setViewportView(chat);
		
		introducirTexto = new JTextField();
		introducirTexto.setBounds(10, 297, 562, 50);
		frame.getContentPane().add(introducirTexto);
		introducirTexto.setColumns(10);
		
		lblChat = new JLabel("CHAT");
		lblChat.setFont(new Font("Mistral", Font.ITALIC, 35));
		lblChat.setBounds(330, 38, 104, 38);
		frame.getContentPane().add(lblChat);
		
		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Realizar la conexion,
				//verificar primero el nombre introducido.
				if(nombreUsuario.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(null, "Necesitas un nombre de usuario");
					
				}else {
					//recogemos su nombre de usuario
					nombreDeUsuario=nombreUsuario.getText();
					
					chat.append("Conectado: "+nombreDeUsuario);
						progressBar.setValue(100);
						
						
						
						
		       
					
					try {
						socket=new Socket(InetAddress.getLocalHost(), 2510);
						socketRecibir=new Socket(InetAddress.getLocalHost(), 2515);
						c1=new ClienteRecibir(socketRecibir,chat);
						c1.start();
						 
							
							try {
								out=new ObjectOutputStream (socket.getOutputStream());
							}catch (Exception e) {
								// TODO: handle exception
							}
						
						System.out.println("Aceptada conexion");
						
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						chat.append("\n"+" Server apagado");
					}
				}
				
				
			}
		});
		btnConectar.setBounds(464, 11, 135, 38);
		frame.getContentPane().add(btnConectar);
		
		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteEnviar c = new ClienteEnviar(nombreDeUsuario,"desconectar",out);
				c.start();
				
				frame.dispose();
				System.exit(0);
			}
		});
		btnDesconectar.setBounds(609, 11, 135, 38);
		frame.getContentPane().add(btnDesconectar);
		
		nombreUsuario = new JTextField();
		nombreUsuario.setBounds(10, 50, 160, 26);
		frame.getContentPane().add(nombreUsuario);
		nombreUsuario.setColumns(10);
		
		lblNombreUsuario = new JLabel("Nombre Usuario");
		lblNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreUsuario.setBounds(10, 23, 160, 14);
		frame.getContentPane().add(lblNombreUsuario);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//al pulsar el boton ejecuta el hilo de mandar datos
				String tectoMandar=introducirTexto.getText();
				if(tectoMandar.contentEquals("")) {
					JOptionPane.showMessageDialog(null, "Introduce algun texto");
					
				}else {
					ClienteEnviar c = new ClienteEnviar(nombreDeUsuario,tectoMandar,out);
					c.start();
					
				}
				
			}
		});
		btnEnviar.setBounds(591, 297, 153, 50);
		frame.getContentPane().add(btnEnviar);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(464, 60, 280, 16);
		
		frame.getContentPane().add(progressBar);
	
		
		
	}
	
}
