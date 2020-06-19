package Model.Entidades;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite {
    protected int x;
    protected int y;
    protected int alto; //alto de la hoja de sprites en px
    protected int ancho;
    protected int filas;
    protected int columnas;
    protected String dir;
    protected final int tamanio;
    protected BufferedImage img;
    public BufferedImage spriteActual;

    public Sprite(int x, int y, final int alto,final int ancho, final int filas,
                  final int columnas, String dir){
        this.x = x;
        this.y = y;
        this.alto = alto;
        this.ancho = ancho;
        this.filas = filas;
        this.columnas = columnas;
        this.dir = dir;
        tamanio = ancho/columnas;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public BufferedImage cargarSprite(){
        try {
            img = ImageIO.read(getClass().getResourceAsStream( dir ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public int getTamanio(){ return tamanio; }

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
    protected void setSprite(int x, int y){
        BufferedImage[][] array = splitImage();
        spriteActual = array[x][y];
    }
    public BufferedImage getSprite(){
        return spriteActual;
    }
    public BufferedImage getSpriteSheet(){ return img; }
}
