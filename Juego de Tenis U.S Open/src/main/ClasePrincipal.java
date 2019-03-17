package main;

import java.util.Random;
import java.util.Scanner;

public class ClasePrincipal {
	// Definiendo a los Enum con sus difernete estados y objetos
	public enum Lados {
		IZQUIERDA, DERECHA;
	}

	public enum Estado {
		INDEFINIDO, PERDIO, GANO, EMPATE;
	}

	public enum Turno {
		USUARIO, PC;
	}

	public enum SaqueIncial {
		USUARIO, PC;
	}

	static Lados ladoPC = Lados.DERECHA; // Lado de lanzamiento de la PC
	static Lados ladoUsuario = Lados.DERECHA; // Lado de lanzamiento del Usuario
	static Estado Estados = Estado.INDEFINIDO;
	static Turno turnoActual;//Establece el turno del lanzador que sigue
	static SaqueIncial PrimerSaque;
	// ***************************
	// Variables para los puntos globales
	static int puntosUsuario = 0;
	static int puntosPC = 0;
	// Random
	static Random rdm = new Random();

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		// Variables De jugadore
		String jugadorPC = null;
		String jugadorUsuario = null;
		// Variables de saque
		int OrdenDeSaque = 1;

		// Variables De direccion
		String Direccion1;
		int Direccion = 0;

		// ArregloJugadores
		String[] JugadoresTenis = { "Alexander Zverev", "Serena Williams", "Rafael Nadal", "Naomi Osaka",
				"Kei Nishikori" };

		// ConfiguracionDeLosJUGADORES
		jugadorUsuario = "Edwin";// ColocarNombreDeUsuario(jugadorUsuario,entrada);
		jugadorPC = ColocarNombreDePC(jugadorPC, JugadoresTenis);

		do {// Realiza la asignacion del orden de saque de los jugadores
			OrdenDeSaque = 1; // rdm.nextInt(3);
		} while (OrdenDeSaque < 1);

		if (OrdenDeSaque == 1) {
			PrimerSaque = SaqueIncial.USUARIO;
			System.out.println("\nSaca: " + jugadorUsuario);
		} else {
			PrimerSaque = SaqueIncial.PC;
			System.out.println("\nSaca: " + jugadorPC);
		}
		//Este switch realiza el primer saque 
		switch (PrimerSaque) {
		case USUARIO:
			SaqueJugador(Direccion, entrada);
			break;
		case PC:
			SaquePC(Direccion);
			break;
		}
		System.out.println("Los puntos de la Pc son :" + puntosPC);
		System.out.println("Los puntos de la Usuario son :" + puntosUsuario);
		System.out.println("******Final del Programa********");
	}

	private static void SaqueJugador(int DireccionDeSalida, Scanner entrada) {
		System.out.printf("\nIngrese el numero de la Direccion deseada: \n1.DERECHA \n2.IZQUIERDA\n");
		DireccionDeSalida = entrada.nextInt();
		if (DireccionDeSalida == 1) {
			ladoUsuario = Lados.DERECHA;
		} else {
			ladoUsuario = Lados.IZQUIERDA;
		}
		RealizarPausa();
		System.out.println("\n Direccion de saque: " + ladoUsuario);
		for (int i = 0; i < 10; i++) {
			RematePC(DireccionDeSalida);
		}

	}
	private static void RematePC(int DireccionDeSalida) {
		System.out.println("Realizando Remate la PC");
		RealizarPausa();
		do {// Realiza la asignacion la direccion en que sale la bola
			DireccionDeSalida = rdm.nextInt(3);
		} while (DireccionDeSalida < 1);

		if (DireccionDeSalida == 1) {
			ladoPC = Lados.DERECHA;
		} else {
			ladoPC = Lados.IZQUIERDA;
		}
		if (ladoPC == ladoUsuario) {
			puntosPC += 1;
		} else {
			puntosUsuario += 1;
		}

	}
	private static void SaquePC(int DireccionDeSalida) {
		do {// Realiza la asignacion la direccion en que sale la bola
			DireccionDeSalida = rdm.nextInt(3);
		} while (DireccionDeSalida < 1);

		if (DireccionDeSalida == 1) {
			ladoPC = Lados.DERECHA;
		} else {
			ladoPC = Lados.IZQUIERDA;
		}
		RealizarPausa();
	}
	private static void RealizarPausa() {
		int TiempoDeEspera = 1000;
		try {
			for (int i = 0; i < 10; i++) {
				Thread.sleep(TiempoDeEspera / 10);
				System.out.printf(" * ");
			}
			System.out.println();
			Thread.sleep(TiempoDeEspera); // Con esto se realiza la pausa del programa.
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	private static String ColocarNombreDePC(String jugadorPC, String[] JugadoresTenis) {
		jugadorPC = JugadoresTenis[rdm.nextInt(JugadoresTenis.length)];
		System.out.printf("\nEl jugador 2: " + jugadorPC);
		System.out.printf("\n");
		return jugadorPC;// Retorna el nombre del jugador de la PC
	}
	private static String ColocarNombreDeUsuario(String jugadorUsuario, Scanner entrada) {
		// TODO Auto-generated method stub
		System.out.print("¨US OPEN¨");
		System.out.print("\nIngrese el nombre del Jugador 1: ");
		jugadorUsuario = entrada.nextLine();
		System.out.print("\nEl jugador 1: " + jugadorUsuario);
		return jugadorUsuario; // Retorna el nombre del jugador del usuario

	}
}
