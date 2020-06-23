package Model;

import Model.Entidades.Burbujas.Burbuja;
import Model.Entidades.Burbujas.Elemento;
import Model.Entidades.Burbujas.ElementoAire;
import Model.Entidades.Burbujas.ElementoFuego;
import Model.Entidades.Enemigo;
import Model.Entidades.EnemigoBurbuja;
import Model.Entidades.Items.Item;
import org.junit.Test;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertTrue;

public class BurbujaTest {

    @Test
    public void testBurbujaMovesRight() {

        Burbuja burbuja = new Burbuja(5, 5, 1);

        int x = burbuja.getX();
        burbuja.mover();

        boolean moved = x < burbuja.getX();

        assertTrue("burbuja doesnt move", moved);
    }

    @Test
    public void testCollisionBurbujaNormalConEnemigo() {
        Enemigo enemigo = new Enemigo(10, 10);
        CopyOnWriteArrayList<Enemigo> enemigos = new CopyOnWriteArrayList<>();
        enemigos.add(enemigo);

        CopyOnWriteArrayList<Item> items = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<EnemigoBurbuja> enemigosBurbujas = new CopyOnWriteArrayList<>();

        Burbuja burbuja = new Burbuja(10, 10, 1);
        Elemento elemento = new ElementoAire();
        burbuja.setElemento(elemento);

        burbuja.checkCollisions(enemigos,items,enemigosBurbujas);

        assertTrue("no se elimino de los enemigos sin atrapar",enemigos.isEmpty());
        assertTrue("El enemigo no se atrapo dentro de la burbuja", enemigosBurbujas.size() ==1);

    }

    //test integracion burbuja y el elemento/habilidad
    @Test
    public void testCollisionBurbujaFuegoConEnemigo() {
        Enemigo enemigo = new Enemigo(10, 10);
        CopyOnWriteArrayList<Enemigo> enemigos = new CopyOnWriteArrayList<>();
        enemigos.add(enemigo);

        CopyOnWriteArrayList<Item> items = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<EnemigoBurbuja> enemigosBurbujas = new CopyOnWriteArrayList<>();

        Burbuja burbuja = new Burbuja(10, 10, 1);
        Elemento elemento = new ElementoFuego();
        burbuja.setElemento(elemento);

        burbuja.checkCollisions(enemigos,items,enemigosBurbujas);

        assertTrue("no se elimino de los enemigos sin atrapar",enemigos.isEmpty());
        assertTrue("El enemigo no fue eliminado directamente", enemigosBurbujas.isEmpty());
        assertTrue("No se dropeo un item al matar el enemigo con fuego", !items.isEmpty());
    }

}
