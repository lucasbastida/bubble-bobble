package View;

import javax.swing.JFrame;

public class VistaJuego extends JFrame {

    public VistaJuego(PanelJuego panelJuego) {
        setTitle("Bubble Bobble");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelJuego);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

}
