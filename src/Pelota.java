import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Pelota extends Actor{

    protected int velX = 5;
    protected int velY = 5;
    protected int ancho = 10;
    protected int alto = 10;

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

        if(this.x < 0 || (this.x + this.ancho) > Arkanoid.getInstance().getCanvas().getWidth()){
            this.velX *= -1;
        }

        this.y += this.velY;

        if(this.y < 0 || (this.y + this.alto) > Arkanoid.getInstance().getCanvas().getHeight()){
            this.velY *= -1;
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

    public void colisionaCon(Actor a) {
        System.out.println("Pelota colisiona con " + a);
        if( a instanceof  Ladrillo) {
            Arkanoid.getInstance().eliminaActor(a);
          //  this.velX *= -1;
         //   this.velY *= -1;
        }
    }


}
