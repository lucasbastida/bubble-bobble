package Model;

import Model.Entidades.Bloque;
import Model.Entidades.Enemigo;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertTrue;

public class EnemyTest {
    Enemigo enemigo;
    CopyOnWriteArrayList<Bloque> walls;

    @Before
    public void setup(){
        enemigo = new Enemigo(5,5);
        walls = new CopyOnWriteArrayList<>();
    }
    @Test
    public void testEnemyMoves(){
        int x = enemigo.getX();
        int y = enemigo.getY();
        enemigo.mover(walls);

        boolean moved = x != enemigo.getX() || y != enemigo.getY();

        assertTrue("enemy doesnt move", moved);
    }
}
