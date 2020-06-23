package Model.Entidades.Burbujas;

import Model.Entidades.Enemigo;
import Model.Entidades.EnemigoBurbuja;
import Model.Entidades.Items.Item;

import java.util.concurrent.CopyOnWriteArrayList;

public interface Elemento {

    void eliminarEnemigo(CopyOnWriteArrayList<Enemigo> enemigos,
                         CopyOnWriteArrayList<EnemigoBurbuja> enemigosBurbuja, Enemigo enemigo, CopyOnWriteArrayList<Item> items);
}
