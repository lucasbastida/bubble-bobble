package Model;

import java.awt.image.BufferedImage;

public interface Burbuja {
    void animacionDisparar();
    BufferedImage getSprite();
    int getX();
    int getY();
    Sprite getSpriteSheet();

    void animacionStop();
}
