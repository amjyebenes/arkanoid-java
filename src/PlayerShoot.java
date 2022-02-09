public class PlayerShoot extends Actor{
    private static int VELOCIDAD_PIXELS_POR_FRAME = 5;
    public static int ANCHO = 5;
    public static int ALTO = 20;

    public PlayerShoot(int x, int y) {
        super(x, y);
        this.setAncho(ANCHO);
        this.setAlto(ALTO);
        this.setSpriteActual(ResourcesCache.getInstance().getImagen(ResourcesCache.IMAGEN_DISPARO));
    }

    /**
     *
     */
    @Override
    public void actua() {
        super.actua();

        this.y -= VELOCIDAD_PIXELS_POR_FRAME; // El disparo sube en vertical

        // Si el disparo se pierde por el borde superior, elimino el actor del juego
        if ((this.y + this.getAlto()) < 0) {
            Arkanoid.getInstance().eliminaActor(this);
        }
    }


    /**
     * Este método se disparará cuando un actor colisione con el disparo
     */
    @Override
    public void colisionaCon(Actor a) {
        super.colisionaCon(a);
        // Si colisionamos con monstruo, eliminamos el disparo
        if (a instanceof Ladrillo) {
            Arkanoid.getInstance().eliminaActor(a);
        }
    }
}
