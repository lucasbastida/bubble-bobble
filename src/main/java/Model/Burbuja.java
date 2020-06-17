package Model;

import java.awt.image.BufferedImage;

public interface Burbuja { //Esto deberia ser una clase, no una interfaz
    /*TODO: hacer esto una clase que extienda de gameObject e implementar los metodos
    que van a compartir los dos tipos de burbujas*/
    void animacionDisparar();
    BufferedImage getSprite();
    int getX();
    int getY();
    Sprite getSpriteSheet();
    void animacionStop();
    void mover();
}
