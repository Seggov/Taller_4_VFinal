package Main;

import javax.swing.SwingUtilities;

import controlador.Sistema;

/**
 * Punto de entrada del programa.
 * Main solo inicia Sistema para mantener separada la arquitectura.
 */
public class Main {

	public static void main(String[] args) {
		/*
		 * SwingUtilities.invokeLater ejecuta la GUI en el hilo correcto de Swing.
		 * Esto evita comportamientos raros donde el programa queda corriendo,
		 * pero la ventana no se dibuja correctamente.
		 */
		SwingUtilities.invokeLater(() -> {
			Sistema sistema = Sistema.getInstancia();
			System.out.println("Inicializando Menu...\n");
			sistema.iniciarSistema();
		});
	}

}
