package Model.Entidades;

import Model.Sprite;

public class Jugador extends GameObject {

    private int dy, dx;
    public static Sprite sprite = new Sprite(128, 320, 2, 5, "/bub");

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

    public Sprite getSprite(){
        return sprite;
    }
}
