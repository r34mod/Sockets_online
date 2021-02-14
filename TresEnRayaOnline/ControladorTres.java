package TresEnRayaOnline;

import trenEnRaya.Tresenraya;

public class ControladorTres {
	
	public static boolean jugar(char a[][],int posicionF,int posicionC,char J) {
		boolean fichaPuesta=true;
		if (a[posicionF][posicionC]=='*') {
			a[posicionF][posicionC]=J;
			
		}else {
			fichaPuesta=false;
			
		}
		
	return fichaPuesta;}
	
	public static boolean hayCasillasLibres(char a[][]) {
		boolean hayHueco=false;
		for (int contadorFilas=0;contadorFilas<a.length&&!hayHueco;contadorFilas++)
		{
			for (int contadorColumnas=0;contadorColumnas<a[contadorFilas].length&&!hayHueco;contadorColumnas++) {
			
				if(a[contadorFilas][contadorColumnas]=='*') {
					hayHueco=true;
				}
			}
		}
		
	return hayHueco;}
	
	
	
	
	
	//para mirar una vez terminado si hay algun ganador en diagonal
		public static int mirarDiagonales(char a[][]) {
			int JugadorGanador=0;
			boolean ganador=false;
			if (a[0][0]==a[1][1]&&a[0][0]==a[2][2]&&(a[0][0]=='O'||a[0][0]=='X')) {
				ganador=true;
							
			}else {
				if (a[0][2]==a[1][1]&&a[0][2]==a[2][0]&&(a[1][1]=='O'||a[1][1]=='X')) {
					ganador=true;
				}
				
			}
			if (ganador) {
				if( a[1][1]=='O') {
					JugadorGanador=1;
					
				}else {
					if( a[1][1]=='X') {
						JugadorGanador=2;
						
					}
					
				}
				
			}
			
		return JugadorGanador;}
		
		//para mirar una vez terminado si hay algun ganador en Filas
		public static int mirarFilas(char a[][]) {
			int JugadorGanador=0;
			//crear un switch
			int filas=3;
			boolean ganador=false;
			if (a[0][0]==a[0][1]&&a[0][0]==a[0][2]&&(a[0][0]=='O'||a[0][0]=='X')) {
				ganador=true;
				filas=0;		
			}else {
				if (a[1][0]==a[1][1]&&a[1][0]==a[1][2]&&(a[1][1]=='O'||a[1][1]=='X')) {
					ganador=true;
					filas=1;
				}else {
					if (a[2][0]==a[2][1]&&a[2][0]==a[2][2]&&(a[2][0]=='O'||a[2][0]=='X')) {
						ganador=true;
						filas=2;
					}
					
				}
				
			}
			if (ganador) {
				switch(filas) {
				case 0:
					if( a[0][0]=='O') {
						JugadorGanador=1;
					}else {
						JugadorGanador=2;
					}
					break;
				case 1:
					if( a[1][0]=='O') {
						JugadorGanador=1;
					}else {
						JugadorGanador=2;
					}
					break;
				case 2:
					if( a[2][0]=='O') {
						JugadorGanador=1;
					}else {
						JugadorGanador=2;
					}
					break;
				
				}
			
				
			}
			
		return JugadorGanador;}
		
		//para mirar una vez terminado si hay algun ganador en columnas
		public static int mirarColumnas(char a[][]) {
			int JugadorGanador=0;
			int columnas=3;
			boolean ganador=false;
			if (a[0][0]==a[1][0]&&a[0][0]==a[2][0]&&(a[0][0]=='O'||a[0][0]=='X')) {
				ganador=true;
				columnas=0;
							
			}else {
				if (a[0][1]==a[1][1]&&a[0][1]==a[2][1]&&(a[1][1]=='O'||a[1][1]=='X')) {
					ganador=true;
					columnas=1;
				}else {
					if (a[0][2]==a[1][2]&&a[0][2]==a[2][2]&&(a[0][2]=='O'||a[0][2]=='X')) {
						ganador=true;
						columnas=2;
					}
					
				}
				
			}
			if (ganador) {
				switch(columnas) {
				case 0:
					if( a[0][0]=='O') {
						JugadorGanador=1;
					}else {
						JugadorGanador=2;
					}
					break;
				case 1:
					if( a[0][1]=='O') {
						JugadorGanador=1;
					}else {
						JugadorGanador=2;
					}
					break;
				case 2:
					if( a[0][2]=='O') {
						JugadorGanador=1;
					}else {
						JugadorGanador=2;
					}
					break;
				
				}
				
			}
			
		return JugadorGanador;}
		
		
		public static int hayGanador(char a[][]) {
			int ganador=0;
			
			if (Tresenraya.mirarColumnas(a)!=0) {
				ganador=Tresenraya.mirarColumnas(a);
			}else {
				
				if (Tresenraya.mirarFilas(a)!=0) {
					ganador=Tresenraya.mirarFilas(a);
				}else {
					
					if (Tresenraya.mirarDiagonales(a)!=0) {
						ganador=Tresenraya.mirarDiagonales(a);
						
					}
					
				}
				
			}
		return ganador;}
		
		//movimiento de la CPU
		public static String cpu(char a[][]) {
			String posicion="";
			boolean movimientoRealizado=false;
			for(int contadorFilas=0;contadorFilas<=2&&!movimientoRealizado;contadorFilas++) {
				
				for(int contadorColumnas=0;contadorColumnas<=2&&!movimientoRealizado;contadorColumnas++) {
					if (a[contadorFilas][contadorColumnas]=='*') {
						
						a[contadorFilas][contadorColumnas]='X';
						movimientoRealizado=true;
						posicion+=contadorFilas+""+contadorColumnas;
						
					}
					
				}
			}
			
		return posicion;}

}
