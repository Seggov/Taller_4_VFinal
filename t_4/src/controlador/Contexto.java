package controlador;

import java.util.ArrayList;

import modelo.Carta;

public class Contexto {

	private ArrayList<Carta> cartas = new ArrayList<>();

	public ArrayList<Carta> getCartas() {
		return cartas;
	}

	public void agregarCarta(Carta carta) {
		cartas.add(carta);
	}
}
