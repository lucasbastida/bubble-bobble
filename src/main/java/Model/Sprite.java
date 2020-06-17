package Model;

import javax.imageio.ImageIO;
import java.awt.*;
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
    private BufferedImage img;

    public Sprite(final int alto,final int ancho, final int filas,
                  final int columnas, String dir){
        this.alto = alto;
        this.ancho = ancho;
        this.filas = filas;
        this.columnas = columnas;
        this.dir = dir;
        tamanio = ancho/columnas;
    }

    public BufferedImage cargarSprite(){
        try {
            img = ImageIO.read(getClass().getResourceAsStream( dir ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public int getTamanio(){
        return tamanio;
    }

    public int getColumnas() {return columnas;};

    public BufferedImage[][] splitImage() {
        BufferedImage[][] imageArray= new BufferedImage[filas][columnas]; //2D Array to hold each image piece
        for (int y = 0; y < filas; y++)
        {
            for (int x = 0; x < columnas; x++)
            {
                //Initialize the image array with image chunks
                imageArray[y][x] = getNewSubimage(x,y);
            }
        }
        return imageArray;
    }

    public BufferedImage getNewSubimage(int x, int y) {
        BufferedImage temp = img.getSubimage(tamanio*x,tamanio*y , tamanio, tamanio);
        BufferedImage newImage = new BufferedImage(img.getColorModel(), img.getRaster().createCompatibleWritableRaster(tamanio,tamanio), img.isAlphaPremultiplied(), null);
        temp.copyData(newImage.getRaster());
        return newImage;
    }

    public BufferedImage getBufferedImage(){
        return img;
    }
}
