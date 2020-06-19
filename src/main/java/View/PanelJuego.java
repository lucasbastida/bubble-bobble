package View;

import Model.Burbuja;
import Model.Juego;
import util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PanelJuego extends JPanel implements Observer {

    private static final int PWIDTH = 1280;
    private static final int PHEIGHT = 720;

    private BufferedImage backBuffer;

    private Juego juego;

    public PanelJuego(Juego juego) {
        this.juego = juego;
        setBackground(Color.white);
        setSize(new Dimension(PWIDTH, PHEIGHT));
        setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
        setFocusable(true);
        requestFocus(); // JPanel now receives key events

        backBuffer = new BufferedImage(PWIDTH, PHEIGHT, BufferedImage.TYPE_INT_RGB); //crea un buffer para pintar
    }



    private void paintScreen() { // actively render the buffer image to the screen
        Graphics g = getGraphics();

        Graphics bbg = backBuffer.getGraphics();

        bbg.clearRect(0,0,PWIDTH,PHEIGHT);
        bbg.setColor(Color.BLUE);
        bbg.fillRect(0, 0, PWIDTH, PHEIGHT);

        //TODO no entiendo nada de la logica de la animacion, no carga al inicio porque la imagen esta en blanco debido a esto
        //TODO refactorear jugador y burbuja porque esta todo acoplado y no se entiende nada
        //todos los numeros magicos contribuye al quilombo
        dibujarJugador(bbg);
        dibujarBurbujas(bbg);

        g.drawImage(backBuffer, 0 , 0, null);
    }


    private void dibujarJugador(Graphics g) {
        g.drawImage(juego.getJugador().getSprite(),
                juego.getJugador().getX(),
                juego.getJugador().getY(),
                juego.getJugador().getTamanio(),
                juego.getJugador().getTamanio(), null);
    }

    private void dibujarBurbujas(Graphics g) {
        for (Burbuja burbuja : juego.getJugador().getBurbujas()) {
            g.drawImage(burbuja.getSprite(),
                    burbuja.getX(),
                    burbuja.getY(),
                    juego.getJugador().getHabilidad().getTamanio(),
                    juego.getJugador().getHabilidad().getTamanio(), null);
        }
    }

    @Override
    public void update() {
        paintScreen();
    }
}