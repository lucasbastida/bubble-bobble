package Model.Entidades.Burbujas;

import Model.Entidades.Enemigo;

import java.util.concurrent.CopyOnWriteArrayList;

public class ElementoFuego implements Elemento {
    @Override
    public void eliminarEnemigo(CopyOnWriteArrayList<Enemigo> enemigos, Enemigo enemigo) {
        //Aca solo lo borra del arraylist
        enemigos.remove(enemigo);
    }
}
