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
    private int speed = 2;
    private int gravedad =1; //esta variable debe ser igual para los enemigos

    private Burbuja habilidad;
    private final CopyOnWriteArrayList<Burbuja> burbujas;

    private boolean alive = true;

    public Jugador(int x, int y) {
        super(x, y, 320 / 5, 192 / 3);
        burbujas = new CopyOnWriteArrayList<>();
        setHabilidad(new Burbuja(x + 30, y, 1));//cambiar esto
    }

    public void mover(CopyOnWriteArrayList<Bloque> walls,
                      CopyOnWriteArrayList<Item> items) {

        if(!checkCollisionsWall(walls)){
            x += dx*speed;
            y += dy*speed;
            checkCollisionsItems(items);
        }

        //System.out.println("posicion jugador: " + x + ":" + y);
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
    public boolean checkCollisionsWall(CopyOnWriteArrayList<Bloque> walls) {
    //TODO: hacer un metodo que funcione
        Rectangle r1 = this.getOffsetBounds();

        for (Bloque b : walls) {

            Rectangle r2 = b.getBounds();

            if (r1.intersects(r2)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkCollisionsItems(CopyOnWriteArrayList<Item> items) {
        //TODO: hacer un metodo que funcione
        Rectangle r1 = this.getOffsetBounds();

        for (Item i : items) {

            Rectangle r2 = i.getBounds();

            if (r1.intersects(r2)) {
                items.remove(i);
            }
        }
        return false;
    }

    public void morir() {
        //TODO actialiar animacion
//        setSprite(2,4);
        alive = false;
    }

    public int getDireccion() {
        return direccion;
    }

    public Rectangle getOffsetBounds() {
        return new Rectangle(x + dx, y + dy, alto, ancho);
    }

    public void setMirandoDerecha(boolean mirandoDerecha) {
        this.mirandoDerecha = mirandoDerecha;
        if (mirandoDerecha) direccion = 1;
        else direccion = -1;
    }

    public void caerPorGravedad(){
        y += gravedad;
    }

    public boolean isAlive() {
        return alive;
    }
}
