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

    Juego juego;

    public PanelJuego(Juego juego) {
        this.juego = juego;
        setBackground(Color.white);
        setPreferredSize( new Dimension(PWIDTH, PHEIGHT));
        setFocusable(true);
        requestFocus(); // JPanel now receives key events

        img = juego.getJugador().getSpriteSheet().cargarSprite();
        juego.getJugador().getHabilidad().getSpriteSheet().cargarSprite();
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
            if ((g != null) && (dbImage != null)){
                g.drawImage(dbImage, 0, 0, null);
                dibujarJugador(g);
                dibujarBurbujas(g);
                //g.drawImage(juego.getJugador().getHabilidad().getSpriteSheet().getNewSubimage(0,0), juego.getJugador().getX()+20,
                     //   juego.getJugador().getY(),
                     //   juego.getJugador().getSpriteSheet().getTamanio(),
                     //   juego.getJugador().getSpriteSheet().getTamanio(), null);
            }

            Toolkit.getDefaultToolkit().sync(); // sync the display on some systems
            g.dispose();
        }
        catch (Exception e)
        { System.out.println("Graphics context error: " + e); }
    }

   private void dibujarJugador(Graphics g) { //capaz es innecesario pero me gusta mas asi
       g.drawImage(juego.getJugador().getSprite(),
               juego.getJugador().getX(),
               juego.getJugador().getY(),
               juego.getJugador().getSpriteSheet().getTamanio(),
               juego.getJugador().getSpriteSheet().getTamanio(), null);
   }
   private void dibujarBurbujas(Graphics g){
        g.drawImage(juego.getJugador().getHabilidad().getSprite(),
                juego.getJugador().getX()+80,
                juego.getJugador().getY(),
                juego.getJugador().getHabilidad().getSpriteSheet().getTamanio(),
                juego.getJugador().getHabilidad().getSpriteSheet().getTamanio(), null);
   }
    @Override
    public void update() {
        gameRender();
        paintScreen();
    }
}