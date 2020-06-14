package Model.Entidades;

public class Jugador extends GameObject {

    private int dy, dx;

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
}
