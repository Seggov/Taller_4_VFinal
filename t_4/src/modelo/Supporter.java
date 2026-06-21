package modelo;

public class Supporter extends Carta {

    private int efectosPorTurno;

    public Supporter(String nombre, int rareza, int efectosPorTurno) {
        super(nombre, rareza);
        this.efectosPorTurno = efectosPorTurno;
    }

    public int getEfectosPorTurno() { return efectosPorTurno; }

    @Override
    public String getTipo() {
        return "Supporter";
    }

    @Override
    public int calcularPoder() {
        return this.efectosPorTurno * 50;
    }
}