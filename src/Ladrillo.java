import java.awt.*;

public class Ladrillo extends Actor{

    protected String img;
    private Color color;

    public Ladrillo(int x, int y, Color color) {
        super(x,y);
        this.color = color;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D  g2 = (Graphics2D) g;
        g2.setColor(this.color);
        Rectangle ladrillo = new Rectangle(this.x,this.y,30,10);
        g2.draw(ladrillo);
        g2.fill(ladrillo);
    }

    @Override
    public void actua() {
    }
}
