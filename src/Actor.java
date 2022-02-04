import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Actor{

    protected int x,y;
    protected BufferedImage img;
    private int ancho;
    private int alto;
    protected BufferedImage spriteActual;
    protected boolean marcadoParaEliminacion = false;
    // Posibilidad de que el actor sea animado, a trav�s del siguiente array de sprites y las variables
    // velocidadDeCambioDeSprite y unidadDeTiempo
    protected List<BufferedImage> spritesDeAnimacion = new ArrayList<BufferedImage>();
    protected int velocidadDeCambioDeSprite = 0;
    private int unidadDeTiempo = 0;

    public Actor() {
        super();
    }

    public Actor(int x, int y) {
        this.x = x;
        this.y = y;
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

    public void paint(Graphics g) {
        g.drawImage(this.spriteActual, this.x, this.y, null);
    }

    public void actua() {
        // En el caso de que exista un array de sprites el actor actual se tratar� de una animaci�n, para eso llevaremos a cabo los siguientes pasos
        if (this.spritesDeAnimacion != null && this.spritesDeAnimacion.size() > 0) {
            unidadDeTiempo++;
            if (unidadDeTiempo % velocidadDeCambioDeSprite == 0){
                unidadDeTiempo = 0;
                int indiceSpriteActual = spritesDeAnimacion.indexOf(this.spriteActual);
                int indiceSiguienteSprite = (indiceSpriteActual + 1) % spritesDeAnimacion.size();
                this.spriteActual = spritesDeAnimacion.get(indiceSiguienteSprite);
            }
        }
    }

    public void colisionaCon(Actor a) {
    }


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

    public BufferedImage getSpriteActual() {
        return this.spriteActual;
    }

    /**
     * @param img the img to set
     */
    public void setSpriteActual(BufferedImage spriteActual) {
        this.spriteActual = spriteActual;
        this.ancho = this.spriteActual.getWidth();
        this.alto = this.spriteActual.getHeight();
    }

    /**
     * @return the spritesDeAnimacion
     */
    public java.util.List<BufferedImage> getSpritesDeAnimacion() {
        return spritesDeAnimacion;
    }

    /**
     * @param spritesDeAnimacion the spritesDeAnimacion to set
     */
    public void setSpritesDeAnimacion(List<BufferedImage> spritesDeAnimacion) {
        this.spritesDeAnimacion = spritesDeAnimacion;
    }
}
