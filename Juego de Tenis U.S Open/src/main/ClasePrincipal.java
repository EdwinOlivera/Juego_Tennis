package main;

import java.util.Random;
import java.util.Scanner;

public class ClasePrincipal {
	// Definiendo a los Enum con sus difernete estados y objetos
	public enum AsignarPuntos {
		NINGUNO, PRIMERO, SEGUNDO, TERCERO, CUARTO;
	}

	public enum Lados {
		IZQUIERDA, DERECHA;
	}

	public enum Estados {
		INDEFINIDO, PERDIO, GANO, EMPATE;
	}

	public enum Turnos {
		USUARIO, PC;
	}

	public enum SaqueInciales {
		USUARIO, PC;
	}

	public enum Golpes {
		ACERTO, FALLO;
	}

	public enum JugadorGanadores {
		INDEFINIDO, PC, USUARIO;
	}

	static JugadorGanadores jugadorQueGano = JugadorGanadores.INDEFINIDO;
	static Lados ladoPC = Lados.DERECHA; // Lado de lanzamiento de la PC
	static Lados ladoUsuario = Lados.DERECHA; // Lado de lanzamiento del Usuario
	static Estados estado = Estados.INDEFINIDO;
	static Turnos turnoActual; // Establece el turno del lanzador que sigue
	static SaqueInciales primerSaque;
	static Golpes golpe; // Define si acierta o falla un golpe
	static AsignarPuntos sumarPuntosPC = AsignarPuntos.NINGUNO;
	static AsignarPuntos sumarPuntosUsuario = AsignarPuntos.NINGUNO;
	// ***************************

	static String jugadorPC = null;
	static String jugadorUsuario = null;

	// Variables para los puntos globales
	static int puntosUsuario = 0;
	static int puntosPC = 0;
	// Random
	static Random rdm = new Random();
	static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {

		// Variables de saque
		int OrdenDeSaque = 1;

		// Variables De direccion
		int Direccion = 0;

		// ArregloJugadores para la PC
		String[] JugadoresTenis = { "Alexander Zverev", "Serena Williams", "Rafael Nadal", "Naomi Osaka",
				"Kei Nishikori" };

		// ConfiguracionDeLosJUGADORES
		jugadorUsuario = "Edwin";// ColocarNombreDeUsuario(jugadorUsuario,entrada);
		jugadorPC = ColocarNombreDePC(jugadorPC, JugadoresTenis);

		do {// Realiza la asignacion del orden de saque de los jugadores
			OrdenDeSaque = 1; // rdm.nextInt(3);
		} while (OrdenDeSaque < 1);
		RealizarPausa();
		if (OrdenDeSaque == 1) {
			primerSaque = SaqueInciales.USUARIO;
			System.out.println("\nSaca: " + jugadorUsuario);
		} else {
			primerSaque = SaqueInciales.PC;
			System.out.println("\nSaca: " + jugadorPC);
		}
		// Este switch realiza el primer saque
		switch (primerSaque) {
		case USUARIO:
			SaqueUsuario(Direccion, entrada);
			break;
		case PC:
			SaquePC(Direccion);
			break;
		}

		do {
			switch (turnoActual) {
			case USUARIO:
				TurnoUsuario(Direccion, entrada);
				break;
			case PC:
				TurnoPC(Direccion);
				break;
			}

		} while (estado == Estados.INDEFINIDO);
		System.out.println("Los puntos de " + jugadorUsuario + " son :" + puntosUsuario);
		System.out.println("Los puntos de " + jugadorPC + " son :" + puntosPC);
		System.out.println("******Final del JUEGO********");
	}

	private static void TurnoPC(int DireccionDeSalida) {
		System.out.println("Turno de la PC");
		RealizarPausa();
		
		DireccionDeSalida = rdm.nextInt(100 + 1);
		if (DireccionDeSalida <= 50) {
			ladoPC = Lados.DERECHA;
		} else {
			ladoPC = Lados.IZQUIERDA;
		}

		if (ladoPC == ladoUsuario) {
			golpe = Golpes.ACERTO;
			turnoActual = Turnos.USUARIO;
		} else {
			golpe = Golpes.FALLO;
			AsignarPuntosUsuario();
			if (estado == Estados.INDEFINIDO) {
				RealizarSaque(DireccionDeSalida);
			}
		}
	}

