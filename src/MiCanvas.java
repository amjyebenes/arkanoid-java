import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class MiCanvas extends Canvas {

    private ArrayList<Actor> actores;
    private BufferStrategy strategy = null;

    public MiCanvas(){
        this.actores = new ArrayList<>();
    }

    public MiCanvas(ArrayList<Actor> actores){
        this.actores = actores;
    }

    @Override
    public void paint(Graphics g) {
        if (this.strategy == null) {
            this.createBufferStrategy(2);
            strategy = getBufferStrategy();
            Toolkit.getDefaultToolkit().sync();
        }
        g = strategy.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        for (Actor a : this.actores) {
            a.paint(g);
        }

        strategy.show();
    }
}
