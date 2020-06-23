package Model.Entidades.Burbujas;

import Model.Entidades.Enemigo;
import Model.Entidades.EnemigoBurbuja;
import Model.Entidades.Items.Item;

import java.util.concurrent.CopyOnWriteArrayList;

public class ElementoFuego implements Elemento {
    @Override
    public void eliminarEnemigo(CopyOnWriteArrayList<Enemigo> enemigos, CopyOnWriteArrayList<EnemigoBurbuja> enemigosBurbuja, Enemigo enemigo, CopyOnWriteArrayList<Item> items) {
        //Aca solo lo borra del arraylist
        enemigos.remove(enemigo);
        items.add(new Item(enemigo.getX(), enemigo.getY()));
    }
}
