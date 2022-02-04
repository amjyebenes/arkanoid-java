import javax.swing.*;
import java.awt.*;

public class Fondo extends Actor{

    public Fondo(int x, int y, int ancho, int alto) {
        super(x,y,ancho,alto);
        this.setSpriteActual(ResourcesCache.getInstance().getImagen(ResourcesCache.IMAGEN_FONDO));
    }

    @Override
    public void paint(Graphics g) {
        ImageIcon icon = new ImageIcon(ImagesCache.getInstance().getImagen(ImagesCache.FONDO_PANTALLA));
        g.drawImage(icon.getImage(), 0, 0, Arkanoid.getInstance().getCanvas().getWidth(), Arkanoid.getInstance().getCanvas().getHeight(), null);
    }

    @Override
    public void actua() {

    }
}
