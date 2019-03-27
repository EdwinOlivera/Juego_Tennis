//Laura Karina Chincilla Chincilla 20152100165
//Fabiana Yamaly Artiaga Portillo 20151000019

package main;

import java.util.Random;
import java.util.Scanner;

public class ClasePrincipal {
	// Definiendo a los Enum con sus difernete estados y objetos
	public enum SetRealizados {
		NINGUNO, PRIMERO, SEGUNDO, TERCERO;
	}

	public enum JuegosRealizados {
		NINGUNO, PRIMERO, SEGUNDO, TERCERO, CUARTO, QUINTO, SEXTO, SEPTIMO, OCTAVO, NOVENO, DECIMO, FINSET;
	}

	public enum PuntosAsignados {
		NINGUNO, PRIMERO, SEGUNDO, TERCERO;
	}

	public enum Lados {
		IZQUIERDA, DERECHA;
	}

	public enum Estados {
		INDEFINIDO, GANO, EMPATE;
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

	static SetRealizados SetTerminados = SetRealizados.NINGUNO;
	static JuegosRealizados games = JuegosRealizados.NINGUNO;

	static JugadorGanadores jugadorQueGano = JugadorGanadores.INDEFINIDO;
	static Lados ladoPC = Lados.DERECHA; // Lado de lanzamiento de la PC
	static Lados ladoUsuario = Lados.DERECHA; // Lado de lanzamiento del Usuario
	static Estados estado = Estados.INDEFINIDO;
	static Turnos turnoActual; // Establece el turno del lanzador que sigue
	static SaqueInciales primerSaque;
	static Golpes golpe; // Define si acierta o falla un golpe
	static PuntosAsignados sumarPuntosPC = PuntosAsignados.NINGUNO;
	static PuntosAsignados sumarPuntosUsuario = PuntosAsignados.NINGUNO;
	// ***************************

	static String jugadorPC = null;// Son los que se usan para determinar el nombre de los jugadores
	static String jugadorUsuario = null;

	// Variables para los puntos globales
	static int puntosUsuario = 0;
	static int puntosPC = 0;
	static int marcadorPC = 0;
	static int marcadorUsuario = 0;

	// Random
	static Random rdm = new Random();
	static Scanner entrada = new Scanner(System.in);

	static int DireccionDegolpe = 0;

