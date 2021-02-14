package TresEnRayaOnline;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;







public class Jugador extends JComponent{
	private EsperaTablero esp;
	private JFrame frame;
    private JLabel lblHasSeleccionado;
    private JLabel lblNewLabel;
    private JLabel lblGanador;
    private JButton btnIniciar; 
    private boolean hayJuego=false;
    private int ganador=0;
    private boolean casillasLibres=true;
    private Semaphore espera=new Semaphore (1,true);
    //BOTONES
    private JButton btnNewButton;
    private JButton button;
    private JButton button_1;
    private JButton button_2;
    private JButton button_3;
    private JButton button_4;
    private JButton button_5;
    private JButton button_6;
    private JButton button_7;
    
    //BOTONES CIERRE
    private boolean juegoIniciado=false;
    private boolean cpu=false;
    private char[][] plantilla=new char[3][3];
    private  int turno=1;
	//



    private static Socket sc=null;
    private  static OutputStream ostream    =null;
    private static ObjectOutputStream out;

    //numero de jugador
    private  int jugador=0;

    
    private static  InputStream istream;
	private static ObjectInputStream in ;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jugador window = new Jugador();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Jugador() {
		initialize();
	}
	
	
	
	private void initialize() {
		frame = new JFrame("Tres en raya");
		frame.setBounds(100, 100, 524, 432);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new Gradient());
		//frame.pack();
		frame.setVisible(true);
		frame.setSize(new Dimension(500, 524));
		//frame.getContentPane().setBackground(Color.YELLOW);
		frame.getContentPane().setLayout(null);
		
