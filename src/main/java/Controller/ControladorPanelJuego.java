package Controller;

import Model.Juego;
import View.PanelJuego;
import View.Images.PlayerState;
import View.VistaJuego;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ControladorPanelJuego {

    private PanelJuego panelJuego;//referencia al View panelJuego
    private Juego juego;//referencia al Modelo juego
    private VistaJuego vistaJuego;


    public ControladorPanelJuego(Juego juego) {
        this.juego = juego;
        panelJuego = new PanelJuego(this.juego, this);
        this.juego.registerObserver(panelJuego);
        vistaJuego = new VistaJuego(panelJuego);
    }

    /**
     * Controlador interactua con el modelo, le comunica que debe hacer el jugador
     * tambien comunica a la vista que tecla apreto para cargar la animacion correspondiente.
     * @param keyCode tecla
     */
    public void teclaPresionada(int keyCode) {
        if ((keyCode == KeyEvent.VK_ESCAPE) || (keyCode == KeyEvent.VK_Q)) {
            juego.stopGame();
            return;
        }

        if (keyCode == KeyEvent.VK_LEFT) {
            juego.getJugador().setDx(-2);
            juego.getJugador().setMirandoDerecha(false);
            panelJuego.getPlayerImage().setState(PlayerState.MOVING_LEFT);
            return; //si ya entró a este if no es necesario que siga haciendo todas las otras
            //comparaciones porque le van a dar falso
        }

        if (keyCode == KeyEvent.VK_RIGHT) {
            juego.getJugador().setDx(2);
            juego.getJugador().setMirandoDerecha(true);
            panelJuego.getPlayerImage().setState(PlayerState.MOVING_RIGHT);
            return;
        }

        if (keyCode == KeyEvent.VK_UP) {
            juego.getJugador().setDy(-2);
            panelJuego.getPlayerImage().setState(PlayerState.JUMPING);
            return;
        }

        if (keyCode == KeyEvent.VK_DOWN) {
            juego.getJugador().setDy(2);
            return;
        }
        if (keyCode == KeyEvent.VK_F) {
            panelJuego.getPlayerImage().setState(PlayerState.ATTACKING);
            return;
        }
        if (keyCode== KeyEvent.VK_SPACE) {
            juego.getJugador().jump(15);
            panelJuego.getPlayerImage().setState(PlayerState.JUMPING);
            return;
        }
    }

    public void movementReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT
                || keyCode == KeyEvent.VK_RIGHT) {
            juego.getJugador().setDx(0);
            panelJuego.getPlayerImage().setState(PlayerState.IDLE);
            return;
        }
        if (keyCode == KeyEvent.VK_F){
            panelJuego.getPlayerImage().setState(PlayerState.IDLE);
            juego.getJugador().disparar();
            return;
        }
        if (keyCode == KeyEvent.VK_UP
                || keyCode == KeyEvent.VK_DOWN) {
            juego.getJugador().setDy(0);
            panelJuego.getPlayerImage().setState(PlayerState.IDLE);
            return;
        }
    }

}
