package Vista;

import javax.swing.*;

public class CrearColeccion extends SistemaInterfaz {

    private JPanel panelOpciones; // referencia guardada para el toggle
    private ControladorCartas controladorCartas = new ControladorCartas(); // controlador para manejar la lógica de cartas
    // Botón principal que activa/oculta las opciones
    public JButton crearBotonPrincipal() {
        JButton b = new JButton("Ver Colección");
        b.addActionListener(e -> toggleOpciones());
        return b;
    }

    // Panel con las 3 sub-opciones (empieza oculto)
    public JPanel crearPanelOpciones() {
        panelOpciones = new JPanel();
        panelOpciones.setLayout(new BoxLayout(panelOpciones, BoxLayout.LINE_AXIS));
        panelOpciones.setVisible(false);

        JButton op1 = new JButton("Ver todas");
        JButton op2 = new JButton("Buscar carta");
        JButton op3 = new JButton("Mis favoritas");

        op1.addActionListener(e -> JOptionPane.showMessageDialog(null, "Mostrando todas las cartas"));
        op2.addActionListener(e -> JOptionPane.showMessageDialog(null, "Buscando carta..."));
        op3.addActionListener(e -> JOptionPane.showMessageDialog(null, "Mostrando favoritas"));

        panelOpciones.add(op1);
        panelOpciones.add(op2);
        panelOpciones.add(op3);

        return panelOpciones;
    }

    private void toggleOpciones() {
        panelOpciones.setVisible(!panelOpciones.isVisible());
        panelOpciones.revalidate();
        panelOpciones.repaint();
    }


    private void verColeccion(){

        controladorCartas.verColeccion(); // delega la lógica de mostrar la colección al controlador
        
    }
}