	private static void TurnoUsuario(int DireccionDeSalida, Scanner entrada) {
		System.out.printf("\nIngrese el numero de la Direccion deseada Pegar: \n1.DERECHA \n2.IZQUIERDA\n");
		do {
			DireccionDeSalida = entrada.nextInt();
			if (DireccionDeSalida > 2 || DireccionDeSalida < 1) {
				System.out.println("Ha ingresado una opcion no valida");
			}
		} while (DireccionDeSalida > 2 || DireccionDeSalida < 1);

		if (DireccionDeSalida == 1) {
			ladoUsuario = Lados.DERECHA;
		} else {
			ladoUsuario = Lados.IZQUIERDA;
		}

		if (ladoUsuario == ladoPC) {
			turnoActual = Turnos.PC;
			golpe = Golpes.ACERTO;
			System.out.println("\nDireccion del Golpe: " + ladoUsuario);
		} else {
			golpe = Golpes.FALLO;
			AsignarPuntosPC();
			if (estado == Estados.INDEFINIDO) {
				RealizarSaque(DireccionDeSalida);
			}
		}

	}

	private static void AsignarPuntosPC() {
		switch (sumarPuntosPC) {
		case NINGUNO:
			puntosPC += 15;
			sumarPuntosPC = AsignarPuntos.PRIMERO;
			break;
		case PRIMERO:
			puntosPC += 30;
			sumarPuntosPC = AsignarPuntos.SEGUNDO;
			break;
		case SEGUNDO:
			puntosPC += 40;
			sumarPuntosPC = AsignarPuntos.TERCERO;
			break;
		case TERCERO:
			puntosPC += 1;
			if (puntosUsuario < puntosPC) {
				System.out.println("Ha ganado la PC");
				jugadorQueGano = JugadorGanadores.PC;
				estado = Estados.GANO;
			} else if (puntosUsuario == puntosPC) {
				System.out.println("Se ha empatado el partido");
				jugadorQueGano = JugadorGanadores.INDEFINIDO;
				estado = Estados.EMPATE;
			}
			sumarPuntosPC = AsignarPuntos.CUARTO;
			break;
		case CUARTO:
			System.out.println("Falta definir la cuarta suma para la PC");
			break;
		}
	}

	private static void AsignarPuntosUsuario() {
		switch (sumarPuntosUsuario) {
		case NINGUNO:
			puntosUsuario += 15;
			sumarPuntosUsuario = AsignarPuntos.PRIMERO;
			break;
		case PRIMERO:
			puntosUsuario += 30;
			sumarPuntosUsuario = AsignarPuntos.SEGUNDO;
			break;
		case SEGUNDO:
			puntosUsuario += 40;
			sumarPuntosUsuario = AsignarPuntos.TERCERO;
			break;
		case TERCERO:
			puntosUsuario += 0;
			if (puntosPC < puntosUsuario) {
				System.out.println("Ha ganado El usuario");
				jugadorQueGano = JugadorGanadores.USUARIO;
				estado = Estados.GANO;
			} else if (puntosUsuario == puntosPC) {
				System.out.println("Se ha empatado el partido");
				jugadorQueGano = JugadorGanadores.INDEFINIDO;
				estado = Estados.EMPATE;
			}
			break;
		case CUARTO:
			System.out.println("Falta definir la cuarta suma el Usuario");
			break;

		default:
			break;
		}
	}

	private static void SaqueUsuario(int DireccionDeSalida, Scanner entrada) {
		MostrarPuntos();
		System.out.printf("\nEl usario hara un SAQUE");
		System.out.printf("\nIngrese el numero de la Direccion deseada hacer el SAQUE: \n1.DERECHA \n2.IZQUIERDA\n");
		DireccionDeSalida = entrada.nextInt();
		if (DireccionDeSalida == 1) {
			ladoUsuario = Lados.DERECHA;
		} else {
			ladoUsuario = Lados.IZQUIERDA;
		}
		turnoActual = Turnos.PC;
		// RealizarPausa();
		System.out.println("\nDireccion de saque: " + ladoUsuario);
	}

	private static void SaquePC(int DireccionDeSalida) {
		MostrarPuntos();
		System.out.println("La PC esta realizando el SAQUE");
		RealizarPausa();
		DireccionDeSalida = rdm.nextInt(100 + 1);

		if (DireccionDeSalida <= 50) {
			ladoPC = Lados.DERECHA;
		} else {
			ladoPC = Lados.IZQUIERDA;
		}
		turnoActual = Turnos.USUARIO;

	}

	private static void RealizarSaque(int DireccionDeSalida) {
		switch (primerSaque) {
		case USUARIO:
			SaqueUsuario(DireccionDeSalida, entrada);
			break;
		case PC:
			SaquePC(DireccionDeSalida);
			break;

		default:
			break;
		}

	}

	private static void RealizarPausa() {
		// MostrarPuntos();
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

	private static void MostrarPuntos() {
		System.out.println("Los puntos de " + jugadorUsuario + " son : " + puntosUsuario);
		System.out.println("Los puntos de " + jugadorPC + " son : " + puntosPC);
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
