package Vista;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class PanelConImagen extends JPanel {
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
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imagenCarta != null) {
                g.drawImage(imagenCarta, 0, 0, 200, 280, this);
            }
        }
        
    
    
    
    
    
    
    
    
    
    
    
    

}