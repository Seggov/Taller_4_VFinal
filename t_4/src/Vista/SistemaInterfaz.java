package Vista;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SistemaInterfaz {
	
	public void creacionInterfazGeneral() {
		JFrame ventana = new JFrame("Mi Ventana 2");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(400, 400);
		
		ventana.getContentPane().add(createGUI());
		
		ventana.setVisible(true); 
	}
	
	private JPanel createGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JPanel botonera = new JPanel();
		botonera.setLayout(new BoxLayout(botonera, BoxLayout.LINE_AXIS));
		
		JPanel panelVacio = new PanelConImagen();
		
		JButton button1 = new JButton("Administración");
		JButton button2 = crearColeccion(panelVacio);
		
		botonera.add(button1);
		botonera.add(button2);		
		
		mainPanel.add(botonera, BorderLayout.NORTH);
		mainPanel.add(panelVacio, BorderLayout.CENTER);
		
		return mainPanel;
	}

	private JButton crearColeccion(JPanel panelVacio) {
		JButton b = new JButton("Ver Colección");
		b.addActionListener( e -> {
			JOptionPane.showMessageDialog(null, "Has presionado Ver Colección");
		});
		return b;
	}


	private class PanelConImagen extends JPanel {
		
		private Image imagenCarta;	
		
		public PanelConImagen() {
			URL ruta = getClass().getResource("/CartasImagenes/ImagenData/Preterminado.png");
			
			if (ruta != null) {
				this.imagenCarta = new ImageIcon(ruta).getImage();
			} else {
				System.out.println("No se encontró la imagen en la ruta.");
			}
		}
		
		@Override
		public void paint(Graphics g) {
			super.paint(g); 
			
			
			if (imagenCarta != null) {
				g.drawImage(imagenCarta, 0, 0, 200, 280, this);
			}
		}
	}
}