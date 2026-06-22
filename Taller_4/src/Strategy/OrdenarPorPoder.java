package Strategy;

import java.util.ArrayList;
import java.util.Comparator;

import modelo.Carta;

/**
 * Ordena las cartas por poder calculado, dejando primero las mas poderosas.
 * Usa Carta.calcularPoder(), que internamente aplica el Visitor.
 */
public class OrdenarPorPoder implements OrdenamientoStrategy {

	
	@Override
	public ArrayList<Carta> ordenar(ArrayList<Carta> cartas) {
		ArrayList<Carta> copia = new ArrayList<>(cartas);
		copia.sort(Comparator.comparingInt(Carta::calcularPoder).reversed());
		return copia;
	}
}
