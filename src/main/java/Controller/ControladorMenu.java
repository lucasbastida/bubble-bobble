package Controller;

import View.VistaMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorMenu {

    VistaMenu vistaMenu;

    public ControladorMenu() {
        this.vistaMenu = new VistaMenu();

        vistaMenu.addExitListener(new ExitListener());
        vistaMenu.addNewGameListener(new NewGameListener());
    }

    class NewGameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Nuevo juego");
        }
    }

    class ExitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
