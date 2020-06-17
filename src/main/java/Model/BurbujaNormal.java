package Model;

import Model.Entidades.GameObject;

import java.awt.image.BufferedImage;

public class BurbujaNormal extends GameObject implements Burbuja  {
    private int dx;
    private int dy;
    private final static int MAX_DES=200;

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

    public void mover(int direccion){ //direccion == -1 ->apunta a la izq
        int speed = 3;
        setX(getX() + speed);
       /* if((getX())>500){ //TODO implementar que las burbujas desaparezan despues de un tiempo
            setSprite(0,1);
        }*/
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    //Devuelve el maximo desplazamiento en pixeles que hace la burbuja antes de desaparecer
    public int getMaxDes(){
        return MAX_DES;
    }

}
