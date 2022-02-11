import java.awt.*;
import java.awt.event.KeyEvent;

public class Nave extends Actor{
    private boolean izquierda = false, derecha = false;
    // Velocidad de la nave, expresada en píxeles por cada frame
    public static int VELOCIDAD = 5;


    public Nave(int x, int y, int ancho, int alto) {
        super(x,y,ancho,alto);
        this.setSpriteActual(ResourcesCache.getInstance().getImagen(ResourcesCache.IMAGEN_NAVE));
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(this.spriteActual, this.x, this.y, null);
    }

    @Override
    public void actua() {
        // Compruebo las variables booleanas que determinan el movimiento
        if (izquierda) this.x -= VELOCIDAD;
        if (derecha) this.x += VELOCIDAD;

        // Compruebo si el player sale del canvas por cualquiera de los cuatro márgenes
        mover(this.x);
    }

    public void mover(int x) {
        this.x = x;
        // Controlo los casos en los que el jugador pueda salir del Canvas
        MiCanvas canvas = Arkanoid.getInstance().getCanvas(); // Referencia al objeto Canvas usado

        // Compruebo si el jugador sale por la derecha
        if ((this.x + this.getAncho()) > canvas.getWidth()) {
            this.x = canvas.getWidth() - this.getAncho();
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
            case KeyEvent.VK_SPACE:
                disparo(); break;
        
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
    private void disparo () {
        int xDisparo = this.x + this.getAncho()/2 - PlayerShoot.ANCHO/2;
        PlayerShoot disparo = new PlayerShoot(xDisparo, this.y); // Creo el nuevo actor
        // Incorporo el nuevo actor al juego
        Arkanoid.getInstance().incorporaNuevoActor(disparo);

        ResourcesCache.getInstance().playSonido("missile.wav");
    }
}
