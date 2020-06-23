package Model.Entidades.Burbujas;

import Model.Entidades.Enemigo;
import Model.Entidades.Items.Item;
import Model.Entidades.Sprite;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Burbuja extends Sprite{

    private final int direccion;
    private Elemento elemento;

    public Burbuja(int x, int y, int direccion) {
        super(x, y, 128/2, 64/1);
        this.direccion = direccion;
        setElemento(new ElementoAire());
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
                elemento.eliminarEnemigo(enemigos, enemigo);
                items.add(new Item(enemigo.getX(), enemigo.getY()));
            }
        }
    }

    public void setElemento(Elemento elemento){
        this.elemento = elemento;
    }
}
