package controlador;

import java.util.ArrayList;

import modelo.Carta;

/**
 * Contexto funciona como memoria central del programa.
 * Aqui se guardan todas las cartas cargadas desde el archivo.
 */
public class Contexto {

	private ArrayList<Carta> cartas = new ArrayList<>();

	public ArrayList<Carta> getCartas() {
		return cartas;
	}

	// Agrega una carta a la coleccion central.
	public void agregarCarta(Carta carta) {
		cartas.add(carta);
	}
}
