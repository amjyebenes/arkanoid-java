import java.awt.*;
import java.util.ArrayList;

public class MiCanvas extends Canvas {

    ArrayList<Actor> actores;

    public MiCanvas(){
        this.actores = new ArrayList<>();
    }

    public MiCanvas(ArrayList<Actor> actores){
        this.actores = actores;
    }

    @Override
    public void paint(Graphics g) {
        this.setBackground(Color.white);

        for (Actor a : this.actores){
            a.paint(g);
        }
    }
}
