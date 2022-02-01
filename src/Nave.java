import java.awt.*;
import java.awt.image.BufferedImage;

public class Nave extends Actor{


    public Nave(int x, int y, int ancho, int alto) {
        super(x,y,ancho,alto,ImagesCache.getInstance().getImagen(ImagesCache.IMAGEN_PLAYER));
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(this.img, this.x, this.y, null);
    }

    @Override
    public void actua() {

    }
}
