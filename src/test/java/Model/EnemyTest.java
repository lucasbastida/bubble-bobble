package Model;

import Model.Entidades.Enemigo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EnemyTest {
    Enemigo enemigo;

    @Before
    public void setup(){
        enemigo = new Enemigo(5,5);
    }
    @Test
    public void testEnemyMoves(){
        int x = enemigo.getX();
        int y = enemigo.getY();
        enemigo.mover();

        boolean moved = x != enemigo.getX() || y != enemigo.getY();

        assertTrue("enemy doesnt move", moved);
    }
}
