import java.awt.*;
import java.util.ArrayList;

public class Nave extends Actor{


    public Nave(int x, int y) {
        super(x,y);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D  g2 = (Graphics2D) g;
        g2.setColor(Color.blue);
        Rectangle nave = new Rectangle(this.x,this.y,60,30);
        g2.draw(nave);
        g2.fill(nave);
    }

    @Override
    public void actua() {

    }
}
