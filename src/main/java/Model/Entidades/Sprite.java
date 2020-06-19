package Model.Entidades;

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
        return new Rectangle(x, y, tamanio, tamanio);
    }
}
