package Model.Entidades.Burbujas;

import Model.Entidades.Sprite;

public class Burbuja extends Sprite { //Esto deberia ser una clase, no una interfaz

    private final int direccion;

    public Burbuja(int x, int y, int direccion) {
        super(x, y, 128/2, 64);
        this.direccion = direccion;
    }

    //TODO implementar que las burbujas desaparezan despues de un tiempo
    public void mover() {
        int speed = 3;
        x = x + speed * direccion;

    }
}
