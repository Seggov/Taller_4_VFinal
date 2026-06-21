package Vista;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;


// solo REDIRIQUE Y CREA
public class SistemaInterfaz {

		
	
	protected CrearColeccion crearColeccion = new CrearColeccion();
    protected Administracion administracion = new Administracion();
    
	protected ArrayList<String> cartasTotales = new ArrayList<>();

	
	// aqui estamos CONSTRUYENDO LA VENTANA BASE (en blanco)
    public void creacionInterfazGeneral() {
        JFrame ventana = new JFrame("Pokemon Coleccion de Cartas"); //titulodelaventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // este es como su close.
        ventana.setSize(400, 500); // (archo,alto)
        
        ventana.getContentPane().add(createGUI()); // aqui llamamos al METODO PARA AGREGAR ELEMENTOS A LA VENTANA
   
        ventana.setVisible(true); // Mostrar la ventana
    }
    
    
    
    // aqui le estamos MOSTRANDO Y AGREGANDO LOS BOTONES AL PANEL Y IMAGEN
    private JPanel createGUI() {
    	
        JPanel mainPanel = new JPanel(new BorderLayout()); // aqui se HACE UNA DIVISION EN 5 
        
        // NORTH
        // CENTER
        // WEST
        // SOUTH
        // EAST
        
        
        
        // # Botones principales 
        JPanel botonera = new JPanel();
        botonera.setLayout(new BoxLayout(botonera, BoxLayout.LINE_AXIS));
        
        // Le decimos que tienen SUBPANELES PERO ESTAN OCULTOS (despues creamos una condicional para que se vean)
        botonera.add(administracion.crearBotonPrincipal());
        botonera.add(crearColeccion.crearBotonPrincipal());

        // # Sub-opciones de cada boton
        JPanel opcionesAdmin      = administracion.crearPanelOpciones();
        JPanel opcionesColeccion  = crearColeccion.crearPanelOpciones();

        // # Panel norte agrupa todo lo de arriba
        JPanel norte = new JPanel();
        
        // el BoxLayout.PAGE_AXIS significa que los pondra uno encima de otro (VERTICAL)
        norte.setLayout(new BoxLayout(norte, BoxLayout.PAGE_AXIS));
        norte.add(botonera);
        norte.add(opcionesAdmin);
        norte.add(opcionesColeccion);
     // 4. Pegamos la columna gigante en la parte "Norte" (Arriba) de la pantalla
        mainPanel.add(norte, BorderLayout.NORTH);
        
     // 5. Pegamos tu imagen en el "Centro" de la pantalla
        mainPanel.add(new PanelConImagen(), BorderLayout.CENTER);
        
        return mainPanel;
    }
    
}