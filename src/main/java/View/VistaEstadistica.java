package View;

import javax.swing.*;

public class VistaEstadistica extends JFrame {

        public VistaEstadistica(PanelEstadistica panelEstadistica){
            this.setIconImage(new ImageIcon(getClass().getResource("/logo.png")).getImage());
            setTitle("Estadisticas");
            //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            getContentPane().add(panelEstadistica);
            pack();
            setLocationRelativeTo(null);
            setResizable(false);
            setVisible(true);
        }
}
