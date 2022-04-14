import java.util.Scanner;

public class BuscaMinas {
	/*
	 * Teniendo una matriz de 6x7 de char con 10 minas colocadas aleatoriamente (no
	 * repetidas), pide al usuario fila y columna (solo zonas que no se hayan
	 * explorado). El jugador gana cuando no pisa ninguna mina durante 5 turnos
	 * seguidos. Si pisa una mina, el juego termina. Estos son los valores que
	 * debemos mostrar: - V = Vacio - M = Mina - X = No explorado
	 */
	public static void main(String[] args) {
		// declaramos variables a usar
		boolean pisaMina = false;
		// si te quedas sin turnos pierdes
		int turnos = 5;
		// contador de minas
		int minas = 10;
		// declaramos un array para las posiciones
		int[] numeros = { 1, 2, 3, 4, 5, 6, 7 };
		//se usaran mas adelante
		int fila = 0, columna = 0;
		// creamos dos campos de juego, uno visible para el usuario, el otro llevara las minas
		char[][] campoVisible = new char[6][7];
		char[][] campoMinas = new char[6][7];
		// rellenamos con '-' todos los campos
		for (int i = 0; i < campoVisible.length; i++) {
			for (int j = 0; j < campoVisible[0].length; j++) {
				campoVisible[i][j] = '-';
				campoMinas[i][j] = '-';
			}
		}
		//sustuimos los guiones por minas en el array no visible
		while (minas != 0) {
			generadorDeMinas(campoMinas);
			minas--;
		}
		// declaramos un scanner para recoger filas/columnas
		Scanner sc = new Scanner(System.in);
		System.out.println("Busca Minas!");
		System.out.println("Reglas:");
		System.out.println("- Si pisas una mina pierdes.");
		System.out.println("- Si aguantas 5 ganas.");
		// logica del programa
		//en cada vuelta se imprimira el campo de minas actualizado, hasta que se gane/pierda
		while (turnos != 0 && pisaMina != true) {
			imprimirCampo(numeros, campoVisible);
			System.out.println("");
			System.out.println("Te quedan " + turnos + " turnos para ganar!");
			//try/catch para evitar que se pare el programa, dado que el campo no es igual en ambas 'direcciones'
			try {
				System.out.println("\nIntroduce la Fila (F<=6): ");
				fila = sc.nextInt();
				System.out.println("Introduce la Columna (C<=7): ");
				columna = sc.nextInt();
				//serie de condicionales
				// para cuando pierdas
				if (campoMinas[fila - 1][columna - 1] == 'M') {
					pisaMina = true;
					System.out.println("Pisaste una Mina, Perdiste!");
					imprimirCampo(numeros, campoMinas);
				//si me das uno que ya estaba dicho
				} else if (campoMinas[fila - 1][columna - 1] == 'V') {
					System.out.println("Ese campo ya es Visible.");
					System.out.println("Dime otro!");
				//para el resto
				} else {
					campoVisible[fila - 1][columna - 1] = 'V';
					campoMinas[fila - 1][columna - 1] = 'V';
					turnos--;
					System.out.println("El campo estaba vacio.");
				}
				// metemos un salto de linea al final para que no se pisen las impresiones
				System.out.println("");
				//dependiendo de las filas/columnas, se da un mensaje u otro.
			} catch (ArrayIndexOutOfBoundsException e) {
				if (fila >= 6) {
					System.out.println("Nro Fila superior a 6.");
					System.out.println("Introduce un Nro de fila menor o igual que 6.");
				} else if (columna >= 7) {
					System.out.println("Nro Columna superior a 7.");
					System.out.println("Introduce un Nro de columna menor o igual que 7.");
				}
				
			}
			//este esta bastante claro.
			if(turnos == 0) {
				System.out.println("Felicidades! has ganado.");
				imprimirCampo(numeros, campoMinas);
			}
		}
		//cerramos el scanner.
		sc.close();

	}

	// declaracion de metodos.
	// colocara las minas
	static void generadorDeMinas(char[][] arr) {
		arr[genRan6()][genRan7()] = 'M';
	}
	
	// genera un numero aleatorio entre 0 y 6
	static int genRan6() {
		return (int) (Math.random() * 6) + 0;
	}

	// genera un numero aleatorio entre 0 y 7
	static int genRan7() {
		return (int) (Math.random() * 7) + 0;
	}
	//pues imprime el campo basicamente.
	static void imprimirCampo(int[] numeros, char[][] campo) {
		// imprime campo visible
		for (int i = 0; i < numeros.length; i++) {
			System.out.print("  " + numeros[i]);
		}
		for (int i = 0; i < campo.length; i++) {
			System.out.println("");
			System.out.print(numeros[i]);
			for (int j = 0; j < campo[0].length; j++) {
				System.out.print("[" + campo[i][j] + "]");
			}
		}
	}

}
