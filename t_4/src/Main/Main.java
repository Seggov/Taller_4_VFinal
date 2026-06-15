package Main;

import controlador.Sistema;

public class Main {

	public static void main(String[] args) {
		
		Sistema sistema = Sistema.getInstancia(); // asi creamos UNA SOLA INSTANCIA
		
		System.out.println("Inicializando Menu...\n"); 
		
		sistema.iniciarSistema();
	}

}
