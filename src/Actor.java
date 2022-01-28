import java.awt.*;

public abstract class Actor{

    protected int x,y;
    protected String img;

    public Actor() {
        super();
    }

    public Actor(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public Actor(int x, int y, String img) {
        super();
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public abstract void paint(Graphics g);

    public abstract void actua();

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
