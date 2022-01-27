import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Pelota extends Actor{

    protected int velX = -5;
    protected int velY = -5;

    public Pelota() {
        super();
    }

    public Pelota(int x, int y, int velX, int velY) {
        super(x,y);
        this.velX = velX;
        this.velY = velY;
    }

    public void actua(){
        this.x += this.velX;

        if(this.x > 600 || this.x < 0){
            this.x -= this.velX;
        }

        this.y += this.velY;

        if(this.y > 800 || this.y < 0){
            this.y -= this.velY;
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D  g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.draw(new Ellipse2D.Double(this.x,this.y,50,50));
        actua();
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
