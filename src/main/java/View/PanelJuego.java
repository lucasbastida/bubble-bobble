package View;

import Model.Entidades.Burbujas.Burbuja;
import Model.Juego;
import util.Observer;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class PanelJuego extends JPanel implements Observer {

    private static final int PWIDTH = 1280; //tamanio de la ventana
    private static final int PHEIGHT = 720;

    private Juego juego;//modelo Juego

    private HashMap<String, SpriteSheet> imagenes;

    private BufferedImage backBuffer;

    public int indexAnimacionJugador; //index usado para la animacion de la imagen

    public PanelJuego(Juego juego) {
        this.juego = juego;
        setBackground(Color.white);
        setSize(new Dimension(PWIDTH, PHEIGHT));
        setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
        setFocusable(true);
        requestFocus(); // JPanel now receives key events

        imagenes = new HashMap<String, SpriteSheet>();
        cargarImagenes();

        backBuffer = new BufferedImage(PWIDTH, PHEIGHT, BufferedImage.TYPE_INT_RGB); //crea un buffer para pintar
    }

    //creamos un sprite sheet para cada diferente objeto y usamos las imagenes de el.
    //deja de mezclar la vista, el modelo y el controlador, el modelo no tiene nada que ver con la vista, solo notifica.
    private void cargarImagenes() {
        imagenes.put("bub", new SpriteSheet(192, 320, 3, 5, "/bub.png"));
        imagenes.put("burbuja", new SpriteSheet(64, 128, 1, 2, "/burbuja.png"));
    }


    public void paintScreen() { // actively render the buffer image to the screen
//        super.(g); //alternativa para pintar (tambien doble buffered) -> renombrar paintScreen a paintComponent(Grap
        Graphics g = getGraphics();
        Graphics bbg = backBuffer.getGraphics();

        bbg.setColor(Color.WHITE);
        bbg.fillRect(0, 0, PWIDTH, PHEIGHT);

        //TODO no entiendo nada de la logica de la animacion, no carga al inicio porque la imagen esta en blanco debido a esto
        //TODO refactorear jugador y burbuja porque esta todo acoplado y no se entiende nada
        //todos los numeros magicos contribuye al quilombo
        dibujarJugador(bbg);
        dibujarBurbujas(bbg);
        dibujarEnemigos(bgg);

        g.drawImage(backBuffer, 0 , 0, null);
    }


    //TODO usar enum en vez de literales?
    private void dibujarJugador(Graphics g) {
        //TODO juego.getJugador().checkCollisions(juego.getEnemigos());
        g.drawImage(imagenes.get("bub").getSpriteActual(), //usa los datos de sprite sheet,
                juego.getJugador().getX(), //obtiene las coordenadas y su altura y anchura para dibujar del modelo
                juego.getJugador().getY(),
                juego.getJugador().getAncho(),
                juego.getJugador().getAlto(), null);
    }

    private void dibujarBurbujas(Graphics g) {
        for (Burbuja burbuja : juego.getJugador().getBurbujas()) {
            //TODO burbuja.checkCollisions(juego.getEnemigos());
            g.drawImage(imagenes.get("burbuja").getSpriteActual(),//obtiene la imagen burbuja desde el objeto sprite sheet en el hashmap
                    burbuja.getX(), //obtiene las coordenadas y su altura y anchura para dibujar del modelo
                    burbuja.getY(),
                    burbuja.getAncho(),
                    burbuja.getAlto(), null);
        }
    }

    private void dibujarEnemigos(Graphics g) { //capaz es innecesario pero me gusta mas asi
        for (Enemigo enemigo:juego.getEnemigos()) {
            enemigo.animacion();
            enemigo.mover();
            g.drawImage(enemigo.getSprite(),
                    enemigo.getX(),
                    enemigo.getY(),
                    enemigo.getTamanio(),
                    enemigo.getTamanio(), null);
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