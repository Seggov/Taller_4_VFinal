package Vista;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

// Panel que muestra una imagen de carta predefinida y sirve como espacio para mostrar cartas en el futuro
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