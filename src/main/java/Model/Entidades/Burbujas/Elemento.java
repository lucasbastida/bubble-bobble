package Model.Entidades.Burbujas;

import Model.Entidades.Enemigo;

import java.util.concurrent.CopyOnWriteArrayList;

public interface Elemento {

    void eliminarEnemigo(CopyOnWriteArrayList<Enemigo> enemigos, Enemigo enemigo);
}
