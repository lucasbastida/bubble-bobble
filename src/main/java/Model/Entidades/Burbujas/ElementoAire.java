package Model.Entidades.Burbujas;

import Model.Entidades.Enemigo;
import Model.Entidades.EnemigoBurbuja;
import Model.Entidades.Items.Item;

import java.util.concurrent.CopyOnWriteArrayList;

public class ElementoAire implements Elemento {
    @Override
    public void eliminarEnemigo(CopyOnWriteArrayList<Enemigo> enemigos,
                                CopyOnWriteArrayList<EnemigoBurbuja> enemigosBurbuja, Enemigo enemigo, CopyOnWriteArrayList<Item> items) {
        //Aca lo borra del arrayList y lo guarda en otro?

        enemigosBurbuja.add(new EnemigoBurbuja(enemigo.getX(), enemigo.getY()));
        enemigos.remove(enemigo);
    }
}
