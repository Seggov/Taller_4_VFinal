package modelo;

/**
 * Clase base para representar una carta de forma simple.
 * 
 * Por ahora solo guarda los datos generales para ir avanzando de a poco.
 */
public class Carta {
	
	private String nombre;
	private int rareza;
	private String tipo;
	
	public Carta(String nombre, int rareza, String tipo) {
		this.nombre = nombre;
		this.rareza = rareza;
		this.tipo = tipo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getRareza() {
		return rareza;
	}
	
	public String getTipo() {
		return tipo;
	}

}
