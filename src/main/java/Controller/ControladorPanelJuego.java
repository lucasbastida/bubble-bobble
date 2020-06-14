package Controller;

import Model.Juego;
import View.PanelJuego;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ControladorPanelJuego {

    PanelJuego panelJuego;
    //Juego juego;

    public ControladorPanelJuego(PanelJuego panelJuego, Juego juego) {
        this.panelJuego = panelJuego;
        //this.juego = juego;

        panelJuego.addKeyListener( new KeyAdapter() {
            // listen for esc, q
            public void keyPressed(KeyEvent e)
            { int keyCode = e.getKeyCode();
                if ((keyCode == KeyEvent.VK_ESCAPE) || (keyCode == KeyEvent.VK_Q)) {
                    juego.stopGame();
                }
            }
        });

    }
}
