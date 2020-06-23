package Model;

import Model.Entidades.Burbujas.Burbuja;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BurbujaTest {

    @Test
    public void testBurbujaMovesRight(){

        Burbuja burbuja = new Burbuja(5,5,1);

        int x = burbuja.getX();
        burbuja.mover();

        boolean moved = x < burbuja.getX();

        assertTrue("burbuja doesnt move", moved);
    }

}
