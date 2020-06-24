package View;

import Model.Juego;
import util.Observer;
import util.ObserverEstadisticas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PanelEstadistica extends JPanel implements Observer, ObserverEstadisticas {
    //tamanio de la ventana
    private static final int PWIDTH = 400;
    private static final int PHEIGHT = 400;

    private Juego juego;//referencia a modelo Juego

    private BufferedImage backBuffer;//buffer donde se dibujan las imagenes

    private Font font;

    private int puntaje;
    private int cantidadDeEnemigos;
    private int cantidadDeItems;
    private int cantidadDeVidas;

    public PanelEstadistica(Juego juego) {
        this.juego = juego;

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
        setFocusable(true);
        requestFocus();
        font = new Font("Arial", Font.BOLD, 40);
        backBuffer = new BufferedImage(PWIDTH, PHEIGHT, BufferedImage.TYPE_INT_RGB); //crea un buffer para pintar
        updateEstadisticas();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        Graphics g = getGraphics();

        Graphics bbg = backBuffer.getGraphics();

        bbg.setColor(Color.BLACK);
        bbg.fillRect(0, 0, PWIDTH, PHEIGHT);
        bbg.setColor(Color.GREEN);
        bbg.setFont(font);

        bbg.drawString("Puntaje: " + puntaje, 95, 60);
        bbg.drawString("Enemigos: " + cantidadDeEnemigos, 95, 130);
        bbg.drawString("Items: " + cantidadDeItems, 95, 210);
        bbg.drawString("Vidas: " + cantidadDeVidas, 95, 290);


        g.drawImage(backBuffer, 0, 0, null);
    }


    @Override
    public void update() {
        repaint();
    }

    @Override
    public void updateEstadisticas() {
        puntaje = juego.getJugador().getPuntajeAcumulado();
        cantidadDeEnemigos = juego.getEnemigos().size() + juego.getEnemigosBurbuja().size();
        cantidadDeItems = juego.getItems().size();
        cantidadDeVidas = juego.getJugador().getVidasRestantes();
    }
}
