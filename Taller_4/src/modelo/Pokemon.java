package modelo;

import visitor.CartaVisitor;

/**
 * Carta de tipo Pokemon, con dano y cantidad de energias.
 */
public class Pokemon extends Carta {

	private int dano;
	private int cantEnergias;

	public Pokemon(String nombre, int rareza, int dano, int cantEnergias) {
		super(nombre, rareza);
		this.dano = dano;
		this.cantEnergias = cantEnergias;
	}

	public int getDano() {
		return dano;
	}

	public int getCantEnergias() {
		return cantEnergias;
	}

	@Override
	public String getTipo() {
		return "Pokemon";
	}

	@Override
	public void aceptar(CartaVisitor visitor) {
		visitor.visitar(this);
	}
}
