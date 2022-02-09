import java.awt.*;

public class Ladrillo extends Actor{

    protected String img;
    private Color color;

    public Ladrillo(int x, int y, int ancho, int alto, Color color) {
        super(x,y,ancho,alto);
        this.color = color;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D  g2 = (Graphics2D) g;
        g2.setColor(this.color);
        Rectangle ladrillo = new Rectangle(this.x,this.y, this.getAncho(), this.getAlto());
        g2.fill(ladrillo);
        g2.setColor(Color.black);
        g2.draw(ladrillo);
    }

    @Override
    public void actua() {
    }

    public void colisionaCon(Actor a) {
        super.colisionaCon(a);
        ResourcesCache.getInstance().playSonido("rebotaladrillo.wav");
        Arkanoid.getInstance().incorporaNuevoActor(new Explosion(this.x, this.y));
    }
}
