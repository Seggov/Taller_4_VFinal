package Vista;

import java.awt.*;

import javax.swing.*;


/**
 * Clase encargada de construir la ventana principal.
 * Esta clase solo arma la interfaz; la logica queda delegada en Sistema.
 */
public class SistemaInterfaz {

	private CrearColeccion crearColeccion = new CrearColeccion();
	private Administracion administracion = new Administracion();

	/**
	 * Crea la ventana principal y la deja visible en pantalla.
	 */
    public void creacionInterfazGeneral() {
        JFrame ventana = new JFrame("Pokemon Coleccion de Cartas");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(400, 500);
        ventana.setLocationRelativeTo(null);
        ventana.getContentPane().add(createGUI());
        ventana.setVisible(true);
        ventana.toFront();
        ventana.requestFocus();
    }
    
    /**
     * Arma el panel base con los botones superiores y la imagen inicial.
     */
    private JPanel createGUI() {
    	
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel botonera = new JPanel();
        botonera.setLayout(new BoxLayout(botonera, BoxLayout.LINE_AXIS));
        
        botonera.add(administracion.crearBotonPrincipal());
        botonera.add(crearColeccion.crearBotonPrincipal());

        JPanel opcionesAdmin      = administracion.crearPanelOpciones();
        JPanel opcionesColeccion  = crearColeccion.crearPanelOpciones();

        JPanel norte = new JPanel();
        norte.setLayout(new BoxLayout(norte, BoxLayout.PAGE_AXIS));
        norte.add(botonera);
        norte.add(opcionesAdmin);
        norte.add(opcionesColeccion);

        mainPanel.add(norte, BorderLayout.NORTH);
        mainPanel.add(new PanelConImagen(), BorderLayout.CENTER);
        
        return mainPanel;
    }
    
}
