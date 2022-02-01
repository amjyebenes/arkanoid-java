import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

    public static void main(String[] args) {
        ventana = new JFrame("Space Invaders");

        ventana.addWindowListener(new WindowAdapter() {

        });
        ventana.setBounds(0, 0, 400, 600);
        ventana.getContentPane().setLayout(new BorderLayout());
        ArrayList<Actor> actores = creaActores();

        canvas = new MiCanvas(actores);
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

        Nave jugador = new Nave(200, 500,50,30);
        actores.add(jugador);

        Color colores[] = {Color.RED,Color.yellow,Color.blue,Color.green,Color.CYAN,Color.MAGENTA};
        int cont= 0;
        for(int i = 0; i < 12; i++){
            int x = (32 * i);

            for(int j = 0; j < 20; j++) {
                int y = 14 * j;
                Ladrillo m = new Ladrillo(x, y,30,10,colores[cont++]);
                if(cont > 5) cont = 0;
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
}
