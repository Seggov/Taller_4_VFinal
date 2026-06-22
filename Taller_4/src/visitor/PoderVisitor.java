package visitor;

import modelo.Energy;
import modelo.Item;
import modelo.Pokemon;
import modelo.Supporter;

/**
 * Visitor que centraliza las formulas de poder exigidas por el enunciado.
 *
 * Cada metodo visitar contiene la formula especifica de un tipo de carta.
 * Carta.calcularPoder() crea este Visitor, la carta lo acepta y el Visitor
 * guarda el resultado en el atributo poder.
 */
public class PoderVisitor implements CartaVisitor {

	private int poder;

	@Override
	public void visitar(Pokemon pokemon) {
		if (pokemon.getCantEnergias() == 0) {
			poder = 0;
		} else {
			poder = (pokemon.getDano() / pokemon.getCantEnergias()) * 100;
		}
	}

	@Override
	public void visitar(Item item) {
		poder = item.getBonificacion() * 20;
	}

	@Override
	public void visitar(Supporter supporter) {
		poder = supporter.getEfectosPorTurno() * 50;
	}

	@Override
	public void visitar(Energy energy) {
		poder = 1;
	}

	public int getPoder() {
		return poder;
	}
}
