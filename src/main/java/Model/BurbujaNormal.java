package Model;

import Model.Entidades.GameObject;

import java.awt.image.BufferedImage;

public class BurbujaNormal extends GameObject implements Burbuja  {

    public static Sprite sprite = new Sprite(64, 128, 1, 2, "/burbuja.png");
    public BufferedImage spriteActual;

    public BurbujaNormal(int x, int y, int width, int height) {
        super(x, y, width, height);

    }

    public void animacionDisparar(){
        setSprite(0,0);
    }
    public void animacionStop(){
        setSprite(0,1);
    }

    private void setSprite(int x, int y){
        BufferedImage[][] array = sprite.splitImage();
        spriteActual = array[x][y];
    }
    public BufferedImage getSprite(){
        return spriteActual;
    }
    public Sprite getSpriteSheet(){
        return sprite;
    }
}
