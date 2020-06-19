package View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

    protected int alto; //alto de la hoja de sprites en px
    protected int ancho;
    protected int filas;
    protected int columnas;

    protected final int tamanio;

    private BufferedImage img; //imagen de sprite sheet entero

    private BufferedImage spriteActual; //una subimagen de imageArray
    private BufferedImage[][] imageArray; //sprite sheet dividido en subimagenes

    /**
     * @param alto     el alto de una imagen dentro del sprite sheet
     * @param ancho    el ancho de una imagen dentro del sprite sheet
     * @param filas    numero de filas del sprite sheet
     * @param columnas numero de columnas del sprite sheet
     * @param dir      ubicacion del sprite sheet nigga
     */
    public SpriteSheet(int alto, int ancho, int filas, int columnas, String dir) {
        this.alto = alto;
        this.ancho = ancho;
        this.filas = filas;
        this.columnas = columnas;
        this.tamanio = ancho / columnas;

        cargarSprite(dir);
        splitImage();
        setSpriteActual(0, 0);
    }

    private BufferedImage cargarSprite(String dir) {
        try {
            img = ImageIO.read(getClass().getResourceAsStream(dir));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    private void splitImage() {
        imageArray = new BufferedImage[filas][columnas]; //2D Array to hold each image piece
        for (int y = 0; y < filas; y++) {
            for (int x = 0; x < columnas; x++) {
                //Initialize the image array with image chunks
                imageArray[y][x] = getNewSubimage(x, y);
            }
        }
    }

    private BufferedImage getNewSubimage(int x, int y) {
        BufferedImage temp = img.getSubimage(tamanio * x, tamanio * y, tamanio, tamanio);
        BufferedImage newImage = new BufferedImage(img.getColorModel(), img.getRaster().createCompatibleWritableRaster(tamanio, tamanio), img.isAlphaPremultiplied(), null);
        temp.copyData(newImage.getRaster());
        return newImage;
    }

    public void setSpriteActual(int fila, int columna) {
        spriteActual = imageArray[fila][columna];
    }

    public BufferedImage getSpriteActual() {
        return spriteActual;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

}
