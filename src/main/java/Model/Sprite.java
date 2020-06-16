package Model;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
            incY = 0;
           if(incX<columnas){
               incX++;
           }else incX = 0;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            incY = columnas;
            if(incX<columnas){
                incX++;
            }else incX = 0;
        }

    }
    public BufferedImage cargarSprite(){
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream( dir ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public int getMx(){
       return (incX%columnas)*tamanio;
    }
    public int getMy(){
        return (incY/columnas)*tamanio;
    }

    public int getTamanio(){
        return tamanio;
    }

}
