package Model.Entidades;

import Model.Entidades.Burbujas.Burbuja;
import Model.Entidades.Burbujas.Elemento;
import Model.Entidades.Burbujas.ElementoAire;
import Model.Entidades.Burbujas.ElementoFuego;
import Model.Entidades.Items.Item;
import Model.Entidades.Items.ItemEspecial;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Jugador extends Sprite {

    private int dy, dx;

    private boolean mirandoDerecha = true;
    private boolean disparando = false;
    private int direccion = 1;
    private int speed = 2;
    private int gravedad =1; //esta variable debe ser igual para los enemigos

    private int puntajeAcumulado = 0;
    private Elemento habilidad;

    private final CopyOnWriteArrayList<Burbuja> burbujas;

    private boolean alive = true;

    public Jugador(int x, int y) {
        super(x, y, 320 / 5, 192 / 3);
        burbujas = new CopyOnWriteArrayList<>();
        habilidad = new ElementoAire();
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
        burbujas.add(crearBurbuja(habilidad));
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
    public void checkCollisionsItems(CopyOnWriteArrayList<Item> items) {
        Rectangle r1 = this.getOffsetBounds();

        for (Item i : items) {

            Rectangle r2 = i.getBounds();

            if (r1.intersects(r2)) {
                items.remove(i);
                sumarPuntaje(i.getPuntaje());
                if(i instanceof ItemEspecial){
                    System.out.println("Nueva Habilidad");
                    cambiarHabilidad();
                    //TODO: aca se setearia la nueva habilidad
                }

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

    public void sumarPuntaje(int puntos){
        puntajeAcumulado += puntos;
        System.out.println("Puntaje: " + puntajeAcumulado);
    }

    public int getPuntajeAcumulado(){return puntajeAcumulado;}

    private Burbuja crearBurbuja(Elemento elemento){
        Burbuja b = new Burbuja(x,y,direccion);
        b.setElemento(elemento);
        return b;
    }

    private void cambiarHabilidad(){
        habilidad = new ElementoFuego();
    }

}