		Gradient gradient = new Gradient(Color.MAGENTA, Color.BLUE, 3);
		btnIniciar = new JButton("INICIAR");
		
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (lblHasSeleccionado.getText().contentEquals("")||lblHasSeleccionado.getText().contentEquals("Selecciona modo")) {
					lblHasSeleccionado.setText("Selecciona modo");
				}else {
					//se realiza la conexion con el servidor
					int numeroConexion=-1;
					try {
						sc = new Socket(InetAddress.getLocalHost(), 2505);
						
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						System.err.println("Jugador");
					}
					//desabilita el boton y espera otro jugador a que le de al play
					
					//inicializar las variables de conexion
					//establecer la conexion con el servidor
					//establecemos la conexion con el socket
					 try {
						 Jugador.istream = sc.getInputStream();
						 Jugador.in = new ObjectInputStream(istream);
						
						//recibiendo un mensaje
						
						try {
							numeroConexion=in.readInt();
							plantilla=(char[][]) in.readObject();
							
						} catch (ClassNotFoundException a) {
							// TODO Auto-generated catch block
							a.printStackTrace();
						}
						
						out=new ObjectOutputStream (sc.getOutputStream());
					} catch (IOException m) {
						// TODO Auto-generated catch block
						m.printStackTrace();
					}
					 //Si el servidor devuelve un numero y es distinto a -1
					 //empieza el juego
					if (numeroConexion!=-1) {
						jugador=numeroConexion;
						System.out.println(jugador);
					}
					
					//crear hilo para poder refrescar la pantalla cuando no es el turo, tendria que mandarle el socket
//					EsperaTablero(this)
					//EsperaTablero espera =new EsperaTablero(turno,jugador, sc,botones)
					//pedir plantilla al servidor Tresenraya.plantillaPrincipal(plantilla);
					//inicializar botones
					  btnNewButton.setText("");
					  btnNewButton.setBackground(Color.GREEN);
				      button.setText("");
				      button.setBackground(Color.GREEN);
				      button_1.setText("");
				      button_1.setBackground(Color.GREEN);
				      button_2.setText("");
				      button_2.setBackground(Color.GREEN);
				      button_3.setText("");
				      button_3.setBackground(Color.GREEN);
				      button_4.setText("");
				      button_4.setBackground(Color.GREEN);
				      button_5.setText("");
				      button_5.setBackground(Color.GREEN);
				      button_6.setText("");
				      button_6.setBackground(Color.GREEN);
				      button_7.setText("");
				      button_7.setBackground(Color.GREEN);
				      hayJuego=true;
				     // turno=1;
				      lblNewLabel.setText("1");
				      
				      lblNewLabel.setFont(new Font("ARIAL", Font.ITALIC, 15));
				      lblGanador.setText("");
				      refrescarTablero(plantilla);
				      cpu=false;
					juegoIniciado=true;
					if (lblHasSeleccionado.getText().contentEquals("Seleccionado J VS CPU")) {
						cpu=true;
					}
					
				esp=new EsperaTablero(in, sc, plantilla, btnNewButton, button, button_1, button_2,button_3, button_4, button_5, button_6, button_7,espera,lblNewLabel,turno);
				esp.start();
				}
				
			
				
			}
		});
		btnIniciar.setBounds(367, 226, 89, 23);
		frame.getContentPane().add(btnIniciar);
		
		
	    btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hayJuego) {
					turno=Integer.parseInt(lblNewLabel.getText());
				if (turno==jugador&&juegoIniciado) {
					
					try {
						out.writeInt(0);
						out.writeInt(0);
						out.flush();
						//read de plantilla
						//plantilla=(char[][]) in.readObject();
						
						ganador=in.readInt();
						casillasLibres=in.readBoolean();
						
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					
					if(jugador==1) {
						turno=2;
					}else {
						turno=1;
					}
					
					
					//refrescarTablero(plantilla);
					
					lblNewLabel.setText(""+turno);
					lblNewLabel.setBackground(Color.DARK_GRAY);
					if (ganador!=0) {
						lblGanador.setText("GANADOR J"+ganador);
						lblGanador.setFont(new Font("Mistral", Font.ITALIC, 35));
						lblHasSeleccionado.setText("");
						hayJuego=false;
					}
					
					if(!casillasLibres&&ganador==0) {
						lblGanador.setText("TABLAS");
						hayJuego=false;
					}
				}else{
					JOptionPane.showMessageDialog(null, "No es tu turno");
					
				}
			}
				
			}
		});
		btnNewButton.setBounds(24, 61, 89, 61);
		frame.getContentPane().add(btnNewButton);
		
		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hayJuego) {
					turno=Integer.parseInt(lblNewLabel.getText());
					if (turno==jugador&&juegoIniciado) {
						try {
							out.writeInt(0);
							out.writeInt(1);
							out.flush();
							//read de plantilla
							//plantilla=(char[][]) in.readObject();
							espera.acquireUninterruptibly();
	    					espera.release();
							ganador=in.readInt();
							casillasLibres=in.readBoolean();
							
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						
						if(jugador==1) {
							turno=2;
						}else {
							turno=1;
						}
						
						
						//refrescarTablero(plantilla);
						
						lblNewLabel.setText(""+turno);
						lblNewLabel.setBackground(Color.DARK_GRAY);
						if (ganador!=0) {
							lblGanador.setText("GANADOR J"+ganador);
							lblGanador.setFont(new Font("Mistral", Font.ITALIC, 35));
							lblHasSeleccionado.setText("");
							hayJuego=false;
						}
						
						if(!casillasLibres&&ganador==0) {
							lblGanador.setText("TABLAS");
							hayJuego=false;
						}
					}else{
						JOptionPane.showMessageDialog(null, "No es tu turno");
					}
				}
			}
		});
		button.setBounds(123, 61, 89, 61);
		frame.getContentPane().add(button);
		
		    button_1 = new JButton("");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(hayJuego) {
						turno=Integer.parseInt(lblNewLabel.getText());
						if (turno==jugador&&juegoIniciado) {
							try {
								out.writeInt(0);
								out.writeInt(2);
								out.flush();
								//read de plantilla
								//plantilla=(char[][]) in.readObject();
								espera.acquireUninterruptibly();
		    					espera.release();
								ganador=in.readInt();
								casillasLibres=in.readBoolean();
								
								
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 
							
							if(jugador==1) {
								turno=2;
							}else {
								turno=1;
							}
							
							
							//refrescarTablero(plantilla);
							
							lblNewLabel.setText(""+turno);
							lblNewLabel.setBackground(Color.DARK_GRAY);
							if (ganador!=0) {
								lblGanador.setText("GANADOR J"+ganador);
								lblGanador.setFont(new Font("Mistral", Font.ITALIC, 35));
								lblHasSeleccionado.setText("");
								hayJuego=false;
							}
							
							if(!casillasLibres&&ganador==0) {
								lblGanador.setText("TABLAS");
								hayJuego=false;
							}
						}else{
							JOptionPane.showMessageDialog(null, "No es tu turno");
						}
					}
				}
			});
			button_1.setBounds(222, 61, 89, 61);
			frame.getContentPane().add(button_1);
			
			button_2 = new JButton("");
			button_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(hayJuego) {
						turno=Integer.parseInt(lblNewLabel.getText());
						if (turno==jugador&&juegoIniciado) {
							try {
								out.writeInt(1);
								out.writeInt(0);
								out.flush();
								//read de plantilla
								//plantilla=(char[][]) in.readObject();
								espera.acquireUninterruptibly();
		    					espera.release();
								ganador=in.readInt();
								casillasLibres=in.readBoolean();
								
								
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 
							
							if(jugador==1) {
								turno=2;
							}else {
								turno=1;
							}
							
							
							//refrescarTablero(plantilla);
							
							lblNewLabel.setText(""+turno);
							 lblNewLabel.setBackground(Color.DARK_GRAY);
							if (ganador!=0) {
								lblGanador.setText("GANADOR J"+ganador);
								lblGanador.setFont(new Font("Mistral", Font.ITALIC, 35));
								lblHasSeleccionado.setText("");
								hayJuego=false;
							}
							
							if(!casillasLibres&&ganador==0) {
								lblGanador.setText("TABLAS");
								hayJuego=false;
							}
						}else{
							JOptionPane.showMessageDialog(null, "No es tu turno");
						}
					}
			}
			});
			button_2.setBounds(24, 129, 89, 61);
			frame.getContentPane().add(button_2);
			
			 button_3 = new JButton("");
				button_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(hayJuego) {
							turno=Integer.parseInt(lblNewLabel.getText());
							if (turno==jugador&&juegoIniciado) {
								try {
									out.writeInt(1);
									out.writeInt(1);
									out.flush();
									//read de plantilla
									//plantilla=(char[][]) in.readObject();
									espera.acquireUninterruptibly();
			    					espera.release();
									ganador=in.readInt();
									casillasLibres=in.readBoolean();
									
									
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} 
								
								if(jugador==1) {
									turno=2;
								}else {
									turno=1;
								}
								
								
								//refrescarTablero(plantilla);
								
								lblNewLabel.setText(""+turno);
								 lblNewLabel.setBackground(Color.DARK_GRAY);
								if (ganador!=0) {
									lblGanador.setText("GANADOR J"+ganador);
									lblGanador.setFont(new Font("Mistral", Font.ITALIC, 35));
									lblHasSeleccionado.setText("");
									hayJuego=false;
								}
								
								if(!casillasLibres&&ganador==0) {
									lblGanador.setText("TABLAS");
									hayJuego=false;
								}
							}else{
								JOptionPane.showMessageDialog(null, "No es tu turno");
							}
						}
					}
				});
				button_3.setBounds(123, 129, 89, 61);
				frame.getContentPane().add(button_3);
				
				button_4 = new JButton("");
				button_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(hayJuego) {
							turno=Integer.parseInt(lblNewLabel.getText());
							if (turno==jugador&&juegoIniciado) {
								
								try {
									out.writeInt(1);
									out.writeInt(2);
									out.flush();
									//read de plantilla
									//plantilla=(char[][]) in.readObject();
									espera.acquireUninterruptibly();
			    					espera.release();
									ganador=in.readInt();
									casillasLibres=in.readBoolean();
									
									
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} 
								
								if(jugador==1) {
									turno=2;
								}else {
									turno=1;
								}
								
								
								//refrescarTablero(plantilla);
								
								lblNewLabel.setText(""+turno);
								lblNewLabel.setBackground(Color.DARK_GRAY);
								if (ganador!=0) {
									lblGanador.setText("GANADOR J"+ganador);
									lblGanador.setFont(new Font("Mistral", Font.ITALIC, 35));
									lblHasSeleccionado.setText("");
									hayJuego=false;
								}
								
								if(!casillasLibres&&ganador==0) {
									lblGanador.setText("TABLAS");
									hayJuego=false;
								}
							}else{
								JOptionPane.showMessageDialog(null, "No es tu turno");
							}
						}
					}
				});
				button_4.setBounds(222, 129, 89, 61);
				frame.getContentPane().add(button_4);
				button_5 = new JButton("");
				button_5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(hayJuego) {
							turno=Integer.parseInt(lblNewLabel.getText());
							if (turno==jugador&&juegoIniciado) {
								try {
									out.writeInt(2);
									out.writeInt(0);
									out.flush();
									//read de plantilla
									//plantilla=(char[][]) in.readObject();
									espera.acquireUninterruptibly();
			    					espera.release();
									ganador=in.readInt();
									casillasLibres=in.readBoolean();
									
									
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} 
								
								if(jugador==1) {
									turno=2;
								}else {
									turno=1;
								}
								
								
								//refrescarTablero(plantilla);
								
								lblNewLabel.setText(""+turno);
								lblNewLabel.setBackground(Color.DARK_GRAY);
								if (ganador!=0) {
									lblGanador.setText("GANADOR J"+ganador);
									lblGanador.setFont(new Font("Mistral", Font.ITALIC, 35));
									lblHasSeleccionado.setText("");
									hayJuego=false;
								}
								
								if(!casillasLibres&&ganador==0) {
									lblGanador.setText("TABLAS");
									hayJuego=false;
								}
							}else{
								JOptionPane.showMessageDialog(null, "No es tu turno");
							}
						}
					}
				});
				button_5.setBounds(24, 196, 89, 61);
				frame.getContentPane().add(button_5);
				
				button_6 = new JButton("");
				button_6.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(hayJuego) {
							turno=Integer.parseInt(lblNewLabel.getText());
							if (turno==jugador&&juegoIniciado) {
								try {
									out.writeInt(2);
									out.writeInt(1);
									out.flush();
									//read de plantilla
									//plantilla=(char[][]) in.readObject();
									espera.acquireUninterruptibly();
			    					espera.release();
									ganador=in.readInt();
									casillasLibres=in.readBoolean();
									
									
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} 
								
								if(jugador==1) {
									turno=2;
								}else {
									turno=1;
								}
								
								
								//refrescarTablero(plantilla);
								
								lblNewLabel.setText(""+turno);
								 lblNewLabel.setBackground(Color.DARK_GRAY);
								if (ganador!=0) {
									lblGanador.setText("GANADOR J"+ganador);
									lblGanador.setFont(new Font("Mistral", Font.ITALIC, 35));
									lblHasSeleccionado.setText("");
									hayJuego=false;
								}
								
								if(!casillasLibres&&ganador==0) {
									lblGanador.setText("TABLAS");
									hayJuego=false;
								}
							}else{
								JOptionPane.showMessageDialog(null, "No es tu turno");
							}
						}
					}
				});
				button_6.setBounds(123, 196, 89, 61);
				frame.getContentPane().add(button_6);
				
				button_7 = new JButton("");
				button_7.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(hayJuego) {
							turno=Integer.parseInt(lblNewLabel.getText());
							if (turno==jugador&&juegoIniciado) {
								try {
									out.writeInt(2);
									out.writeInt(2);
									out.flush();
									//read de plantilla
									//plantilla=(char[][]) in.readObject();
									espera.acquireUninterruptibly();
			    					espera.release();
									ganador=in.readInt();
									casillasLibres=in.readBoolean();
									
									
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} 
								
								if(jugador==1) {
									turno=2;
								}else {
									turno=1;
								}
								
								
								//refrescarTablero(plantilla);
								
								lblNewLabel.setText(""+turno);
								 lblNewLabel.setBackground(Color.DARK_GRAY);
								if (ganador!=0) {
									lblGanador.setText("GANADOR J"+ganador);
									lblGanador.setFont(new Font("Mistral", Font.ITALIC, 35));
									lblHasSeleccionado.setText("");
									hayJuego=false;
								}
								
								if(!casillasLibres&&ganador==0) {
									lblGanador.setText("TABLAS");
									hayJuego=false;
								}
							}else{
								JOptionPane.showMessageDialog(null, "No es tu turno");
							}
						}
					}
				});
				button_7.setBounds(222, 196, 89, 61);
				frame.getContentPane().add(button_7);
				
				JButton btnJVsJ = new JButton("J VS J");
				btnJVsJ.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						lblHasSeleccionado.setText("Seleccionado "+btnJVsJ.getText());
						
					}
				});
				btnJVsJ.setBounds(367, 61, 89, 46);
				frame.getContentPane().add(btnJVsJ);
				
				JButton btnJVsCpu = new JButton("J VS CPU");
				btnJVsCpu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						lblHasSeleccionado.setText("Seleccionado "+btnJVsCpu.getText());
						
						
					}
				});
				btnJVsCpu.setBounds(367, 111, 89, 46);
				frame.getContentPane().add(btnJVsCpu);
				
				
				lblHasSeleccionado = new JLabel("");
				lblHasSeleccionado.setBounds(321, 168, 157, 14);
				frame.getContentPane().add(lblHasSeleccionado);
				
				
				lblNewLabel = new JLabel("");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblNewLabel.setBounds(292, 350, 157, 46);
				frame.getContentPane().add(lblNewLabel);
				
			    lblGanador = new JLabel("");
				lblGanador.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblGanador.setBounds(292, 336, 157, 46);
				frame.getContentPane().add(lblGanador);
				
				
	}
	
	
	
	
	
	
	

		
	public  int mirarTurno() {
		return turno;
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
