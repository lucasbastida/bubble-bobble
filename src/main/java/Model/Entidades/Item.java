package Model.Entidades;

public class Item extends Sprite {

    private int puntaje; //Puntos suma el jugador cuando recoge el item

    public Item(int x, int y) {
        super(x, y, 64/1, 64/1);
        puntaje = 1000; //
    }

    public int getPuntaje(){
        return puntaje;
    }
}
