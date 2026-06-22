package Vista;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

/**
 * Panel simple que dibuja la imagen inicial de una carta.
 * Si la imagen no existe, el panel queda vacio pero la interfaz sigue funcionando.
 */
public class PanelConImagen extends JPanel {
        private static final long serialVersionUID = 1L;
        private transient Image imagenCarta;
		
        public PanelConImagen() {
            // Corrige "Preterminado" por "Predeterminado" si ese es el nombre real del archivo
            URL ruta = getClass().getResource("/CartasImagenes/ImagenData/Predeterminado.png");
            if (ruta != null) {
                this.imagenCarta = new ImageIcon(ruta).getImage();
            } else {
                System.out.println("No se encontró la imagen predeterminada en la ruta.");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imagenCarta != null) {
                g.drawImage(imagenCarta, 0, 0, 200, 280, this);
            }
        }
        
    
    
    
    
    
    
    
    
    
    
    
    

}
