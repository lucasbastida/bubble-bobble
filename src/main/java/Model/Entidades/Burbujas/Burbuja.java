package Model.Entidades.Burbujas;

import Model.Entidades.Enemigo;
import Model.Entidades.Items.Item;
import Model.Entidades.Sprite;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Burbuja extends Sprite { //Esto deberia ser una clase, no una interfaz

    private final int direccion;

    public Burbuja(int x, int y, int direccion) {
        super(x, y, 128/2, 64/1);
        this.direccion = direccion;
    }

    //TODO implementar que las burbujas desaparezan despues de un tiempo
    public void mover() {
        int speed = 3;
        x = x + speed * direccion;

    }

    public void checkCollisions(CopyOnWriteArrayList<Enemigo> enemigos,CopyOnWriteArrayList<Item> items ) {

        Rectangle r1 = this.getBounds();
        for (Enemigo enemigo : enemigos) {

            Rectangle r2 = enemigo.getBounds();

            if (r1.intersects(r2)) {
                atraparEnemigo(enemigo);
                enemigos.remove(enemigo);
                items.add(new Item(enemigo.getX(), enemigo.getY()));
            }
        }
    }

    private void atraparEnemigo(Enemigo enemigo) {
    }
}
