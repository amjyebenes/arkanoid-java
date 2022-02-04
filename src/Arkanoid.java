import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Arkanoid extends JFrame {
    private static final int FPS = 60;
    private static JFrame ventana;
    private static MiCanvas canvas;
    private static Nave nave = null;
    private static Arkanoid instance = null;
    private static ArrayList<Actor> actores;
    private java.util.List<Actor> actoresParaIncorporar = new ArrayList<Actor>();
    private List<Actor> actoresParaEliminar = new ArrayList<Actor>();

    public static Arkanoid getInstance(){
        if(instance == null){
            instance = new Arkanoid();
        }
        return instance;
    }

    public static void main(String[] args) {
        ventana = new JFrame("Arkanoid");
        ventana.setBounds(500, 100, 415, 600);
        ventana.getContentPane().setLayout(new BorderLayout());
        actores = creaActores();
        canvas = new MiCanvas(actores);

        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                nave.mover(e.getX());
            }
        });
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                nave.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                nave.keyReleased(e);
            }
        });
        ventana.getContentPane().add(canvas, BorderLayout.CENTER);
        ventana.setIgnoreRepaint(true);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ventana.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                cerrarAplicacion();
            }
        });

        int millisPorCadaFrame = 1000 / FPS;
        do {
            long millisAntesDeProcesarEscena = new Date().getTime();

            canvas.pintaEscena();

            for (Actor a : actores) {
                a.actua();
            }

            long millisDespuesDeProcesarEscena = new Date().getTime();
            int millisDeProcesamientoDeEscena = (int) (millisDespuesDeProcesarEscena - millisAntesDeProcesarEscena);
            int millisPausa = millisPorCadaFrame - millisDeProcesamientoDeEscena;
            millisPausa = Math.max(millisPausa, 0);
            try {
                Thread.sleep(millisPausa);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);

    }

    private static ArrayList<Actor> creaActores () {
        actores = new ArrayList<>();

//        Fondo fondo = new Fondo(0,0,400,600);
//        actores.add(fondo);

        nave = new Nave(150, 500,50,30);
        actores.add(nave);

        Color colores[] = {Color.RED,Color.yellow,Color.white,Color.green,Color.CYAN,Color.MAGENTA, Color.green};
        for(int i = 0; i < 10; i++){
            int x = (35 * i)+25;

            for(int j = 0; j < 6; j++) {
                int y = (25 * j) + 20;
                Ladrillo m = new Ladrillo(x, y,30,10,colores[j]);
                actores.add(m);
            }
        }
        Pelota pelota = new Pelota(300,400,-3,-3);
        actores.add(pelota);
        return actores;
    }

    private static void cerrarAplicacion(){
        String[] opciones = {"Aceptar","Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(ventana,"Desea cerrar la ventana?",
                "Salir",JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
        if (eleccion == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

    public MiCanvas getCanvas() {
        return canvas;
    }
    public void incorporaNuevoActor (Actor a) {
        this.actoresParaIncorporar.add(a);
    }

    /**
     * Método llamado para eliminar actores del juego
     * @param a
     */
    public void eliminaActor (Actor a) {
        this.actoresParaEliminar.add(a);
    }

    /**
     * Incorpora los actores nuevos al juego y elimina los que corresponden
     */
    private void actualizaActores () {
        // Incorporo los nuevos actores
        this.actores.addAll(actoresParaIncorporar);
        this.actoresParaIncorporar.clear(); // Limpio la lista de actores a incorporar, ya están incorporados

        // Elimino los actores que se deben eliminar
        this.actores.removeAll(actoresParaEliminar);
        this.actoresParaEliminar.clear(); // Limpio la lista de actores a eliminar, ya los he eliminado
    }


    /**
     * Detecta colisiones entre actores e informa a los dos
     */
    private void detectaColisiones() {
        // Una vez que cada actor ha actuado, intento detectar colisiones entre los actores y notificarlas. Para detectar
        // estas colisiones, no nos queda más remedio que intentar detectar la colisión de cualquier actor con cualquier otro
        // sólo con la excepción de no comparar un actor consigo mismo.
        // La detección de colisiones se va a baser en formar un rectángulo con las medidas que ocupa cada actor en pantalla,
        // De esa manera, las colisiones se traducirán en intersecciones entre rectángulos.
        for (Actor actor1 : actores) {
            // Creo un rectángulo para este actor.
            Rectangle rect1 = new Rectangle(actor1.getX(), actor1.getY(), actor1.getAncho(), actor1.getAlto());
            // Compruebo un actor con cualquier otro actor
            for (Actor actor2 : actores) {
                // Evito comparar un actor consigo mismo, ya que eso siempre provocaría una colisión y no tiene sentido
                if (!actor1.equals(actor2)) {
                    // Formo el rectángulo del actor 2
                    Rectangle rect2 = new Rectangle(actor2.getX(), actor2.getY(), actor2.getAncho(), actor2.getAlto());
                    // Si los dos rectángulos tienen alguna intersección, notifico una colisión en los dos actores
                    if (rect1.intersects(rect2)) {
                        actor1.colisionaCon(actor2); // El actor 1 colisiona con el actor 2
                        actor2.colisionaCon(actor1); // El actor 2 colisiona con el actor 1
                    }
                }
            }
        }
    }
}
