package View;

import Model.Juego;
import util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PanelJuego extends JPanel implements Observer
{
    private static final int PWIDTH = 1280; // size of panel
    private static final int PHEIGHT = 720;

    // global variables for off-screen rendering
    private Graphics dbg;
    private Image dbImage = null;

    private BufferedImage img = null;
    //public int incremento = 0;

    Juego juego;

    public PanelJuego(Juego juego) {
        this.juego = juego;
        setBackground(Color.white);
        setPreferredSize( new Dimension(PWIDTH, PHEIGHT));
        setFocusable(true);
        requestFocus(); // JPanel now receives key events

        try
        {
            //TODO se deberia
            img = ImageIO.read(getClass().getResourceAsStream( "/linkformatted.png" ));
        }
        catch ( IOException exc )
        {
            //TODO: Handle exception.
        }
    }

    public void addNotify()
        /* Wait for the JPanel to be added to the
        JFrame/JApplet before starting. */
    {
        super.addNotify(); // creates the peer
        juego.startGame(); // start the thread
    }


    private void gameRender(){ // draw the current frame to an image buffer
        if (dbImage == null){ // create the buffer
            dbImage = createImage(PWIDTH, PHEIGHT);
            if (dbImage == null) {
                System.out.println("dbImage is null");
                return;
            }
            else dbg = dbImage.getGraphics( );
        }
        // clear the background
        dbg.setColor(new Color(44, 70, 121));
        dbg.fillRect (0, 0, PWIDTH, PHEIGHT);
        // draw game elements
    }

    private void paintScreen( ){ // actively render the buffer image to the screen
        Graphics g;
        try {
            g = this.getGraphics(); // get the panel's graphic context
            if ((g != null) && (dbImage != null))
                g.drawImage(dbImage, 0, 0, null);
            g.drawString("Esto es todo :)", 600,300);
            g.drawString("Con Esc o Q se cierra", 600,350);
            //g.drawImage( img , juego.getJugador().getX(),juego.getJugador().getY(), juego.getJugador().getWidth(), juego.getJugador().getHeight(), null);

           // int mx = (incremento%8)*32;
            //int my = (incremento/8)*32;

            //g.drawImage(img,200, 200, 200+32, 200+32,mx, my, mx+32, my+32,this);
            g.drawImage(img, juego.getJugador().getX(),juego.getJugador().getY(),juego.getJugador().getX()+32, juego.getJugador().getY()+32,
                    juego.getJugador().getSprite().getMx(),
                    juego.getJugador().getSprite().getMy(),
                    juego.getJugador().getSprite().getMx()+32,
                    juego.getJugador().getSprite().getMy()+32,this);

            Toolkit.getDefaultToolkit().sync(); // sync the display on some systems
            g.dispose();
        }
        catch (Exception e)
        { System.out.println("Graphics context error: " + e); }
    }

    @Override
    public void update() {
        gameRender();
        paintScreen();
    }
}