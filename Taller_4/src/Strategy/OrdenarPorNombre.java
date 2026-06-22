package Strategy;

import java.util.ArrayList;
import java.util.Comparator;

import modelo.Carta;

/**
 * Ordena las cartas alfabeticamente por nombre.
 * Devuelve una copia para no modificar directamente la lista original del Contexto.
 */
public class OrdenarPorNombre implements OrdenamientoStrategy {

	@Override
	public ArrayList<Carta> ordenar(ArrayList<Carta> cartas) {
		ArrayList<Carta> copia = new ArrayList<>(cartas);
		copia.sort(Comparator.comparing(Carta::getNombre));
		return copia;
	}
}
