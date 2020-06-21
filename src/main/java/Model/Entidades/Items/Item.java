package Model.Entidades.Items;

import Model.Entidades.Sprite;

public class Item extends Sprite {

    private int puntaje = 1000; //Puntos suma el jugador cuando recoge el item

    public Item(int x, int y) {
        super(x, y, 56/1, 64/1);
    }

    public int getPuntaje(){
        return puntaje;
    }

    protected void setPuntaje(int puntos){
        this.puntaje = puntos;
    }
}
