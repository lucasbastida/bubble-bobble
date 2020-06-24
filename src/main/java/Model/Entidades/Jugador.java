package Model.Entidades;

import Model.Entidades.Burbujas.Burbuja;
import Model.Entidades.Burbujas.Elemento;
import Model.Entidades.Burbujas.ElementoAire;
import Model.Entidades.Burbujas.ElementoFuego;
import Model.Entidades.Items.Item;
import Model.Entidades.Items.ItemEspecial;
import util.Observer;
import util.ObserverEstadisticas;
import util.Subject;
import util.SujetoEstadisticas;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Jugador extends Sprite implements SujetoEstadisticas {

    private double dy, dx;
    protected double maxDY; //limit to fastest we can go falling or jumping
    protected double gravity;
    protected boolean falling = true;
    protected boolean canJump;

    private boolean mirandoDerecha = true;
    private boolean disparando = false;
    private int direccion = 1;

    private int puntajeAcumulado = 0;
    private int vidas;
    private Elemento habilidad;

    private final CopyOnWriteArrayList<Burbuja> burbujas;

    private boolean alive = true;

    private CopyOnWriteArrayList<ObserverEstadisticas> observersEstadisticas = new CopyOnWriteArrayList<>();

    public Jugador(int x, int y) {
        super(x, y, 320 / 5, 192 / 3);
        burbujas = new CopyOnWriteArrayList<>();

        vidas = 3;
        habilidad = new ElementoAire();

        gravity = 0.5;
        maxDY= 5;
    }

    public void mover(CopyOnWriteArrayList<Bloque> walls,
                      CopyOnWriteArrayList<Item> items,
                      CopyOnWriteArrayList<EnemigoBurbuja> enemigosBurbuja) {

        if (!hasHorizonatlCollison(walls)) {
            x += dx*2;
        }
        if (!hasVerticalCollision(walls)) {
            y += dy;
        }
        //checkCollisionsItems(items);
        //checkCollisionsEnemigoBurbuja(enemigosBurbuja, items);
        // luego modificar para queenemigos hagan lo mismo

        fall();
        //System.out.println("posicion jugador: " + x + ":" + y);
    }

    public void disparar() {
        disparando = true;
        burbujas.add(crearBurbuja(habilidad));
    }


    public CopyOnWriteArrayList<Burbuja> getBurbujas() {
        return burbujas;
    }


    public synchronized void setDy(int dy) {
        this.dy = dy;
    }

    public synchronized void setDx(int dx) {
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

    public boolean hasVerticalCollision(CopyOnWriteArrayList<Bloque> walls) {
        for (Bloque b : walls) {
            if (getOffsetBounds().intersects(b.getTop()) && dy > 0) {
                canJump = true;
                falling = false;
                dy = 0;
                gravity = 0;
                return true;
            } else {
                falling = true;
                gravity = 0.5;
            }
            if (getBounds().intersects(b.getBottom()) && dy < 0) {
                dy = 0;
                return true;
            }
        }
        return false;
    }

    public boolean hasHorizonatlCollison(CopyOnWriteArrayList<Bloque> walls) {
        for (Bloque b : walls) {
            if (getBounds().intersects(b.getLeft()) && dx > 0) {
                return true;
            }
            if (getBounds().intersects(b.getRight()) && dx < 0) {
                return true;
            }
        }
        return false;
    }

    public void fall(){
        if(falling){
            dy += gravity;
            if(dy >maxDY) dy = maxDY;
        }
    }

    public void jump(double acceleration){
        if(canJump){
            dy -= acceleration;
            canJump = false;
        }
    }

    public void moveRight(boolean moveRight){

    }

    public void morir() {
        //TODO actialiar animacion
//        setSprite(2,4);
        alive = false;
        //notifyObserversEstadisticas(); esto se haría pero cuando se empiecen a restar vidas
    }

    public int getDireccion() {
        return direccion;
    }

    public Rectangle getOffsetBounds() {
        return new Rectangle(x + (int) dx, y + (int) dy, alto, ancho);
    }

    public void setMirandoDerecha(boolean mirandoDerecha) {
        this.mirandoDerecha = mirandoDerecha;
        if (mirandoDerecha) direccion = 1;
        else direccion = -1;
    }

    public boolean isAlive() {
        return alive;
    }

    public void sumarPuntaje(int puntos) {
        puntajeAcumulado += puntos;
        notifyObserversEstadisticas();
        System.out.println("Puntaje: " + puntajeAcumulado);
    }

    public int getPuntajeAcumulado() {
        return puntajeAcumulado;
    }

    private Burbuja crearBurbuja(Elemento elemento) {
        Burbuja b = new Burbuja(x, y, direccion);
        b.setElemento(elemento);
        return b;
    }

    public void cambiarHabilidad() {
        habilidad = new ElementoFuego();
    }

    public int getVidasRestantes(){
        return vidas;
    }

    @Override
    public boolean registerObserver(ObserverEstadisticas observerEstadisticas) {

        return observersEstadisticas.add(observerEstadisticas);
    }

    @Override
    public boolean removeObserver(ObserverEstadisticas observerEstadisticas) {
     return observersEstadisticas.remove(observerEstadisticas);
    }

    public void notifyObserversEstadisticas() {
        for (ObserverEstadisticas o :
                observersEstadisticas) {
            o.updateEstadisticas();
        }
    }
}
