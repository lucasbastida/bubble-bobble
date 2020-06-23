package Controller;

import Model.Juego;
import View.PanelJuego;
import View.Images.PlayerState;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ControladorPanelJuego {

    private PanelJuego panelJuego;//referencia al View panelJuego
    private Juego juego;//referencia al Modelo juego


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

    /**
     * Controlador interactua con el modelo, le comunica que debe hacer el jugador
     * tambien comunica a la vista que tecla apreto para cargar la animacion correspondiente.
     * @param keyCode tecla
     */
    private void movementPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            juego.getJugador().setDx(-2);
            juego.getJugador().setMirandoDerecha(false);
            panelJuego.getPlayerImage().setState(PlayerState.MOVING_LEFT);
        }

        if (keyCode == KeyEvent.VK_RIGHT) {
            juego.getJugador().setDx(2);
            juego.getJugador().setMirandoDerecha(true);
            panelJuego.getPlayerImage().setState(PlayerState.MOVING_RIGHT);
        }

        if (keyCode == KeyEvent.VK_UP) {
            juego.getJugador().setDy(-2);
            panelJuego.getPlayerImage().setState(PlayerState.JUMPING);
        }

        if (keyCode == KeyEvent.VK_DOWN) {
            juego.getJugador().setDy(2);
        }
        if (keyCode == KeyEvent.VK_F) {
            panelJuego.getPlayerImage().setState(PlayerState.ATTACKING);
        }
        if (keyCode== KeyEvent.VK_SPACE) {
            juego.getJugador().jump(15);
            panelJuego.getPlayerImage().setState(PlayerState.JUMPING);
        }
    }

    private void movementReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT
                || keyCode == KeyEvent.VK_RIGHT) {
            juego.getJugador().setDx(0);
            panelJuego.getPlayerImage().setState(PlayerState.IDLE);
        }
        if (keyCode == KeyEvent.VK_F){
            panelJuego.getPlayerImage().setState(PlayerState.IDLE);
            juego.getJugador().disparar();
        }
        if (keyCode == KeyEvent.VK_UP
                || keyCode == KeyEvent.VK_DOWN) {
            juego.getJugador().setDy(0);
            panelJuego.getPlayerImage().setState(PlayerState.IDLE);
        }
    }

}
