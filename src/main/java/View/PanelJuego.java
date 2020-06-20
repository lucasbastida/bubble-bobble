package View;

import Model.Entidades.Burbujas.Burbuja;
import Model.Entidades.Enemigo;
import Model.Juego;
import util.Observer;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class PanelJuego extends JPanel implements Observer {

    private static final int PWIDTH = 1280; //tamanio de la ventana
    private static final int PHEIGHT = 720;

    private Juego juego;//modelo Juego

    private HashMap<String, SpriteSheet> imagenes;
    private HashMap<Enemigo, AnimatedImage> enemyImages;


    private BufferedImage backBuffer;

    public PanelJuego(Juego juego) {
        this.juego = juego;

        setBackground(Color.white);
        setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
        setFocusable(true);
        requestFocus(); // JPanel now receives key events

        backBuffer = new BufferedImage(PWIDTH, PHEIGHT, BufferedImage.TYPE_INT_RGB); //crea un buffer para pintar
        imagenes = new HashMap<String, SpriteSheet>();
        cargarImagenes();
        createEnemyImages();
    }

    //creamos un sprite sheet para cada diferente objeto y usamos las imagenes de el.
    //deja de mezclar la vista, el modelo y el controlador, el modelo no tiene nada que ver con la vista, solo notifica.
    private void cargarImagenes() {
        imagenes.put("bub", new SpriteSheet(192, 320, 3, 5, "/bub.png"));
        imagenes.put("burbuja", new SpriteSheet(64, 128, 1, 2, "/burbuja.png"));
        imagenes.put("walker", new SpriteSheet(128, 256, 2, 4, "/walker.png"));
    }


    public void paintScreen() { // actively render the buffer image to the screen
//        super.(g); //alternativa para pintar (tambien doble buffered) -> renombrar paintScreen a paintComponent(Grap
        Graphics g = getGraphics();
        Graphics bbg = backBuffer.getGraphics();

        bbg.setColor(Color.WHITE);
        bbg.fillRect(0, 0, PWIDTH, PHEIGHT);

        //todos los numeros magicos contribuye al quilombo
        dibujarJugador(bbg);
        dibujarBurbujas(bbg);
        dibujarEnemigos(bbg);

        g.drawImage(backBuffer, 0 , 0, null);
    }


    //TODO usar enum en vez de literales?
    private void dibujarJugador(Graphics g) {
        g.drawImage(imagenes.get("bub").getSpriteActual(), //usa los datos de sprite sheet,
                juego.getJugador().getX(), //obtiene las coordenadas y su altura y anchura para dibujar del modelo
                juego.getJugador().getY(),
                juego.getJugador().getAncho(),
                juego.getJugador().getAlto(), null);
    }

    private void dibujarBurbujas(Graphics g) {
        for (Burbuja burbuja : juego.getJugador().getBurbujas()) {
            g.drawImage(imagenes.get("burbuja").getSpriteActual(),//obtiene la imagen burbuja desde el objeto sprite sheet en el hashmap
                    burbuja.getX(), //obtiene las coordenadas y su altura y anchura para dibujar del modelo
                    burbuja.getY(),
                    burbuja.getAncho(),
                    burbuja.getAlto(), null);
        }
    }

    private void dibujarEnemigos(Graphics g) { //capaz es innecesario pero me gusta mas asi
        for (Enemigo enemigo : juego.getEnemigos()) {
            enemyImages.get(enemigo).animate(enemigo.getDireccion());
            g.drawImage(enemyImages.get(enemigo).getSpriteActual(),
                    enemigo.getX(),
                    enemigo.getY(),
                    enemigo.getAncho(),
                    enemigo.getAlto(), null);
        }
    }

    public void createEnemyImages(){
        juego.getEnemigos().size();
        enemyImages = new HashMap<Enemigo, AnimatedImage>();
        for (Enemigo e : juego.getEnemigos()){
            enemyImages.put(e, new AnimatedImage(imagenes.get("walker")));
        }
    }


    @Override
    public void update() {
        paintScreen();
//        repaint();//alternativa para pintar (tambien doble buffered)
    }

    public HashMap<String, SpriteSheet> getImagenes() {
        return imagenes;
    }

}