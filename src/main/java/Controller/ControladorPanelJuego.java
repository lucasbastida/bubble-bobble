package Controller;

import Model.Juego;
import View.PanelJuego;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ControladorPanelJuego {

    private PanelJuego panelJuego;//referencia al View panelJuego
    private Juego juego;//referencia al Modelo juego

    private int index = 0;

    public ControladorPanelJuego(PanelJuego panelJuego, Juego juego) {
        this.panelJuego = panelJuego;
        this.juego = juego;

        panelJuego.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                movementPressed(keyCode);

                if ((keyCode == KeyEvent.VK_ESCAPE) || (keyCode == KeyEvent.VK_Q)) {
                    juego.stopGame();
                }
            }

            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();

                movementReleased(keyCode);
            }
        });
    }

    private void movementPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            juego.getJugador().setDx(-2);
            juego.getJugador().mirandoDerecha = false;
            if (index < panelJuego.getSpriteSheets().get("bub").getColumnas() - 1) {
                index++;
                panelJuego.getSpriteSheets().get("bub").setSpriteActual(0, index);
            } else {
                index = 0;
                panelJuego.getSpriteSheets().get("bub").setSpriteActual(0, index);
            }
        }

        if (keyCode == KeyEvent.VK_RIGHT) {
            juego.getJugador().setDx(2);
            juego.getJugador().mirandoDerecha = true;
            if (index < panelJuego.getSpriteSheets().get("bub").getColumnas() - 1) {
                index++;
                panelJuego.getSpriteSheets().get("bub").setSpriteActual(1, index);
            } else {
                index = 0;
                panelJuego.getSpriteSheets().get("bub").setSpriteActual(1, index);
            }
        }

        if (keyCode == KeyEvent.VK_UP) {
            juego.getJugador().setDy(-2);
            if (juego.getJugador().mirandoDerecha) {
                panelJuego.getSpriteSheets().get("bub").setSpriteActual(2, 3);
            } else panelJuego.getSpriteSheets().get("bub").setSpriteActual(2, 2);
        }

        if (keyCode == KeyEvent.VK_DOWN) {
            juego.getJugador().setDy(2);
        }
        if (keyCode == KeyEvent.VK_F) {
            juego.getJugador().disparar();
            if (juego.getJugador().mirandoDerecha) {
                panelJuego.getSpriteSheets().get("bub").setSpriteActual(2, 1);
            } else panelJuego.getSpriteSheets().get("bub").setSpriteActual(2, 0);
        }
    }

    private void movementReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            juego.getJugador().setDx(0);
        }

        if (keyCode == KeyEvent.VK_RIGHT) {
            juego.getJugador().setDx(0);
        }

        if (keyCode == KeyEvent.VK_UP) {
            juego.getJugador().setDy(0);
            if (juego.getJugador().mirandoDerecha) {
                panelJuego.getSpriteSheets().get("bub").setSpriteActual(1, 0);
            } else panelJuego.getSpriteSheets().get("bub").setSpriteActual(0, 0);
        }

        if (keyCode == KeyEvent.VK_DOWN) {
            juego.getJugador().setDy(0);
        }
        if (keyCode == KeyEvent.VK_F) {
            if (juego.getJugador().mirandoDerecha) {
                panelJuego.getSpriteSheets().get("bub").setSpriteActual(1, 0);
            } else panelJuego.getSpriteSheets().get("bub").setSpriteActual(0, 0);
        }
    }

}
