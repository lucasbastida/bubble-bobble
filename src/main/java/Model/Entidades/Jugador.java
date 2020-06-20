package Model.Entidades;

import Model.Entidades.Burbujas.Burbuja;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Jugador extends Sprite {

    private int dy, dx;

    private boolean mirandoDerecha = true;
    private boolean disparando = false;
    private int direccion = 1;

    private Burbuja habilidad;
    private final CopyOnWriteArrayList<Burbuja> burbujas;

    private boolean alive = true;

    public Jugador(int x, int y) {
        super(x, y, 320 / 5, 192 / 3);
        burbujas = new CopyOnWriteArrayList<>();
        setHabilidad(new Burbuja(x + 30, y, 1));//cambiar esto
    }

    public void mover() {
        x += dx;
        y += dy;
        System.out.println("posicion jugador: " + x + ":" + y);
    }

    public void disparar() {
        disparando = true;
        setHabilidad(new Burbuja(x, y, direccion));
        burbujas.add(getHabilidad());
    }

    private void setHabilidad(Burbuja burbuja) {
        habilidad = burbuja;
    }

    public Burbuja getHabilidad() {
        return habilidad;
    }

    public CopyOnWriteArrayList<Burbuja> getBurbujas() {
        return burbujas;
    }


    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void checkCollisions(CopyOnWriteArrayList<Enemigo> enemigos) {

        Rectangle r1 = this.getBounds();

        for (Enemigo enemigo : enemigos) {

            Rectangle r2 = enemigo.getBounds();

            if (r1.intersects(r2)) {
                morir();
            }
        }
    }

    public void morir() {
        //TODO actialiar animacion
//        setSprite(2,4);
        alive = false;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setMirandoDerecha(boolean mirandoDerecha) {
        this.mirandoDerecha = mirandoDerecha;
        if (mirandoDerecha) direccion = 1;
        else direccion = -1;
    }

    public boolean isAlive() {
        return alive;
    }
}
