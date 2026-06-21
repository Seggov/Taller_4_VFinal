package modelo;

public class Energy extends Carta {

    private String elemento;

    public Energy(String nombre, int rareza, String elemento) {
        super(nombre, rareza);
        this.elemento = elemento;
    }

    public String getElemento() { return elemento; }
    
    
    // aqui estamos UTILIZANDO EL ABRSTRAC, 
    @Override
    public String getTipo() {
        return "Energy";
    }

    @Override
    public int calcularPoder() {
        return 1; // Por defecto según la pauta
    }
}