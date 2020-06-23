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

    public PanelEstadistica(Juego juego){
        this.juego = juego;

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
        setFocusable(true);
        requestFocus();
        font = new Font("Arial",Font.BOLD,40);
        backBuffer = new BufferedImage(PWIDTH, PHEIGHT, BufferedImage.TYPE_INT_RGB); //crea un buffer para pintar
    }

    public void paintScreen() {
            Graphics g = getGraphics();
            Graphics bbg = backBuffer.getGraphics();

           // bbg.setColor(Color.BLACK);
            bbg.fillRect(0, 0, PWIDTH, PHEIGHT);
            g.setColor(Color.GREEN);
            g.setFont(font);

            g.drawString("Puntaje: " + juego.getJugador().getPuntajeAcumulado(), 95, 60);
            g.drawString("Enemigos: " + juego.getEnemigos().size(),95,130);
            g.drawString("Items: " + juego.getItems().size(), 95,210);
            g.drawString("Vidas: " + juego.getJugador().getVidasRestantes(),95,290);



           // g.drawImage(backBuffer, 0, 0, null);
            g.dispose();
    }


    @Override
    public void update() {
        paintScreen();
    }
}
