package Model.Entidades;

import java.awt.event.KeyEvent;

public class Enemigo extends Sprite {
    private int index = 0;
    private int time = 2;
    private boolean mirandoDerecha = true;

    public Enemigo(int x, int y) {
        super(x, y, 128, 256, 2, 4, "/walker.png");
        cargarSprite();
        setSprite(0,0);
    }

    public void mover(){
        int speed = 2;
        setX(getX() + speed);
    }
    public void animacion(){
    if(time > 4) {
        //mirandoDerecha = true;
        time = 0;
        if (index < getColumnas()) {
            index++;
            setSprite(1, index);
        } else {
            index = 0;
            setSprite(1, index);
        }
    }else time++;

    }


    //TODO: implementar algoritmo de movimiento
}
