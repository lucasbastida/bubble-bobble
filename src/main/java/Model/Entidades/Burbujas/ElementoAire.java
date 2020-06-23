package Model.Entidades.Burbujas;

import Model.Entidades.Enemigo;

import java.util.concurrent.CopyOnWriteArrayList;

public class ElementoAire implements Elemento {
    @Override
    public void eliminarEnemigo(CopyOnWriteArrayList<Enemigo> enemigos, Enemigo enemigo) {
        //Aca lo borra del arrayList y lo guarda en otro?
        enemigos.remove(enemigo);
        System.out.println("El enemigo esta en una burbuja");
        //TODO implementar que el enemigo este atrapado en una burbuja
    }
}
