package Model.Entidades;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class EnemigoBurbuja extends Sprite{
    private int speed;

    public EnemigoBurbuja(int x, int y) {
        super(x, y, 64, 64);
        speed = 2;
    }
    //TODO: Implementar movimento y animacion del enemigo en la burbuja y la colision con las paredes
    //No debe estar afectado por la gravedad

    public void mover(){
        y = y-speed;
    }

    public void checkCollisionsWalls(CopyOnWriteArrayList<Bloque> walls) {

        Rectangle r1 = this.getBounds();

        for (Bloque b : walls) {

            Rectangle r2 = b.getBounds();

            if (r1.intersects(r2)) {
                speed = 0;
            }
        }
    }
}
