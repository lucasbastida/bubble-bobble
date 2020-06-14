package Controller;

import Model.Juego;
import View.PanelJuego;
import View.VistaMenu;
import View.VistaJuego;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO - deshabilitar boton Nuevo Juego
 */
public class ControladorMenu {

    VistaMenu vistaMenu;

    public ControladorMenu() {
        this.vistaMenu = new VistaMenu();

        vistaMenu.addExitListener(new ExitListener());
        vistaMenu.addNewGameListener(new NewGameListener());
    }

    /**
     * Crea juego, panel juego, registra panel juego como observer, Crea la vista y asocia el panel del juego finalmente
     * crea el controlador del panel juego.
     */
    class NewGameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Nuevo juego");

            Juego juego = new Juego();
            PanelJuego panelJuego = new PanelJuego(juego);
            juego.registerObserver(panelJuego);
            VistaJuego vistaJuego = new VistaJuego(panelJuego);
            ControladorPanelJuego controladorPanelJuego = new ControladorPanelJuego(panelJuego, juego);
        }
    }

    class ExitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
