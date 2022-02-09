import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Pelota extends Actor{

    protected int velX = 5;
    protected int velY = 5;

    public Pelota(int x, int y) {
        super(x,y,10,10); // ESTO ES LO QUE DABA ERROR, TOTE
    }

    @Override
    public void actua(){
        this.x += this.velX;

        if(this.x < 0 || (this.x + this.getAncho()) > Arkanoid.getInstance().getCanvas().getWidth()){
            this.velX *= -1;
        }

        this.y += this.velY;

        if(this.y < 0 || (this.y + this.getAlto()) > Arkanoid.getInstance().getCanvas().getHeight()){
            this.velY *= -1;
        }

        if(this.x + this.velX > Arkanoid.getInstance().getCanvas().getWidth()|| this.x + this.velX < 0) {
            this.velX *= -1;
        }

        if(this.y + this.velY > Arkanoid.getInstance().getCanvas().getHeight() || this.y + this.velY < 0) {
            this.velY *= -1;
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D  g2 = (Graphics2D) g;
        g2.setColor(Color.red);
        Ellipse2D pelota = new Ellipse2D.Double(this.x, this.y, this.getAncho(), this.getAlto());
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
        if( a instanceof  Ladrillo) {
            Arkanoid.getInstance().eliminaActor(a);
            this.velY *= -1;
        }
        if( a instanceof  Nave) {
            ResourcesCache.getInstance().playSonido("rebotanave.wav");
            this.velY *= -1;
        }
    }


}
