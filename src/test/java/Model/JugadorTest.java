package Model;

import Model.Entidades.Bloque;
import Model.Entidades.EnemigoBurbuja;
import Model.Entidades.Items.Item;
import Model.Entidades.Jugador;

import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertTrue;

public class JugadorTest {

    @Test
    public void testJugadorSeMueveDebidoAlaGravedad(){

        Jugador jugador = new Jugador(0,0);

        CopyOnWriteArrayList<Bloque> walls = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Item> items = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<EnemigoBurbuja> enemigoBurbujas = new CopyOnWriteArrayList<>();



        for (int i = 0; i < 10; i++) {
            jugador.mover(walls,items,enemigoBurbujas);
        }

        assertTrue("jugador no se movio debido a la gravedad", jugador.getY() > 0);
    }

    @Test
    public void testCollisionJugadorNoAtraviesaPared(){

        Jugador jugador = new Jugador(0,0);

        CopyOnWriteArrayList<Bloque> walls = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Item> items = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<EnemigoBurbuja> enemigoBurbujas = new CopyOnWriteArrayList<>();

        Bloque pared = new Bloque(64,0);
        Bloque piso = new Bloque(0,65);

        walls.add(pared);
        walls.add(piso);

        for (int i = 0; i < 100; i++) {
            jugador.setDx(2);
            jugador.mover(walls,items,enemigoBurbujas);
        }

        assertTrue("el jugador atraveso la pared derecha", jugador.getX() <= 10);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testPuntajeCantBeNegative(){
        Jugador jugador = new Jugador(0,0);

        jugador.sumarPuntaje(-1000);
    }


    @Test
    public void testCollisionJugadorNoAtravisaPlataformaAlCaer(){
        Jugador jugador = new Jugador(0,0);

        CopyOnWriteArrayList<Bloque> walls = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Item> items = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<EnemigoBurbuja> enemigoBurbujas = new CopyOnWriteArrayList<>();

        Bloque pared = new Bloque(64,0);
        Bloque piso = new Bloque(0,100);//mas abajo del jugador, actuara gravedad
        //graveda = 0.5

        walls.add(pared);
        walls.add(piso);

        for (int i = 0; i < 1000; i++) {
            jugador.mover(walls,items,enemigoBurbujas);
        }

        assertTrue("el jugador atraveso la plataforma al cae por gravedad", jugador.getY() < 150);
    }




}
