package Model.Entidades;

import Model.Sprite;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Jugador extends GameObject {

    private int dy, dx;
    public static Sprite sprite = new Sprite(128, 320, 3, 5, "/bub.png");
    public BufferedImage spriteActual;
    //esto se usa solamente para la animacion
    private static int index = 0;
    boolean mirandoDerecha = true;

    public Jugador(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void mover() {
        x += dx;
        y += dy;
    }
    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public Sprite getSpriteSheet(){
        return sprite;
    }

    public void animacionPressed(int keyCode){
        if (keyCode == KeyEvent.VK_LEFT) {
            mirandoDerecha = false;
            if(index < getSpriteSheet().getColumnas()-1){
                index++;
                setSprite(0, index);
            }else {
                index =0;
                setSprite(0,index);
            }
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            mirandoDerecha = true;
            if(index < getSpriteSheet().getColumnas()-1){
                index++;
                setSprite(1, index);
            }else {
                index =0;
                setSprite(1,index);
            }
        }
        if (keyCode == KeyEvent.VK_F){
            if(mirandoDerecha){
                setSprite(2,1);
            }else setSprite(2,0);
        }
        if (keyCode == KeyEvent.VK_UP) {
            if (mirandoDerecha) {
                setSprite(2, 3);
            } else setSprite(2, 2);
        }
    }

    public void animacionRelease(int keyCode){
        if (keyCode == KeyEvent.VK_F){
            if(mirandoDerecha){
                setSprite(1,0);
            }else setSprite(0,0);
        }
        if (keyCode == KeyEvent.VK_UP) {
            if (mirandoDerecha) {
                setSprite(1, 0);
            } else setSprite(0, 0);
        }
    }
    private void setSprite(int x, int y){
        BufferedImage[][] array = sprite.splitImage();
        spriteActual = array[x][y];
    }
    public BufferedImage getSprite(){
        return spriteActual;
    }
}
