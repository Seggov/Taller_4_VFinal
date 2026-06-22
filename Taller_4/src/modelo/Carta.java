package modelo;

import visitor.CartaVisitor;
import visitor.PoderVisitor;

/**
 * Clase base para todas las cartas de la coleccion.
 * Guarda los datos comunes y delega el calculo de poder al Visitor.
 *
 * La clase es abstracta porque no se crean cartas genericas:
 * siempre se crea una subclase como Pokemon, Item, Supporter o Energy.
 */
public abstract class Carta {

	protected String nombre;
	protected int rareza;

	public Carta(String nombre, int rareza) {
		this.nombre = nombre;
		this.rareza = rareza;
	}

	public String getNombre() {
		return nombre;
	}

	public int getRareza() {
		return rareza;
	}

	// Cada carta hija informa su tipo concreto en texto.
	public abstract String getTipo();

	// Punto de entrada del Visitor. Cada hija llama al metodo visitar correcto.
	public abstract void aceptar(CartaVisitor visitor);

	// Calcula el poder usando Visitor para mantener las formulas fuera de Sistema.
	public int calcularPoder() {
		PoderVisitor visitor = new PoderVisitor();
		aceptar(visitor);
		return visitor.getPoder();
	}
}
