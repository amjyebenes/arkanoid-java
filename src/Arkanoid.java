import com.sun.glass.ui.Pixels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Date;

public class Arkanoid extends JFrame {
    private static final int FPS = 60;
    private static JFrame ventana;
    private static MiCanvas canvas;

    private static Arkanoid instance = null;

    public static Arkanoid getInstance(){
        if(instance == null){
            instance = new Arkanoid();
        }
        return instance;
    }



    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        ventana = new JFrame("Space Invaders");

        ventana.addWindowListener(new WindowAdapter() {

        });
        ventana.setBounds(0, 0, 800, 600);
        ventana.getContentPane().setLayout(new BorderLayout());
        ArrayList<Actor> actores = creaActores();

        canvas = new MiCanvas(actores);
        ventana.getContentPane().add(canvas, BorderLayout.CENTER);
        ventana.setIgnoreRepaint(true);
        ventana.setVisible(true);

        int millisPorCadaFrame = 1000 / FPS;
        do {
            long millisAntesDeProcesarEscena = new Date().getTime();

            canvas.repaint();

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
        ArrayList<Actor> actores = new ArrayList<>();

        Nave jugador = new Nave(300, 300);
        actores.add(jugador);

        Pelota pelota = new Pelota(300,400,50,50);
        actores.add(pelota);

        for (int i = 0; i < 10; i++) {
            int xAleatoria = (int)Math.round(Math.random()*490)+10;
            int yAleatoria = (int)Math.round(Math.random()*190)+10;
            Ladrillo m = new Ladrillo(xAleatoria, yAleatoria);
            actores.add(m);
        }
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
}
