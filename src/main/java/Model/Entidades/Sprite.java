package Model.Entidades;

import java.awt.*;

public class Sprite {

    protected int x;
    protected int y;

    protected int alto; //para colisiones entre objetos en el modelo. No tiene nada que ver con la vista.
    public int ancho;

    public Sprite(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    public int getAlto() {
        return alto;
    }

    public int getAncho() {
        return ancho;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }

    public Rectangle getTop() {
        return new Rectangle(x + 6, y, ancho - 6*2 , 10);
    }

    public Rectangle getBottom() {
        return new Rectangle(x + 6, y + alto - 4, ancho - 6*2, 4);
    }

    public Rectangle getRight() {
        return new Rectangle(x + ancho - 6, y + 4, 6, alto - 4*2);
    }

    public Rectangle getLeft() {
        return new Rectangle(x, y + 4, 6, alto - 4*2);
    }

}
