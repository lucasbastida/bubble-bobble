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

    public PanelEstadistica(Juego juego){
        this.juego = juego;

        setBackground(Color.white);
        setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
       // setFocusable(true);
        // requestFocus(); // JPanel now receives key events

        backBuffer = new BufferedImage(PWIDTH, PHEIGHT, BufferedImage.TYPE_INT_RGB); //crea un buffer para pintar
    }

    public void paintScreen() {
//        super.(g); //alternativa para pintar (tambien doble buffered) -> renombrar paintScreen a paintComponent(Grap
        if (backBuffer == null){
            backBuffer = new BufferedImage(PWIDTH, PHEIGHT, BufferedImage.TYPE_INT_RGB);
        }
        Graphics g;
        try {
            g = this.getGraphics();
            if ((g != null) && (backBuffer != null))
                g.drawImage(backBuffer, 0, 0, null);
            g.setColor(Color.GREEN);
            g.drawString("Puntaje: " + juego.getJugador().getPuntajeAcumulado(), 20, 20);
            Toolkit.getDefaultToolkit().sync();




           // g.drawImage(backBuffer, 0, 0, null);
            g.dispose();
        }
        catch (Exception e)
        {System.out.println("Graphics context error : " + e );}
    }

    @Override
    public void update() {
        paintScreen();
    }
}
