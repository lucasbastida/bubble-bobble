package Model;

import java.awt.event.KeyEvent;

public class Sprite {
    private final int alto;
    private final int ancho;
    private final int filas;
    private final int columnas;
    private final String dir;
    private final int tamanio;
    private int incX = 0;
    private int incY = 0;


    public Sprite(int alto, int ancho, int filas, int columnas, String dir){
        this.alto = alto;
        this.ancho = ancho;
        this.filas = filas;
        this.columnas = columnas;
        this.dir = dir;
        tamanio = ancho/columnas;
    }

    public void animacion(KeyEvent e){
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            incY = 1;
           if(incX<columnas){
               incX++;
           }else incX = 0;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            incY = 0;
            if(incX<columnas){
                incX++;
            }else incX = 0;
        }

        if (keyCode == KeyEvent.VK_UP) {
            incY = 2;
            if(incX<columnas){
                incX++;
            }else incX = 0;
        }

        if (keyCode == KeyEvent.VK_DOWN) {
            incY = 3;
            if(incX<columnas){
                incX++;
            }else incX = 0;
        }
    }
    public int getMx(){
       return (incX%columnas)*tamanio;
    }
    public int getMy(){
        return (incY/columnas)*tamanio;
    }

}
