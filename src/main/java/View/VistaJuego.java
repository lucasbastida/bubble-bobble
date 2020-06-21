package View;

import javax.swing.*;

public class VistaJuego extends JFrame {

    public VistaJuego(PanelJuego panelJuego) {
        this.setIconImage(new ImageIcon(getClass().getResource("/logo.png")).getImage());

        setTitle("Bubble Bobble");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panelJuego);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

}
