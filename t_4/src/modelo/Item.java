package modelo;

public class Item extends Carta {

    private int bonificacion;

    public Item(String nombre, int rareza, int bonificacion) {
        super(nombre, rareza);
        this.bonificacion = bonificacion;
    }

    public int getBonificacion() { return bonificacion; }

    @Override
    public String getTipo() {
        return "Item";
    }

    @Override
    public int calcularPoder() {
        return this.bonificacion * 20;
    }
}