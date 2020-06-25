package Model.Entidades;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Enemigo extends Sprite {

    private int direccion = -1; //esta orientado hacia la izquierda
    private double gravity;
    private int dx;
    private double dy;
    private int speed = 2;

    public Enemigo(int x, int y) {
        super(x, y, 256/4, 128/2);
    }

    public void mover(CopyOnWriteArrayList<Bloque> walls){
        dx = speed*direccion;
        dy = gravity;
        if (hasHorizonatlCollison(walls)) {
            direccion = direccion*(-1);
        }
        x += dx;

        if(!hasVerticalCollision(walls)){
            y += dy;
        }
    }

    public int getDireccion() {
        return direccion;
    }
    public void setDireccion(int direccion){//deberia recibir como parametro 1 o -1
        this.direccion = direccion;
    }

    public boolean hasVerticalCollision(CopyOnWriteArrayList<Bloque> walls) {
        for (Bloque b : walls) {
            if (getOffsetBounds().intersects(b.getTop()) && dy > 0) {
                dy = 0;
                gravity = 0;
                return true;
            } else {
                gravity = 1;
            }
        }
        return false;
    }
    public boolean hasHorizonatlCollison(CopyOnWriteArrayList<Bloque> walls) {
        for (Bloque b : walls) {
            if (getBounds().intersects(b.getLeft()) && dx > 0) {
                return true;
            }
            if (getBounds().intersects(b.getRight()) && dx < 0) {
                return true;
            }
        }
        return false;
    }
    public Rectangle getOffsetBounds() {
        return new Rectangle(x + (int) dx, y + (int) dy, alto, ancho);
    }
}
