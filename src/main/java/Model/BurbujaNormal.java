package Model;

import Model.Entidades.GameObject;

import java.awt.image.BufferedImage;

public class BurbujaNormal extends GameObject implements Burbuja  {
    private int dx;
    private int dy;
    private final int direccion;

    public static Sprite sprite = new Sprite(64, 128, 1, 2, "/burbuja.png");
    public BufferedImage spriteActual;

    public BurbujaNormal(int x, int y, int width, int height, int direccion) {
        super(x, y, width, height);
        this.direccion = direccion;
    }

    public void animacionDisparar(){
        setSprite(0,0);
    }
    public void animacionStop(){ setSprite(0,1); } //esto no me acuerdo cuando se usa ni por que

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

    public void mover(){
        int speed = 3;
        setX(getX() + speed*direccion);
       //TODO implementar que las burbujas desaparezan despues de un tiempo

    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

}
