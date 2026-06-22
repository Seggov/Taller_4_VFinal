package Vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import controlador.Sistema;
import modelo.Carta;
import Strategy.OrdenamientoStrategy;
import Strategy.OrdenarPorNombre;
import Strategy.OrdenarPorRareza;
import Strategy.OrdenarPorPoder;



/**
 * Vista encargada de mostrar la coleccion de cartas.
 *
 * Aqui se aplica Strategy desde la interfaz:
 * cada boton envia una estrategia distinta al Sistema para obtener la misma
 * coleccion ordenada por nombre, rareza o poder.
 */
public class CrearColeccion {

	private JPanel panelOpciones;

	public JButton crearBotonPrincipal() {
		JButton boton = new JButton("Ver Coleccion");
		boton.addActionListener(e -> toggleOpciones());
		return boton;
	}

	public JPanel crearPanelOpciones() {
		panelOpciones = new JPanel();
		panelOpciones.setLayout(new BoxLayout(panelOpciones, BoxLayout.LINE_AXIS));
		panelOpciones.setVisible(false);

		JButton op1 = new JButton("Nombre");
		JButton op2 = new JButton("Rareza");
		JButton op3 = new JButton("Poder");

		op1.addActionListener(e -> mostrarColeccion(new OrdenarPorNombre(), "Coleccion por nombre"));
		op2.addActionListener(e -> mostrarColeccion(new OrdenarPorRareza(), "Coleccion por rareza"));
		op3.addActionListener(e -> mostrarColeccion(new OrdenarPorPoder(), "Coleccion por poder"));

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

	/**
	 * Pide al Sistema la coleccion ordenada y crea una ventana visual con cartas.
	 */
	private void mostrarColeccion(OrdenamientoStrategy estrategia, String titulo) {
		ArrayList<Carta> cartas = Sistema.getInstancia().ordenarColeccion(estrategia);

		if (cartas.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay cartas cargadas en la coleccion");
			return;
		}

		JDialog ventana = new JDialog();
		ventana.setTitle(titulo);
		ventana.setSize(850, 600);
		ventana.setLocationRelativeTo(null);
		ventana.setLayout(new BorderLayout());

		JPanel panelCartas = new JPanel(new GridLayout(0, 4, 10, 10));
		panelCartas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		for (Carta carta : cartas) {
			panelCartas.add(crearPanelCarta(carta));
		}

		ventana.add(new JScrollPane(panelCartas), BorderLayout.CENTER);
		ventana.setVisible(true);
	}

	/**
	 * Crea el panel visual de una carta: imagen, nombre, tipo y poder.
	 */
	private JPanel crearPanelCarta(Carta carta) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEtchedBorder());

		JLabel imagen = new JLabel(cargarImagenCarta(carta), SwingConstants.CENTER);
		JLabel datos = new JLabel(
				"<html><center>" + carta.getNombre() + "<br>" + carta.getTipo() + " | Poder: "
						+ carta.calcularPoder() + "</center></html>",
				SwingConstants.CENTER);

		panel.add(imagen, BorderLayout.CENTER);
		panel.add(datos, BorderLayout.SOUTH);

		panel.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				JOptionPane.showMessageDialog(null,
						carta.getNombre() + "\nTipo: " + carta.getTipo() + "\nRareza: " + carta.getRareza()
								+ "\nPoder: " + carta.calcularPoder());
			}
		});

		return panel;
	}

	/**
	 * Busca la imagen de la carta en CartasImagenes/ImagenData.
	 * Los espacios del nombre se cambian por guion bajo porque asi estan guardadas
	 * las imagenes del proyecto.
	 */
	private ImageIcon cargarImagenCarta(Carta carta) {
		String nombreArchivo = carta.getNombre().replace(" ", "_") + ".png";
		URL ruta = getClass().getResource("/CartasImagenes/ImagenData/" + nombreArchivo);

		// Si no encuentra la carta, busca la imagen de respaldo
		if (ruta == null) {
			ruta = getClass().getResource("/CartasImagenes/ImagenData/Predeterminado.png");
		}

		// Validacion final: si tampoco existe la de respaldo, retorna un icono vacio
		// para evitar que new ImageIcon(ruta) provoque un NullPointerException
		if (ruta != null) {
			return new ImageIcon(new ImageIcon(ruta).getImage().getScaledInstance(120, 168, java.awt.Image.SCALE_SMOOTH));
		} else {
			System.out.println("Error gráfico: Faltan imagenes para " + nombreArchivo);
			return new ImageIcon(); 
		}
	}
}
