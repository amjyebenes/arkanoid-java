import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Actor{

    protected int x,y;
    protected BufferedImage img;
    private int ancho;
    private int alto;

    public Actor() {
        super();
    }

    public Actor(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public Actor(int x, int y, int ancho, int alto, BufferedImage img) {
        super();
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.setImg(img);
    }

    public Actor(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
        this.ancho = this.img.getWidth();
        this.alto = this.img.getHeight();
    }

    public abstract void paint(Graphics g);

    public abstract void actua();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }
}
