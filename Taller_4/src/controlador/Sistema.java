package controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Vista.SistemaInterfaz;
import Factory.CartaFactory;
import modelo.Carta;
import Strategy.OrdenamientoStrategy;
import Strategy.OrdenarPorNombre;

/**
 * Clase central del programa.
 * Coordina la lectura del archivo, el contexto de cartas y la interfaz grafica.
 *
 * Patron Singleton:
 * Esta clase se crea una sola vez mediante getInstancia(). Asi todas las partes
 * del programa trabajan con el mismo Contexto y no aparecen varias colecciones
 * separadas de cartas.
 */
public class Sistema {

	private Contexto contexto = new Contexto();
	private SistemaInterfaz gui = new SistemaInterfaz();
	private CartaFactory cartaFactory = new CartaFactory();

	private static Sistema instanciaUnica;

	private Sistema() {
	}

	/**
	 * Singleton: entrega una unica instancia de Sistema para toda la aplicacion.
	 */
	public static Sistema getInstancia() {
		if (instanciaUnica == null) {
			instanciaUnica = new Sistema();
		}
		return instanciaUnica;
	}

	public Contexto getContexto() {
		return contexto;
	}

	/**
	 * Punto de inicio del sistema: primero carga cartas y luego abre la GUI.
	 */
	public void iniciarSistema() {
		cargarCartasDesdeTxt();
		gui.creacionInterfazGeneral();
	}

	/**
	 * Lee Sobres.txt linea por linea y usa Factory para crear cada carta.
	 *
	 * Patron Factory:
	 * Sistema solo lee texto. La decision de que clase concreta construir
	 * queda en CartaFactory, por eso aqui no aparecen new Pokemon, new Item, etc.
	 */
	private void cargarCartasDesdeTxt() {
		File archivo = new File("Sobres.txt");
		int cartasCargadas = 0;

		try (Scanner lector = new Scanner(archivo)) {
			while (lector.hasNextLine()) {
				String linea = lector.nextLine();
				Carta carta = cartaFactory.crearCartaDesdeLinea(linea);
				if (carta != null) {
					contexto.agregarCarta(carta);
					cartasCargadas++;
				}
			}
			System.out.println("Cartas cargadas desde Sobres.txt: " + cartasCargadas);
		} catch (FileNotFoundException e) {
			System.out.println("No se pudo leer Sobres.txt en: " + archivo.getAbsolutePath());
		}
	}

	/**
	 * Ordena la coleccion usando la estrategia recibida.
	 *
	 * Patron Strategy:
	 * El metodo recibe una estrategia distinta segun el orden deseado.
	 * Sistema no necesita saber si se ordena por nombre, rareza o poder.
	 */
	public ArrayList<Carta> ordenarColeccion(OrdenamientoStrategy estrategia) {
		return estrategia.ordenar(contexto.getCartas());
	}

	/**
	 * Muestra una version simple de la coleccion por consola usando una estrategia.
	 */
	public void mostrarColeccionOrdenada(OrdenamientoStrategy estrategia) {
		ArrayList<Carta> cartasOrdenadas = ordenarColeccion(estrategia);
		for (Carta carta : cartasOrdenadas) {
			System.out.println(carta.getNombre() + " | " + carta.getTipo() + " | Poder: " + carta.calcularPoder());
		}
	}

	/**
	 * Muestra una version simple de la coleccion por consola.
	 * Luego la GUI puede reutilizar esta informacion para dibujar las cartas.
	 */
	public void verColeccion() {
		mostrarColeccionOrdenada(new OrdenarPorNombre());
	}
}
