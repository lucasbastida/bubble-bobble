package Model;

import Model.Entidades.Sprite;

import java.awt.image.BufferedImage;

public interface Burbuja { //Esto deberia ser una clase, no una interfaz
    /*TODO: hacer esto una clase que extienda de gameObject e implementar los metodos
    que van a compartir los dos tipos de burbujas*/
    void animacionDisparar();
    BufferedImage getSprite();
    int getX();
    int getY();
    void animacionStop();
    void mover();
    public BufferedImage cargarSprite();
    public int getTamanio();
}
