package Controller;

import Model.Juego;
import View.PanelEstadistica;
import View.VistaEstadistica;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ControladorEstadisticas {
    private  PanelEstadistica panelEstadistica;
    private Juego juego;
    private VistaEstadistica vistaEstadistica;

    public ControladorEstadisticas(PanelEstadistica panelEstadistica,
                                   VistaEstadistica vistaEstadistica, Juego juego){

        this.panelEstadistica = panelEstadistica;
        this.juego = juego;
        this.vistaEstadistica = vistaEstadistica;

        panelEstadistica.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_E){
                    juego.removeObserver(panelEstadistica);
                    vistaEstadistica.setVisible(false);
                    vistaEstadistica.dispose();
                }
            }

            public void keyReleased(KeyEvent e) {

            }
        });
    }
}
