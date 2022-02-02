import java.awt.*;
import java.awt.event.KeyEvent;

public class Nave extends Actor{
    private boolean izquierda = false, derecha = false;
    // Velocidad de la nave, expresada en píxeles por cada frame
    public static int VELOCIDAD = 5;


    public Nave(int x, int y, int ancho, int alto) {
        super(x,y,ancho,alto,ImagesCache.getInstance().getImagen(ImagesCache.IMAGEN_PLAYER));
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(this.img, this.x, this.y, null);
    }

    @Override
    public void actua() {
        // Compruebo las variables booleanas que determinan el movimiento
        if (izquierda) this.x -= VELOCIDAD;
        if (derecha) this.x += VELOCIDAD;

        // Compruebo si el player sale del canvas por cualquiera de los cuatro márgenes
        mover(this.x, this.y);
    }

    public void mover(int x, int y) {
        this.x = x;
        this.y = y;
        // Controlo los casos en los que el jugador pueda salir del Canvas
        MiCanvas canvas = Arkanoid.getInstance().getCanvas(); // Referencia al objeto Canvas usado

        // Compruebo si el jugador sale por la derecha
        if (this.x > (canvas.getWidth() - this.getAncho())) {
            this.x = canvas.getWidth() - this.getAlto();
        }

        // Compruebo si el jugador sale por la izquierda
        if (this.x < 0) {
            this.x = 0;
        }
    }

    public void keyPressed (KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                izquierda = true; break;
            case KeyEvent.VK_RIGHT:
                derecha = true; break;
        }
    }

    /**
     * Se ejecuta al recibir un evento del teclado: tecla liberada
     * @param e
     */
    public void keyReleased (KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                izquierda = false; break;
            case KeyEvent.VK_RIGHT:
                derecha = false; break;
        }
    }
}
