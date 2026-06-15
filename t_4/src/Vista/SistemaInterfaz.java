package Vista;

import java.awt.*;
import java.net.URL;
import javax.swing.*;


// solo REDIRIQUE Y CREA
public class SistemaInterfaz {

    // COMPOSICIÓN: usa las clases, no las extiende
    protected CrearColeccion crearColeccion = new CrearColeccion();
    protected Administracion administracion = new Administracion();
	protected ArrayList<String> cartasTotales = new ArrayList<>();


    public void creacionInterfazGeneral() {
        JFrame ventana = new JFrame("Pokemon Coleccion de Cartas");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(400, 400);
        ventana.getContentPane().add(createGUI());
        ventana.setVisible(true); // Mostrar la ventana
    }

    private JPanel createGUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // # Botones principales 
        JPanel botonera = new JPanel();
        botonera.setLayout(new BoxLayout(botonera, BoxLayout.LINE_AXIS));
        botonera.add(administracion.crearBotonPrincipal());
        botonera.add(crearColeccion.crearBotonPrincipal());

        // # Sub-opciones de cada boton
        JPanel opcionesAdmin      = administracion.crearPanelOpciones();
        JPanel opcionesColeccion  = crearColeccion.crearPanelOpciones();

        // # Panel norte agrupa todo lo de arriba
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