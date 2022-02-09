import javax.imageio.ImageIO;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;

public class ResourcesCache {

	//Propiedades estáticas de esta clase
	public static String IMAGEN_NAVE = "barra3.png";
	public static String IMAGEN_DISPARO = "disparo.gif";
	public static String IMAGEN_FONDO = "fondo.png";
	public static String IMAGEN_FONDOINICIO = "fondoINICIO.png";
	public static String IMAGEN_FONDOFINAL = "fondofinal.png";
	
	// HashMap que actúa como almacén de imágenes
	private HashMap<String, Object> hmRecursos = new HashMap<String, Object>();
	// Carpetas en la que se encuentran todos los recursos
	private String nombreCarpetaParaFile = "./src/images/";
	private String nombreCarpetaParaURL = "images/";


	// Instancia Singleton
	private static ResourcesCache instance= null;
	
	
	/**
	 * Getter Singleton
	 * @return
	 */
	public static ResourcesCache getInstance () {
		if (instance == null) {
			instance = new ResourcesCache();
		}
		return instance;
	}


	/**
	 * 
	 */
	public void cargarRecursosEnMemoria () {
		File carpeta = new File(nombreCarpetaParaFile);
		for (File fichero : carpeta.listFiles()) {
	        if (fichero.isFile()) {
	        	cargarFicheroEnHashMap(fichero.getName());
	        }
	    }
	}

	
	/**
	 * 
	 * @param nombreFichero
	 */
	private void cargarFicheroEnHashMap (String nombreFichero) {
		// Obtengo un objeto URL para localizar el recurso
		URL url = null;
		url = getClass().getResource(nombreCarpetaParaURL + nombreFichero);
		// Discriminará el caso de que intento cargar un sonido del caso de cargar imágenes
		try {
			if (nombreFichero.endsWith(".wav") || nombreFichero.endsWith(".mp3")) {
				this.hmRecursos.put(nombreFichero, Applet.newAudioClip(url));
			} 
			else { // Si no es un sonido entiendo que se trata de una imagen
				this.hmRecursos.put(nombreFichero, ImageIO.read(url));
			}
		}
		catch (Exception ex) {
			System.out.println("No se pudo cargar el recurso " + nombreFichero);
			ex.printStackTrace();
		}
	}

	public BufferedImage getImagen(String nombreFichero) {
		return (BufferedImage) hmRecursos.get(nombreFichero);
	}

	public void playSonido(String nombreFichero) {
		((AudioClip)hmRecursos.get(nombreFichero)).play();
	}

	public void loopSonido(final String nombreFichero) {
		((AudioClip)hmRecursos.get(nombreFichero)).loop();
	}

	
}
