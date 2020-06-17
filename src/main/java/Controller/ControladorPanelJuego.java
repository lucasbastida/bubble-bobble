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
                juego.getJugador().animacion(keyCode);

                if ((keyCode == KeyEvent.VK_ESCAPE) || (keyCode == KeyEvent.VK_Q)) {
                    juego.stopGame();
                }

                if (keyCode == KeyEvent.VK_LEFT) {
                    juego.getJugador().setDx(-2);
                }

                if (keyCode == KeyEvent.VK_RIGHT) {
                    juego.getJugador().setDx(2);
                }

                if (keyCode == KeyEvent.VK_UP) {
                    juego.getJugador().setDy(-2);
                }

                if (keyCode == KeyEvent.VK_DOWN) {
                    juego.getJugador().setDy(2);
                }
            }

            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_LEFT) {
                    juego.getJugador().setDx(0);
                }

                if (keyCode == KeyEvent.VK_RIGHT) {
                    juego.getJugador().setDx(0);
                }

                if (keyCode == KeyEvent.VK_UP) {
                    juego.getJugador().setDy(0);
                }

                if (keyCode == KeyEvent.VK_DOWN) {
                    juego.getJugador().setDy(0);
                }
            }
        });
    }
}
