package Strategy;

import java.util.ArrayList;
import java.util.Comparator;

import modelo.Carta;

/**
 * Ordena las cartas por rareza, dejando primero las cartas mas raras.
 * Devuelve una copia para conservar intacta la coleccion original.
 */
public class OrdenarPorRareza implements OrdenamientoStrategy {

	@Override
	public ArrayList<Carta> ordenar(ArrayList<Carta> cartas) {
		ArrayList<Carta> copia = new ArrayList<>(cartas);
		copia.sort(Comparator.comparingInt(Carta::getRareza).reversed());
		return copia;
	}
}
