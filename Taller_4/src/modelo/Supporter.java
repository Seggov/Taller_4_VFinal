package modelo;

import visitor.CartaVisitor;

/**
 * Carta de tipo Supporter, con efectos por turno como atributo propio.
 */
public class Supporter extends Carta {

	private int efectosPorTurno;

	public Supporter(String nombre, int rareza, int efectosPorTurno) {
		super(nombre, rareza);
		this.efectosPorTurno = efectosPorTurno;
	}

	public int getEfectosPorTurno() {
		return efectosPorTurno;
	}

	@Override
	public String getTipo() {
		return "Supporter";
	}

	@Override
	public void aceptar(CartaVisitor visitor) {
		visitor.visitar(this);
	}
}
