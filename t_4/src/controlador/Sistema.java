package controlador;

public class Sistema {
	
	
	// Integramos Singelton
		private static Sistema instanciaUnica;
		
		private Sistema( ) {}
		public static Sistema getInstancia() {
			
			if (instanciaUnica == null) {
				instanciaUnica = new Sistema();
			}
			return instanciaUnica;
			
		}
	
		
	// aqui importamos EL PAQUETE DE VISTA 
	private Vista.SistemaInterfaz gui = new Vista.SistemaInterfaz();
	
	
	// inicializamos SISTEMA
	public void iniciarSistema() {
		gui.creacionInterfazGeneral();
	}
	
	
	
	

}
