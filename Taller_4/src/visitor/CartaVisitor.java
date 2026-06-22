package visitor;

import modelo.Energy;
import modelo.Item;
import modelo.Pokemon;
import modelo.Supporter;

/**
 * Visitor base para ejecutar una operacion distinta segun el tipo real de carta.
 *
 * Cada metodo visitar recibe una subclase concreta.
 * Asi una operacion puede comportarse diferente para Pokemon, Item,
 * Supporter o Energy sin llenar Sistema con condicionales.
 */
public interface CartaVisitor {

	void visitar(Pokemon pokemon);

	void visitar(Item item);

	void visitar(Supporter supporter);

	void visitar(Energy energy);
}
