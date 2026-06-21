package controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Vista.SistemaInterfaz;
import modelo.Carta;
import modelo.Energy;
import modelo.Item;
import modelo.Pokemon;
import modelo.Supporter;

public class Sistema {

	private Contexto contexto = new Contexto();
	private SistemaInterfaz gui = new SistemaInterfaz();

	// Integramos Singelton PARA CREAR UNA SOLA INSTANCIA
	private static Sistema instanciaUnica;

	private Sistema() {
	}

	public static Sistema getInstancia() {
		if (instanciaUnica == null) {
			instanciaUnica = new Sistema();
		}
		return instanciaUnica;
	}

	public Contexto getContexto() {
		return contexto;
	}

	// inicializamos SISTEMA
	public void iniciarSistema() {
		cargarCartasDesdeTxt();
		gui.creacionInterfazGeneral();
	}

	private void cargarCartasDesdeTxt() {
		try (BufferedReader lector = new BufferedReader(new FileReader("Sobres.txt"))) {
			String linea;
			while ((linea = lector.readLine()) != null) {
				Carta carta = crearCartaDesdeLinea(linea);
				if (carta != null) {
					contexto.agregarCarta(carta);
				}
			}
		} catch (IOException e) {
			System.out.println("No se pudo leer Sobres.txt");
		}
	}

	private Carta crearCartaDesdeLinea(String linea) {
		String[] datos = linea.split(";");
		String nombre = datos[0];
		int rareza = Integer.parseInt(datos[1]);
		String tipo = datos[2];

		if (tipo.equals("Pokemon")) {
			int dano = Integer.parseInt(datos[3]);
			int cantEnergias = Integer.parseInt(datos[4]);
			return new Pokemon(nombre, rareza, dano, cantEnergias);
		}

		if (tipo.equals("Item")) {
			int bonificacion = Integer.parseInt(datos[3]);
			return new Item(nombre, rareza, bonificacion);
		}

		if (tipo.equals("Supporter")) {
			int efectosPorTurno = Integer.parseInt(datos[3]);
			return new Supporter(nombre, rareza, efectosPorTurno);
		}

		if (tipo.equals("Energy")) {
			String elemento = datos[3];
			return new Energy(nombre, rareza, elemento);
		}

		return null;
	}
}
