package Strategy;

import java.util.ArrayList;

import modelo.Carta;

/**
 * Strategy comun para ordenar la coleccion de cartas de distintas formas.
 *
 * Cada clase que implementa esta interfaz representa un criterio de orden:
 * nombre, rareza o poder. Sistema solo llama ordenar(), sin conocer el detalle.
 */
public interface OrdenamientoStrategy {

	ArrayList<Carta> ordenar(ArrayList<Carta> cartas);
}
