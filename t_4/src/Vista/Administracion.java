package Vista;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Administracion {

	private JPanel panelOpciones;

	public JButton crearBotonPrincipal() {
		JButton boton = new JButton("Administracion");
		boton.addActionListener(e -> toggleOpciones());
		return boton;
	}

	public JPanel crearPanelOpciones() {
		panelOpciones = new JPanel();
		panelOpciones.setLayout(new BoxLayout(panelOpciones, BoxLayout.LINE_AXIS));
		panelOpciones.setVisible(false);

		JButton op1 = new JButton("Agregar carta");
		JButton op2 = new JButton("Eliminar carta");
		JButton op3 = new JButton("Editar carta");

		op1.addActionListener(e -> JOptionPane.showMessageDialog(null, "Agregar carta"));
		op2.addActionListener(e -> JOptionPane.showMessageDialog(null, "Eliminar carta"));
		op3.addActionListener(e -> JOptionPane.showMessageDialog(null, "Editar carta"));

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
}
