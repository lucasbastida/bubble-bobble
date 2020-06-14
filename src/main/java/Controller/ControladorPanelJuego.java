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

        panelJuego.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if ((keyCode == KeyEvent.VK_ESCAPE) || (keyCode == KeyEvent.VK_Q)) {
                    juego.stopGame();
                }

                if (keyCode == KeyEvent.VK_LEFT) {
                    juego.moverJugadorHorizontal(-2);
                }

                if (keyCode == KeyEvent.VK_RIGHT) {
                    juego.moverJugadorHorizontal(2);
                }

                if (keyCode == KeyEvent.VK_UP) {
                    juego.moverJugadorVertical(-2);
                }

                if (keyCode == KeyEvent.VK_DOWN) {
                    juego.moverJugadorVertical(2);
                }
            }

            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_LEFT) {
                    juego.moverJugadorHorizontal(0);
                }

                if (keyCode == KeyEvent.VK_RIGHT) {
                    juego.moverJugadorHorizontal(0);
                }

                if (keyCode == KeyEvent.VK_UP) {
                    juego.moverJugadorVertical(0);
                }

                if (keyCode == KeyEvent.VK_DOWN) {
                    juego.moverJugadorVertical(0);
                }
            }
        });
    }
}
