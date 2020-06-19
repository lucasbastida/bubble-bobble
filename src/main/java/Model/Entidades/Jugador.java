package Model.Entidades;

import Model.Entidades.Burbujas.Burbuja;

import java.util.ArrayList;

public class Jugador extends Sprite {

    private int dy, dx;

    //esto se usa solamente para la animacion
    private static int index = 0;
    public boolean mirandoDerecha = true;
    public boolean disparando = false;

    private Burbuja habilidad;
    private final ArrayList<Burbuja> burbujas;

    public Jugador(int x, int y) {
        super(x, y, 192, 320);
        burbujas = new ArrayList<>();
        setHabilidad(new Burbuja(x + 30, y, 1));//cambiar esto
    }

    public void mover() {
        x += dx;
        y += dy;
        System.out.println("posicion jugador: " + x + ":" + y);
    }

    public void disparar() {
        disparando = true;
        int direccion;
        if (mirandoDerecha) direccion = 1;
        else direccion = -1;
        setHabilidad(new Burbuja(x, y, direccion));
        burbujas.add(getHabilidad());
    }

    private void setHabilidad(Burbuja burbuja) {
        habilidad = burbuja;
    }

    public Burbuja getHabilidad() {
        return habilidad;
    }

    public ArrayList<Burbuja> getBurbujas() {
        return burbujas;
    }


    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

}
