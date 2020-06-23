package View;

import Model.Juego;
import util.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PanelEstadistica extends JPanel implements Observer {
    //tamanio de la ventana
    private static final int PWIDTH = 400;
    private static final int PHEIGHT = 400;

    private Juego juego;//referencia a modelo Juego

    private BufferedImage backBuffer;//buffer donde se dibujan las imagenes

    private Font font;

    public PanelEstadistica(Juego juego) {
        this.juego = juego;

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
        setFocusable(true);
        requestFocus();
        font = new Font("Arial", Font.BOLD, 40);
        backBuffer = new BufferedImage(PWIDTH, PHEIGHT, BufferedImage.TYPE_INT_RGB); //crea un buffer para pintar
    }

    public void paintScreen() {
        Graphics g = getGraphics();

        Graphics bbg = backBuffer.getGraphics();

        bbg.setColor(Color.BLACK);
        bbg.fillRect(0, 0, PWIDTH, PHEIGHT);
        bbg.setColor(Color.GREEN);
        bbg.setFont(font);

        bbg.drawString("Puntaje: " + juego.getJugador().getPuntajeAcumulado(), 115, 60);
        bbg.drawString("Enemigos: " + juego.getEnemigos().size(), 95, 130);
        bbg.drawString("Items: " + juego.getItems().size(), 125, 210);
        bbg.drawString("Vidas: ", 130, 290);


        g.drawImage(backBuffer, 0, 0, null);
    }


    @Override
    public void update() {
        paintScreen();
    }
}
