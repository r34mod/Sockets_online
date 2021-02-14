package trenEnRaya;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VentanaTresEnRaya {

	private JFrame frame;
    private JLabel lblHasSeleccionado;
    private JLabel lblNewLabel;
    private JLabel lblGanador;
    private JButton btnIniciar; 
    private boolean hayJuego=false;
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
    private int turno=1;
    private char j1='O';
    private char j2='X';
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaTresEnRaya window = new VentanaTresEnRaya();
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
	public VentanaTresEnRaya() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 524, 432);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Tresenraya.plantillaPrincipal(plantilla);
		
		btnIniciar = new JButton("INICIAR");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lblHasSeleccionado.getText().contentEquals("")||lblHasSeleccionado.getText().contentEquals("Selecciona modo")) {
					lblHasSeleccionado.setText("Selecciona modo");
				}else {
					Tresenraya.plantillaPrincipal(plantilla);
					//inicializar botones
					  btnNewButton.setText("");
				      button.setText("");
				      button_1.setText("");
				      button_2.setText("");
				      button_3.setText("");
				      button_4.setText("");
				      button_5.setText("");
				      button_6.setText("");
				      button_7.setText("");
				      hayJuego=true;
				      turno=1;
				      
				      lblGanador.setText("");
				      cpu=false;
					juegoIniciado=true;
					if (lblHasSeleccionado.getText().contentEquals("Seleccionado J VS CPU")) {
						cpu=true;
					}
				}
			
				
			}
		});
		btnIniciar.setBounds(367, 226, 89, 23);
		frame.getContentPane().add(btnIniciar);
		
		
	    btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hayJuego) {
				if (turno==1&&Tresenraya.hayCasillasLibres(plantilla)&&juegoIniciado) {
					if(Tresenraya.jugar(plantilla, 0, 0, j1)) {
						lblNewLabel.setText("Turno j2");
						btnNewButton.setText(""+j1);
						turno=2;
						if (Tresenraya.hayGanador(plantilla)!=0) {
							lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
							lblHasSeleccionado.setText("");
							hayJuego=false;
						}
						if(!Tresenraya.hayCasillasLibres(plantilla)&&((Tresenraya.hayGanador(plantilla)==0))) {
							lblGanador.setText("TABLAS");
							hayJuego=false;
						}
						if(cpu) {
							if(hayJuego) {
							String numeros=Tresenraya.cpu(plantilla);
							cambiarValoresBotones(numeros);
							turno=1;
							lblNewLabel.setText("Turno j1");
							if (Tresenraya.hayGanador(plantilla)!=0) {
								lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
								lblHasSeleccionado.setText("");
								hayJuego=false;
							}
						}
							}
						
					}
					
				}else {
					if(hayJuego) {
					if(Tresenraya.hayCasillasLibres(plantilla)&&juegoIniciado) {
						if(Tresenraya.jugar(plantilla, 0, 0, j2)) {
							lblNewLabel.setText("Turno j1");
							btnNewButton.setText(""+j2);
							turno=1;
							if (Tresenraya.hayGanador(plantilla)!=0) {
								lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
								lblHasSeleccionado.setText("");
								hayJuego=false;
							}
						}	
					}
					if(!Tresenraya.hayCasillasLibres(plantilla)&&((Tresenraya.hayGanador(plantilla)==0))) {
						lblGanador.setText("TABLAS");
						hayJuego=false;
					}
					
				}
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
				if (turno==1&&Tresenraya.hayCasillasLibres(plantilla)&&juegoIniciado) {
					if(Tresenraya.jugar(plantilla, 0, 1, j1)) {
						lblNewLabel.setText("Turno j2");
						button.setText(""+j1);
						turno=2;
						if (Tresenraya.hayGanador(plantilla)!=0) {
							lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
							lblHasSeleccionado.setText("");
							hayJuego=false;
						}
						if(!Tresenraya.hayCasillasLibres(plantilla)&&((Tresenraya.hayGanador(plantilla)==0))) {
							lblGanador.setText("TABLAS");
							hayJuego=false;
						}
						if(cpu) {
							if(hayJuego) {
							String numeros=Tresenraya.cpu(plantilla);
							cambiarValoresBotones(numeros);
							turno=1;
							lblNewLabel.setText("Turno j1");
							if (Tresenraya.hayGanador(plantilla)!=0) {
								lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
								lblHasSeleccionado.setText("");
								hayJuego=false;
							}
							}
						}
						
					}
					
				}else {
					if(hayJuego){
					if(Tresenraya.hayCasillasLibres(plantilla)&&juegoIniciado) {
						
							if(Tresenraya.jugar(plantilla, 0, 1, j2)) {
							lblNewLabel.setText("Turno j1");
							button.setText(""+j2);
							turno=1;
							if (Tresenraya.hayGanador(plantilla)!=0) {
								lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
								lblHasSeleccionado.setText("");
								hayJuego=false;
							}
						}
							if(!Tresenraya.hayCasillasLibres(plantilla)&&((Tresenraya.hayGanador(plantilla)==0))) {
								lblGanador.setText("TABLAS");
								hayJuego=false;
							}
						
					
					}
					
				}
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
				if (turno==1&&Tresenraya.hayCasillasLibres(plantilla)&&juegoIniciado) {
					if(Tresenraya.jugar(plantilla, 0, 2, j1)) {
						lblNewLabel.setText("Turno j2");
						button_1.setText(""+j1);
						turno=2;
						if (Tresenraya.hayGanador(plantilla)!=0) {
							lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
							lblHasSeleccionado.setText("");
							hayJuego=false;
						}
						if(!Tresenraya.hayCasillasLibres(plantilla)&&((Tresenraya.hayGanador(plantilla)==0))) {
							lblGanador.setText("TABLAS");
							hayJuego=false;
						}
						if(cpu) {
							if(hayJuego) {
							String numeros=Tresenraya.cpu(plantilla);
							cambiarValoresBotones(numeros);
							turno=1;
							lblNewLabel.setText("Turno j1");
							}
						}
						
					}
					
				}else {
					if(hayJuego) {
					if(Tresenraya.hayCasillasLibres(plantilla)&&juegoIniciado) {
						if(Tresenraya.jugar(plantilla, 0, 2, j2)) {
							lblNewLabel.setText("Turno j1");
							button_1.setText(""+j2);
							turno=1;
							if (Tresenraya.hayGanador(plantilla)!=0) {
								lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
								lblHasSeleccionado.setText("");
								hayJuego=false;
							}
						}
						if(!Tresenraya.hayCasillasLibres(plantilla)&&((Tresenraya.hayGanador(plantilla)==0))) {
							lblGanador.setText("TABLAS");
							hayJuego=false;
						}
					}
					
				}
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
				if (turno==1&&Tresenraya.hayCasillasLibres(plantilla)&&juegoIniciado) {
					if(Tresenraya.jugar(plantilla, 1, 0, j1)) {
						lblNewLabel.setText("Turno j2");
						button_2.setText(""+j1);
						turno=2;
						if (Tresenraya.hayGanador(plantilla)!=0) {
							lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
							lblHasSeleccionado.setText("");
							hayJuego=false;
						}
						if(!Tresenraya.hayCasillasLibres(plantilla)&&((Tresenraya.hayGanador(plantilla)==0))) {
							lblGanador.setText("TABLAS");
							hayJuego=false;
						}
						if(cpu) {
							if(hayJuego) {
							String numeros=Tresenraya.cpu(plantilla);
							cambiarValoresBotones(numeros);
							turno=1;
							lblNewLabel.setText("Turno j1");
							if (Tresenraya.hayGanador(plantilla)!=0) {
								lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
								lblHasSeleccionado.setText("");
								hayJuego=false;
							}
							}
						}
						
					}
					
				}else {
					if(hayJuego) {
					if(Tresenraya.hayCasillasLibres(plantilla)&&juegoIniciado) {
						if(Tresenraya.jugar(plantilla, 1, 0, j2)) {
							lblNewLabel.setText("Turno j1");
							button_2.setText(""+j2);
							turno=1;
							if (Tresenraya.hayGanador(plantilla)!=0) {
								lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
								lblHasSeleccionado.setText("");
								hayJuego=false;
							}
						}	
						if(!Tresenraya.hayCasillasLibres(plantilla)&&((Tresenraya.hayGanador(plantilla)==0))) {
							lblGanador.setText("TABLAS");
							hayJuego=false;
						}
					}
					
				}
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
				if (turno==1&&Tresenraya.hayCasillasLibres(plantilla)&&juegoIniciado) {
					if(Tresenraya.jugar(plantilla, 1, 1, j1)) {
						lblNewLabel.setText("Turno j2");
						button_3.setText(""+j1);
						turno=2;
						if (Tresenraya.hayGanador(plantilla)!=0) {
							lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
							lblHasSeleccionado.setText("");
							hayJuego=false;
						}
						if(!Tresenraya.hayCasillasLibres(plantilla)&&((Tresenraya.hayGanador(plantilla)==0))) {
							lblGanador.setText("TABLAS");
							hayJuego=false;
						}
						if(cpu) {
							if(hayJuego) {
							String numeros=Tresenraya.cpu(plantilla);
							cambiarValoresBotones(numeros);
							turno=1;
							lblNewLabel.setText("Turno j1");
							if (Tresenraya.hayGanador(plantilla)!=0) {
								lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
								lblHasSeleccionado.setText("");
								hayJuego=false;
							}
							}
						}
						
					}
					
				}else {
					if(hayJuego) {
					if(Tresenraya.hayCasillasLibres(plantilla)&&juegoIniciado) {
						if(Tresenraya.jugar(plantilla, 1, 1, j2)) {
							lblNewLabel.setText("Turno j1");
							button_3.setText(""+j2);
							turno=1;
							if (Tresenraya.hayGanador(plantilla)!=0) {
								lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
								lblHasSeleccionado.setText("");
								hayJuego=false;
							}
						}	
						if(!Tresenraya.hayCasillasLibres(plantilla)&&((Tresenraya.hayGanador(plantilla)==0))) {
							lblGanador.setText("TABLAS");
							hayJuego=false;
						}
					}
					
				}
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
				if (turno==1&&Tresenraya.hayCasillasLibres(plantilla)&&juegoIniciado) {
					if(Tresenraya.jugar(plantilla, 1, 2, j1)) {
						lblNewLabel.setText("Turno j2");
						button_4.setText(""+j1);
						turno=2;
						if (Tresenraya.hayGanador(plantilla)!=0) {
							lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
							lblHasSeleccionado.setText("");
							hayJuego=false;
						}
						if(!Tresenraya.hayCasillasLibres(plantilla)&&((Tresenraya.hayGanador(plantilla)==0))) {
							lblGanador.setText("TABLAS");
							hayJuego=false;
						}
						if(cpu) {
							if(hayJuego) {
							String numeros=Tresenraya.cpu(plantilla);
							cambiarValoresBotones(numeros);
							turno=1;
							lblNewLabel.setText("Turno j1");
							if (Tresenraya.hayGanador(plantilla)!=0) {
								lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
								lblHasSeleccionado.setText("");
								hayJuego=false;
							}
							}
						}
						
					}
					
				}else {
					if(hayJuego) {
					if(Tresenraya.hayCasillasLibres(plantilla)&&juegoIniciado) {
						if(Tresenraya.jugar(plantilla, 1, 2, j2)) {
							lblNewLabel.setText("Turno j1");
							button_4.setText(""+j2);
							turno=1;
							if (Tresenraya.hayGanador(plantilla)!=0) {
								lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
								lblHasSeleccionado.setText("");
								hayJuego=false;
							}
						}	
						if(!Tresenraya.hayCasillasLibres(plantilla)&&((Tresenraya.hayGanador(plantilla)==0))) {
							lblGanador.setText("TABLAS");
							hayJuego=false;
						}
					}
					
				}
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
				if (turno==1&&Tresenraya.hayCasillasLibres(plantilla)&&juegoIniciado) {
					if(Tresenraya.jugar(plantilla, 2, 0, j1)) {
						lblNewLabel.setText("Turno j2");
						button_5.setText(""+j1);
						turno=2;
						if (Tresenraya.hayGanador(plantilla)!=0) {
							lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
							lblHasSeleccionado.setText("");
							hayJuego=false;
						}
						if(!Tresenraya.hayCasillasLibres(plantilla)&&((Tresenraya.hayGanador(plantilla)==0))) {
							lblGanador.setText("TABLAS");
							hayJuego=false;
						}
						if(cpu) {
							if(hayJuego) {
							String numeros=Tresenraya.cpu(plantilla);
							cambiarValoresBotones(numeros);
							turno=1;
							lblNewLabel.setText("Turno j1");
							if (Tresenraya.hayGanador(plantilla)!=0) {
								lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
								lblHasSeleccionado.setText("");
								hayJuego=false;
							}
							}
						}
						
					}
					
				}else {
					if(hayJuego) {
					if(Tresenraya.hayCasillasLibres(plantilla)&&juegoIniciado) {
						if(Tresenraya.jugar(plantilla, 2, 0, j2)) {
							lblNewLabel.setText("Turno j1");
							button_5.setText(""+j2);
							turno=1;
							if (Tresenraya.hayGanador(plantilla)!=0) {
								lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
								lblHasSeleccionado.setText("");
								hayJuego=false;
							}
						}	
						if(!Tresenraya.hayCasillasLibres(plantilla)&&((Tresenraya.hayGanador(plantilla)==0))) {
							lblGanador.setText("TABLAS");
							hayJuego=false;
						}
					}
					
				}
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
				if (turno==1&&Tresenraya.hayCasillasLibres(plantilla)&&juegoIniciado) {
					if(Tresenraya.jugar(plantilla, 2, 1, j1)) {
						lblNewLabel.setText("Turno j2");
						button_6.setText(""+j1);
						turno=2;
						if (Tresenraya.hayGanador(plantilla)!=0) {
							lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
							lblHasSeleccionado.setText("");
							hayJuego=false;
						}
						if(!Tresenraya.hayCasillasLibres(plantilla)&&((Tresenraya.hayGanador(plantilla)==0))) {
							lblGanador.setText("TABLAS");
							hayJuego=false;
						}
						if(cpu) {
							if(hayJuego) {
							String numeros=Tresenraya.cpu(plantilla);
							cambiarValoresBotones(numeros);
							turno=1;
							lblNewLabel.setText("Turno j1");
							if (Tresenraya.hayGanador(plantilla)!=0) {
								lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
								lblHasSeleccionado.setText("");
								hayJuego=false;
							}
							}
						}
						
					}
					
				}else {
					if(hayJuego) {
					if(Tresenraya.hayCasillasLibres(plantilla)&&juegoIniciado) {
						if(Tresenraya.jugar(plantilla, 2, 1, j2)) {
							lblNewLabel.setText("Turno j1");
							button_6.setText(""+j2);
							turno=1;
							if (Tresenraya.hayGanador(plantilla)!=0) {
								lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
								lblHasSeleccionado.setText("");
								hayJuego=false;
							}
						}	
						if(!Tresenraya.hayCasillasLibres(plantilla)&&((Tresenraya.hayGanador(plantilla)==0))) {
							lblGanador.setText("TABLAS");
							hayJuego=false;
						}
					}
					
				}
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
				if (turno==1&&Tresenraya.hayCasillasLibres(plantilla)&&juegoIniciado) {
					if(Tresenraya.jugar(plantilla, 2, 2, j1)) {
						lblNewLabel.setText("Turno j2");
						button_7.setText(""+j1);
						turno=2;
						if (Tresenraya.hayGanador(plantilla)!=0) {
							lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
							lblHasSeleccionado.setText("");
							hayJuego=false;
						}
						if(!Tresenraya.hayCasillasLibres(plantilla)&&((Tresenraya.hayGanador(plantilla)==0))) {
							lblGanador.setText("TABLAS");
							hayJuego=false;
						}
						if(cpu) {
							if(hayJuego) {
							String numeros=Tresenraya.cpu(plantilla);
							cambiarValoresBotones(numeros);
							turno=1;
							lblNewLabel.setText("Turno j1");
							if (Tresenraya.hayGanador(plantilla)!=0) {
								lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
								lblHasSeleccionado.setText("");
								hayJuego=false;
							}
							}
						}
						
					}
					
				}else {
					if(hayJuego) {
					if(Tresenraya.hayCasillasLibres(plantilla)&&juegoIniciado) {
						if(Tresenraya.jugar(plantilla, 2, 2, j2)) {
							lblNewLabel.setText("Turno j1");
							button_7.setText(""+j2);
							turno=1;
							if (Tresenraya.hayGanador(plantilla)!=0) {
								lblGanador.setText("GANADOR J"+Tresenraya.hayGanador(plantilla));
								lblHasSeleccionado.setText("");
								hayJuego=false;
							}
						}	
						if(!Tresenraya.hayCasillasLibres(plantilla)&&((Tresenraya.hayGanador(plantilla)==0))) {
							lblGanador.setText("TABLAS");
							hayJuego=false;
						}
					}
					
				}
				}
			}
			}
		});
		button_7.setBounds(222, 196, 89, 61);
		frame.getContentPane().add(button_7);
		
	    lblNewLabel = new JLabel("TURNOS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(70, 268, 207, 46);
		frame.getContentPane().add(lblNewLabel);
		
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
		
	    lblGanador = new JLabel("");
		lblGanador.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGanador.setBounds(292, 336, 157, 46);
		frame.getContentPane().add(lblGanador);
		
	
	}
	
	public void cambiarValoresBotones(String num){
		int uno=Integer.parseInt(""+num.charAt(0));
		int dos=Integer.parseInt(""+num.charAt(1));
		
		switch (uno) {
		case 0:
			if (dos==0) {
				btnNewButton.setText(""+j2);
			}else {
				if (dos==1) {
					button.setText(""+j2);
				}else {
					button_1.setText(""+j2);
				}
			}
			
			
			break;
		case 1:
			if (dos==0) {
				button_2.setText(""+j2);
			}else {
				if (dos==1) {
					button_3.setText(""+j2);
				}else {
					button_4.setText(""+j2);
				}
			}
			break;
		case 2:
			if (dos==0) {
				button_5.setText(""+j2);
			}else {
				if (dos==1) {
					button_6.setText(""+j2);
				}else {
					button_7.setText(""+j2);
				}
			}
			break;
			
		
		
		}
		
	}
}
