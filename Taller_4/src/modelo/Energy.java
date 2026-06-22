package modelo;

import visitor.CartaVisitor;

/**
 * Carta de tipo Energy, con elemento como atributo propio.
 */
public class Energy extends Carta {

	private String elemento;

	public Energy(String nombre, int rareza, String elemento) {
		super(nombre, rareza);
		this.elemento = elemento;
	}

	public String getElemento() {
		return elemento;
	}

	@Override
	public String getTipo() {
		return "Energy";
	}

	@Override
	public void aceptar(CartaVisitor visitor) {
		visitor.visitar(this);
	}
}
