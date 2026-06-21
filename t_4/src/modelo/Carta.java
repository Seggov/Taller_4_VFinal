package modelo;

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
	
	// obligamos a LA HIJA A IMPRIMIR UN TEXTO, CADA TEXTO VA SER DIFERENTE
	public abstract String getTipo();

    // Obligamos a que cada hija sepa calcular su poder
    public abstract int calcularPoder();
}