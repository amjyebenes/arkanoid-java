import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Pelota extends Actor{

    protected int velX = 5;
    protected int velY = 5;
    protected int ancho = 50;
    protected int alto = 50;

    public Pelota() {
        super();
    }

    public Pelota(int x, int y, int velX, int velY) {
        super(x,y);
        this.velX = velX;
        this.velY = velY;
    }

    @Override
    public void actua(){
        this.x += this.velX;

        if((this.x + this.ancho) > Arkanoid.getInstance().getCanvas().getWidth() || this.x < 0){
            this.x -= this.velX;
        }

        this.y += this.velY;

        if((this.y + this.alto) > Arkanoid.getInstance().getCanvas().getHeight() || this.y < 0){
            this.y -= this.velY;
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D  g2 = (Graphics2D) g;
        g2.setColor(Color.red);
        Ellipse2D pelota = new Ellipse2D.Double(this.x, this.y, this.ancho, this.alto);
        g2.draw(pelota);
        g2.fill(pelota);
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
}
