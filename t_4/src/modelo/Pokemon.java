package modelo;

public class Pokemon extends Carta {
    
    private int dano;
    private int cantEnergias;

    public Pokemon(String nombre, int rareza, int dano, int cantEnergias) {
        super(nombre, rareza); // Llama al constructor de Carta
        this.dano = dano;
        this.cantEnergias = cantEnergias;
    }

    public int getDano() { return dano; }
    public int getCantEnergias() { return cantEnergias; }

    @Override
    public String getTipo() {
        return "Pokemon";
    }

    @Override
    public int calcularPoder() {
        if (cantEnergias == 0) return 0; // Previene un error matemático por división por cero
        return (this.dano / this.cantEnergias) * 100;
    }
}