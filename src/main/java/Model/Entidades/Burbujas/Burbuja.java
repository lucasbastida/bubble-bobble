package Model.Entidades.Burbujas;

import Model.Entidades.Enemigo;
import Model.Entidades.Items.Item;

import java.util.concurrent.CopyOnWriteArrayList;

public interface Burbuja {

    public void mover();
    void checkCollisions(CopyOnWriteArrayList<Enemigo> enemigos, CopyOnWriteArrayList<Item> items);
}
