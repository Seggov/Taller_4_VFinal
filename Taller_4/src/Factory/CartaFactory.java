package Factory;

import modelo.Carta;
import modelo.Energy;
import modelo.Item;
import modelo.Pokemon;
import modelo.Supporter;

/**
 * Factory encargado de crear objetos Carta desde los datos leidos del archivo.
 * Sistema no necesita saber como se construye cada tipo concreto de carta.
 *
 * Esta clase recibe una linea con formato:
 * Nombre;Rareza;Tipo;AtributosExtra
 *
 * Segun el Tipo, construye la subclase correcta:
 * Pokemon, Item, Supporter o Energy.
 */
public class CartaFactory {

	/**
	 * Convierte una linea de Sobres.txt en un objeto Carta.
	 * Si el tipo no existe, retorna null para que Sistema ignore esa linea.
	 */
	public Carta crearCartaDesdeLinea(String linea) {
		// 1. Evitar lineas nulas o en blanco
		if (linea == null || linea.trim().isEmpty()) {
			return null;
		}

		String[] datos = linea.split(";");

		// 2. Validar que la linea tenga al menos los datos base (Nombre, Rareza, Tipo)
		if (datos.length < 3) {
			System.out.println("Linea ignorada por formato incompleto: " + linea);
			return null;
		}

		try {
			String nombre = datos[0];
			int rareza = Integer.parseInt(datos[1]);
			String tipo = datos[2];

			// 3. Validar longitud de arreglo segun el tipo antes de acceder a los indices
			if (tipo.equals("Pokemon") && datos.length >= 5) {
				int dano = Integer.parseInt(datos[3]);
				int cantEnergias = Integer.parseInt(datos[4]);
				return new Pokemon(nombre, rareza, dano, cantEnergias);
			}

			if (tipo.equals("Item") && datos.length >= 4) {
				int bonificacion = Integer.parseInt(datos[3]);
				return new Item(nombre, rareza, bonificacion);
			}

			if (tipo.equals("Supporter") && datos.length >= 4) {
				int efectosPorTurno = Integer.parseInt(datos[3]);
				return new Supporter(nombre, rareza, efectosPorTurno);
			}

			if (tipo.equals("Energy") && datos.length >= 4) {
				String elemento = datos[3];
				return new Energy(nombre, rareza, elemento);
			}
		} catch (NumberFormatException e) {
			System.out.println("Error de lectura numerica en la linea: " + linea);
		}

		return null;
	}
}
