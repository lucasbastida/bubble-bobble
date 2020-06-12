package Model;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements Runnable
{
    private static final int PWIDTH = 1280; // size of panel
    private static final int PHEIGHT = 720;

    private Thread animator; // for the animation

    public volatile boolean running = false; // stops the animation
    private volatile boolean gameOver = false; // for game termination

    private long lastFpsTime;  //The last time at which we recorded the frame rate
    private int fps;  //The current number of frames recorded

    // global variables for off-screen rendering
    private Graphics dbg;
    private Image dbImage = null;

    public GamePanel() {
        setBackground(Color.white);
        setPreferredSize( new Dimension(PWIDTH, PHEIGHT));

        setFocusable(true);
        requestFocus(); // JPanel now receives key events
        readyForTermination();
    }
    @Override
    /* Repeatedly: update, render, sleep so loop takes close
    to period ms */
    public void run() {

        long sleepTime;
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        running = true;

        while (running)
        {
            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double)OPTIMAL_TIME);

            // update the frame counter
            lastFpsTime += updateLength;
            fps++;

            // update our FPS counter if a second has passed since we last recorded
            if (lastFpsTime >= 1000000000)
            {
                System.out.println("(FPS: "+fps+")");
                lastFpsTime = 0;
                fps = 0;
            }

            // update the game logic
            gameUpdate();
            gameRender();
            paintScreen();

            sleepTime = (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000; //time left in this loop

            if (sleepTime <= 0) // update/render took longer than period
                sleepTime = 1; // sleep a bit anyway
            try {
                Thread.sleep(sleepTime); // in ms
            }
            catch(InterruptedException e){ e.printStackTrace();}


        } System.exit(0);

    }

    public void addNotify()
        /* Wait for the JPanel to be added to the
        JFrame/JApplet before starting. */
    {
        super.addNotify(); // creates the peer
        startGame(); // start the thread
    }

    private void startGame()// initialise and start the thread
    {
        if (animator == null || !running) {
            animator = new Thread(this, "Game Thread");
            animator.start();
        }
    }

    public void stopGame() // called by the user to stop execution
    { running = false; }

    private void gameUpdate(){ //if (!gameOver)
        // update game state ...
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

    private void readyForTermination() {
        addKeyListener( new KeyAdapter() {
            // listen for esc, q
            public void keyPressed(KeyEvent e)
            { int keyCode = e.getKeyCode();
                if ((keyCode == KeyEvent.VK_ESCAPE) ||
                        (keyCode == KeyEvent.VK_Q)) {
                    stopGame();
                }
            }
        });
    }

    private void paintScreen( ){ // actively render the buffer image to the screen
        Graphics g;
        try {
            g = this.getGraphics(); // get the panel's graphic context
            if ((g != null) && (dbImage != null))
                g.drawImage(dbImage, 0, 0, null);
            g.drawString("Esto es todo :)", 600,300);
            g.drawString("Con Esc o Q se cierra", 600,350);
            Toolkit.getDefaultToolkit().sync(); // sync the display on some systems
            g.dispose();
        }
        catch (Exception e)
        { System.out.println("Graphics context error: " + e); }
    }
}