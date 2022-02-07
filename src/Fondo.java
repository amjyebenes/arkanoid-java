import javax.swing.*;
import java.awt.*;

public class Fondo extends Actor{

    public Fondo(int x, int y, int ancho, int alto) {
        super(x,y,ancho,alto);
        this.setSpriteActual(ResourcesCache.getInstance().getImagen(ResourcesCache.IMAGEN_FONDO));
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(this.spriteActual,0, 0, Arkanoid.getInstance().getCanvas().getWidth(), Arkanoid.getInstance().getCanvas().getHeight(), null);
    }

    @Override
    public void actua() {

    }
}
