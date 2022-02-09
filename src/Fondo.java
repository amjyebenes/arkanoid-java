import javax.swing.*;
import java.awt.*;

public class Fondo extends Actor{

    public Fondo(int x, int y, int ancho, int alto, int opcion) {
        super(x,y,ancho,alto);
        if(opcion==1) {
            this.setSpriteActual(ResourcesCache.getInstance().getImagen(ResourcesCache.IMAGEN_FONDO));
        }else if(opcion ==2) {
            this.setSpriteActual(ResourcesCache.getInstance().getImagen(ResourcesCache.IMAGEN_FONDOINICIO));
        }else this.setSpriteActual(ResourcesCache.getInstance().getImagen(ResourcesCache.IMAGEN_FONDOFINAL));
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(this.spriteActual,0, 0, Arkanoid.getInstance().getCanvas().getWidth(), Arkanoid.getInstance().getCanvas().getHeight(), null);
    }

    @Override
    public void actua() {

    }
}