	public static void main(String[] args) {

		// Variables de saque
		int OrdenDeSaque = 1;

		// Variables De direccion Para la PC en random y el usuario lo ingresa
		int Direccion = 0;

		// ArregloJugadores para la PC
		String[] JugadoresTenis = { "Alexander Zverev", "Serena Williams", "Rafael Nadal", "Naomi Osaka",
				"Kei Nishikori" };

		// ConfiguracionDeLosJUGADORES
		jugadorUsuario = "Edwin"; // ColocarNombreDeUsuario(jugadorUsuario, entrada);
		jugadorPC = ColocarNombreDePC(jugadorPC, JugadoresTenis);

		do {// Realiza la asignacion del orden de saque de los jugadores
			OrdenDeSaque = rdm.nextInt(3);
		} while (OrdenDeSaque < 1);
		RealizarPausa();
		
		if (OrdenDeSaque == 1) {
			primerSaque = SaqueInciales.USUARIO;
			System.out.println("\nSaca Incialmente: " + jugadorUsuario);
		} else {
			primerSaque = SaqueInciales.PC;
			System.out.println("\nSaca Incialmente: " + jugadorPC);
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
		GameActual(Direccion);// Esta funcion realiza los game de cada partido

	}

	private static void GameActual(int DireccionDeTiro) {
		do {
			do {
				switch (turnoActual) {
				case USUARIO:
					TurnoUsuario(DireccionDeTiro, entrada);
					break;
				case PC:
					TurnoPC(DireccionDeTiro);
					break;
				}
			} while (estado == Estados.INDEFINIDO);
			MostrarPuntosDeGAME();
			if (estado != Estados.INDEFINIDO) {
				System.out.println("Se hara un cambio de GAME");
				CambioDeGames();
			}
		} while (games != JuegosRealizados.FINSET);
	}

	private static void CambioDeGames() {
		SumarMarcador();
		RealizarPausa();
		switch (games) {
		case NINGUNO:
			System.out.println("Se ha terminado el PRIMER GAME, se comenzara el SEGUNDO------>");
			games = JuegosRealizados.PRIMERO;
			break;
		case PRIMERO:
			System.out.println("Se ha terminado el SEGUNDO GAME, se comenzara el TERCERO------>");
			games = JuegosRealizados.SEGUNDO;
			break;
		case SEGUNDO:
			System.out.println("Se ha terminado el TERCER GAME, se comenzara el CUARTO------>");
			games = JuegosRealizados.TERCERO;
			break;
		case TERCERO:
			System.out.println("Se ha terminado el CUARTO GAME, se comenzara el QUINTO------>");
			games = JuegosRealizados.CUARTO;
			break;
		case CUARTO:
			System.out.println("Se ha terminado el QUINTO GAME, se comenzara el SEXTO------>");
			games = JuegosRealizados.QUINTO;
			break;
		case QUINTO:
			System.out.println("Se ha terminado el SEXTO GAME, se comenzara el SEPTIMO------>");
			games = JuegosRealizados.SEXTO;
			break;
		case SEXTO:
			System.out.println("Se ha terminado el SEPTIMO GAME, se comenzara el OCTAVO------>");
			games = JuegosRealizados.SEPTIMO;
			break;
		case SEPTIMO:
			System.out.println("Se ha terminado el OCTAVO GAME, se comenzara el NOVENO------>");
			games = JuegosRealizados.OCTAVO;
			break;
		case OCTAVO:
			System.out.println("Se ha terminado el NOVENO GAME, se comenzara el DECIMO------>");
			games = JuegosRealizados.NOVENO;
			break;
		case NOVENO:
			System.out.println("Se ha terminado el DECIMO GAME, se comenzara el UNDECIMO------>");
			games = JuegosRealizados.DECIMO;
			break;
		case DECIMO:
			System.out.println("Se ha terminado el UNDECIMO GAME. Y este es el ultimo GAME, y el final del SET Actual------>");
			games = JuegosRealizados.FINSET;
			estado = Estados.INDEFINIDO;
			break;
		case FINSET:
			System.out.println("Falta definir que pasa cuando se termina un SET");
			break;
		default:
			System.out.println("Fuera del rango del Game");
			break;
		}
		RestablecerGAME();
	}

	private static void RestablecerGAME() {
		estado = Estados.INDEFINIDO; // Esto hace que no tenga ningun ganador del GAME actual.
		// Estos son los enum que tienen que reestablcerse para el gamen actual
		jugadorQueGano = JugadorGanadores.INDEFINIDO;
		sumarPuntosPC = PuntosAsignados.NINGUNO;
		sumarPuntosUsuario = PuntosAsignados.NINGUNO;
		puntosPC = 0;
		puntosUsuario = 0;
		// *******************************
		if (games != JuegosRealizados.FINSET) {
			switch (primerSaque) {// Aqui se hace el cambio de SAQUE despues de terminar un GAME
			case PC:
				primerSaque = SaqueInciales.USUARIO;
				RealizarSaque(DireccionDegolpe);
				break;
			case USUARIO:
				primerSaque = SaqueInciales.PC;
				RealizarSaque(DireccionDegolpe);
				break;
			default:
				break;
			}
		}
	}

	private static void SumarMarcador() {
		switch (jugadorQueGano) {
		case PC:
			marcadorPC += 1;
			break;
		case USUARIO:
			marcadorUsuario += 1;
			break;

		default:
			break;
		}
		if (marcadorPC == 6) {
			System.out.println("Gano el SET actual " + jugadorPC);
			games = JuegosRealizados.FINSET;
			estado = Estados.INDEFINIDO;
			MostrarPuntosDeSet();
		} else if (marcadorUsuario == 6) {
			System.out.println("Gano el SET acutal " + jugadorUsuario);
			games = JuegosRealizados.FINSET;
			estado = Estados.INDEFINIDO;
			MostrarPuntosDeSet();
		}

	}

	private static void MostrarPuntosDeGAME() {
		System.out.println("******--------********");
		System.out.println("Los puntos de " + jugadorUsuario + " al final del GAME son :" + puntosUsuario);
		System.out.println("Los puntos de " + jugadorPC + " al final del GAME son :" + puntosPC);
		System.out.println("******--------********");

	}

	private static void MostrarPuntosDeSet() {
		System.out.println("Falta definir como mostrar los puntos al final de los SETs");
	}

	private static void TurnoPC(int DireccionDeSalida) {
		System.out.println("Turno de la PC");
		RealizarPausa();
		do {// Realiza la asignacion la direccion en que le pega a la bola
			DireccionDeSalida = rdm.nextInt(3);
		} while (DireccionDeSalida < 1);

		if (DireccionDeSalida == 1) {
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
			DireccionDeSalida = 1; // entrada.nextInt();
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
			System.out.println("\nDireccion del Golpe: " + ladoUsuario);
			AsignarPuntosPC();
			if (estado == Estados.INDEFINIDO) {
				RealizarSaque(DireccionDeSalida);
			}
		}

	}

	private static void AsignarPuntosPC() {
		switch (sumarPuntosPC) {
		case NINGUNO:
			puntosPC = 15;
			sumarPuntosPC = PuntosAsignados.PRIMERO;
			break;
		case PRIMERO:
			puntosPC = 30;
			sumarPuntosPC = PuntosAsignados.SEGUNDO;
			break;
		case SEGUNDO:
			puntosPC = 40;
			sumarPuntosPC = PuntosAsignados.TERCERO;
			break;
		case TERCERO:
			puntosPC += 0;
			if (puntosUsuario < puntosPC) {
				System.out.println("Ha ganado la PC");
				jugadorQueGano = JugadorGanadores.PC;
				estado = Estados.GANO;
			} else if (puntosUsuario == puntosPC) {
				System.out.println("Se ha empatado el partido");
				jugadorQueGano = JugadorGanadores.INDEFINIDO;
				estado = Estados.EMPATE;
			}

			break;

		default:
			break;
		}
	}

	private static void AsignarPuntosUsuario() {
		switch (sumarPuntosUsuario) {
		case NINGUNO:
			puntosUsuario = 15;
			sumarPuntosUsuario = PuntosAsignados.PRIMERO;
			break;
		case PRIMERO:
			puntosUsuario = 30;
			sumarPuntosUsuario = PuntosAsignados.SEGUNDO;
			break;
		case SEGUNDO:
			puntosUsuario = 40;
			sumarPuntosUsuario = PuntosAsignados.TERCERO;
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
		default:
			break;
		}

	}

	private static void SaqueUsuario(int DireccionDeSalida, Scanner entrada) {
		MostrarPuntos();
		System.out.println("El jugador "+ jugadorUsuario +" esta realizando el SAQUE");
		System.out.printf("\nIngrese el numero de la Direccion deseada hacer el SAQUE: \n1.DERECHA \n2.IZQUIERDA\n");
		do {
			DireccionDeSalida = 1;// entrada.nextInt();

			if (DireccionDeSalida < 1 || DireccionDeSalida > 2) {
				System.out.println("Ha ingresado un valor fuera de rango");
			}
		} while (DireccionDeSalida < 1 || DireccionDeSalida > 2);

		if (DireccionDeSalida == 1) {
			ladoUsuario = Lados.DERECHA;
		} else {
			ladoUsuario = Lados.IZQUIERDA;
		}
		turnoActual = Turnos.PC;

		System.out.println("\nDireccion de saque: " + ladoUsuario);
	}

	private static void SaquePC(int DireccionDeSalida) {
		MostrarPuntos();
		System.out.println("El jugador "+jugadorPC+" esta realizando el SAQUE");
		RealizarPausa();
		do {// Realiza la asignacion la direccion en que sale la bola
			DireccionDeSalida = rdm.nextInt(3);
		} while (DireccionDeSalida < 1);

		if (DireccionDeSalida == 1) {
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
		System.out.println("\nLos puntos actuales de " + jugadorUsuario + " son : " + puntosUsuario);
		System.out.println("Los puntos actuales de " + jugadorPC + " son : " + puntosPC);
	}

	private static String ColocarNombreDePC(String jugadorPC, String[] JugadoresTenis) {
		jugadorPC = JugadoresTenis[rdm.nextInt(JugadoresTenis.length)];
		System.out.printf("\nEl jugador 2: " + jugadorPC);
		System.out.printf("\n");
		return jugadorPC;// Retorna el nombre del jugador de la PC
	}

	private static String ColocarNombreDeUsuario(String jugadorUsuario, Scanner entrada) {
		System.out.print("¨US OPEN¨");
		System.out.print("\nIngrese SU nombre de jugador (Usuario): ");
		jugadorUsuario = entrada.nextLine();
		System.out.print("\nEl jugador 1: " + jugadorUsuario);
		return jugadorUsuario; // Retorna el nombre del jugador del usuario

	}
}
