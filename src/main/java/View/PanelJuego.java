package View;

//TODO usar enum en vez de literales?
//todos los numeros magicos contribuye al quilombo

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

    //tamanio de la ventana
    private static final int PWIDTH = 1280;
    private static final int PHEIGHT = 720;

    private Juego juego;//referencia a modelo Juego

    private HashMap<String, SpriteSheet> spriteSheets;
    private HashMap<Enemigo, AnimatedImage> enemyImages;


    private BufferedImage backBuffer;//buffer donde se dibujan las imagenes

    public PanelJuego(Juego juego) {
        this.juego = juego;

        setBackground(Color.white);
        setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
        setFocusable(true);
        requestFocus(); // JPanel now receives key events

        backBuffer = new BufferedImage(PWIDTH, PHEIGHT, BufferedImage.TYPE_INT_RGB); //crea un buffer para pintar
        spriteSheets = new HashMap<String, SpriteSheet>();

        cargarImagenes();
        createEnemyImages();
    }

    /**
     * Coloca un key,value pair en el HashMap spriteSheets.
     * key es el nombre de la entidad
     * value es un Objeto SpriteSheet para almacenar todas las imagenes en memoria.
     */
    private void cargarImagenes() {
        spriteSheets.put("bub", new SpriteSheet(192, 320, 3, 5, "/bub.png"));
        spriteSheets.put("burbuja", new SpriteSheet(64, 128, 1, 2, "/burbuja.png"));
        spriteSheets.put("walker", new SpriteSheet(128, 256, 2, 4, "/walker.png"));
    }


    public void paintScreen() {
//        super.(g); //alternativa para pintar (tambien doble buffered) -> renombrar paintScreen a paintComponent(Grap
        Graphics g = getGraphics();
        Graphics bbg = backBuffer.getGraphics();

        bbg.setColor(Color.WHITE);
        bbg.fillRect(0, 0, PWIDTH, PHEIGHT);


        dibujarJugador(bbg);
        dibujarBurbujas(bbg);
        dibujarEnemigos(bbg);

        g.drawImage(backBuffer, 0 , 0, null);
    }


    /**
     * Dibuja al jugador segun en g segun la informacion del modelo jugador
     * @param g el buffer
     */
    private void dibujarJugador(Graphics g) {
        g.drawImage(spriteSheets.get("bub").getSpriteActual(), //usa los datos de sprite sheet,
                juego.getJugador().getX(), //obtiene las coordenadas y su altura y anchura para dibujar del modelo
                juego.getJugador().getY(),
                juego.getJugador().getAncho(),
                juego.getJugador().getAlto(), null);
    }

    /**
     * Dada todas las burbujas que disparo el jugador, dibuja una imagen en g sugun la informacion de cada modelo burbuja
     * TODO animar la burbuja igual que el enemigo.
     * @param g el buffer
     */
    private void dibujarBurbujas(Graphics g) {
        for (Burbuja burbuja : juego.getJugador().getBurbujas()) {
            g.drawImage(spriteSheets.get("burbuja").getSpriteActual(),//obtiene la imagen burbuja desde el objeto sprite sheet en el hashmap
                    burbuja.getX(), //obtiene las coordenadas y su altura y anchura para dibujar del modelo
                    burbuja.getY(),
                    burbuja.getAncho(),
                    burbuja.getAlto(), null);
        }
    }

    /**
     * hace un tick de animacion de la imagen correspondiente y luego la dibuja segun la informacion del modelo enemigo
     * @param g el buffer a donde dibujara
     */
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

    /**
     * Para cada enemigo en el modelo, se crea una imagen animada correspondiente. No crea una imagen en si de cada enemigo
     * sino que el objeto AnimatedImage tiene una referncia al SpriteSheet con las imagenes ya cargadas.
     * TODO como los mapas van a tener diferentes enemigos, que el metodo checkee si El hashmap es null o existe,
     * en caso de existir o ser null, crear un nuevo hashmap de imagenes para los nuevos enemigos.
     */
    public void createEnemyImages(){
        juego.getEnemigos().size();
        enemyImages = new HashMap<Enemigo, AnimatedImage>();
        for (Enemigo e : juego.getEnemigos()){
            enemyImages.put(e, new AnimatedImage(spriteSheets.get("walker")));
        }
    }


    @Override
    public void update() {
        paintScreen();
//        repaint();//alternativa para pintar (tambien doble buffered)
    }

    public HashMap<String, SpriteSheet> getSpriteSheets() {
        return spriteSheets;
    }

}