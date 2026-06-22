package modelo;

import visitor.CartaVisitor;

/**
 * Carta de tipo Item, con una bonificacion como atributo propio.
 */
public class Item extends Carta {

	private int bonificacion;

	public Item(String nombre, int rareza, int bonificacion) {
		super(nombre, rareza);
		this.bonificacion = bonificacion;
	}

	public int getBonificacion() {
		return bonificacion;
	}

	@Override
	public String getTipo() {
		return "Item";
	}

	@Override
	public void aceptar(CartaVisitor visitor) {
		visitor.visitar(this);
	}
}
