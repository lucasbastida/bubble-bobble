package Model;

import Model.Entidades.*;
import Model.Entidades.Burbujas.Burbuja;
import Model.Entidades.Items.Item;
import Model.Entidades.Items.ItemEspecial;
import View.Images.LevelImage;
import util.Observer;
import util.ObserverEstadisticas;
import util.Subject;
import util.SujetoEstadisticas;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Juego implements Runnable, Subject, SujetoEstadisticas {

//    private static final int PWIDTH = 1280; // size of panel
//    private static final int PHEIGHT = 720;

    private Thread animator; // for the animation

    public volatile boolean running = false; // stops the animation
    private volatile boolean gameOver = false; // for game termination

    private long lastFpsTime;  //The last time at which we recorded the frame rate
    private int fps;  //The current number of frames recorded

    private CopyOnWriteArrayList<Observer> observers = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<ObserverEstadisticas> observersEstadisticas = new CopyOnWriteArrayList<>();

    //TODO cargar valores desde un archivo o clase que tenga las configuraciones?
    private Jugador jugador;
    private CopyOnWriteArrayList<Enemigo> enemigos = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<Bloque> bloques = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<Item> items = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<EnemigoBurbuja> enemigosBurbuja = new CopyOnWriteArrayList<>();
    private Item itemEspecial = new ItemEspecial(200, 200); //Lo creo aca para que se pueda agregar solo uno

    public Juego(){
        enemigos.add(new Enemigo(300,300));
        enemigos.add(new Enemigo(200,200));
        enemigos.add(new Enemigo(500,500));
        enemigos.add(new Enemigo(500,400));
        jugador = new Jugador (200, 200);
        createWalls();
    }

    public void run() {

        long sleepTime;
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        running = true;

        while (running) {
            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double) OPTIMAL_TIME);

            // update the frame counter
            lastFpsTime += updateLength;
            fps++;

            // update our FPS counter if a second has passed since we last recorded
            if (lastFpsTime >= 1000000000) {
                System.out.println("(FPS: " + fps + ")");
                lastFpsTime = 0;
                fps = 0;
            }

            // update the game logic
            gameUpdate();

            //notify observer of game update
            notifyObservers();

            sleepTime = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000; //time left in this loop

            if (sleepTime <= 0) // update/render took longer than period
                sleepTime = 1; // sleep a bit anyway
            try {
                Thread.sleep(sleepTime); // in ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        System.exit(0);

    }

    public Jugador getJugador() {
        return jugador;
    }

    public void startGame()// initialise and start the thread
    {
        if (animator == null || !running) {
            animator = new Thread(this, "Game Thread");
            animator.start();
        }
    }

    private void gameUpdate() { //if (!gameOver)
        // update game state ...
        jugador.mover(getWalls(), getItems(), getEnemigosBurbuja());
        checkCollisionsItems();
        checkCollisionsEnemigoBurbuja();
        moverBurbujas();
        moverEnemigos();
        jugador.checkCollisions(enemigos);
        if(jugador.getPuntajeAcumulado()==2000 & !items.contains(itemEspecial)){
            crearItemEspecial();
            notifyObserversEstadisticas();
        }
    }

    public void moverBurbujas() {
        for (Burbuja b :
                jugador.getBurbujas()) {
            b.mover();
            b.checkCollisions(enemigos, items, enemigosBurbuja);
        }
    }

    public void moverEnemigos() {
        for (Enemigo e :
                enemigos) {
            e.mover();
        }
    }

    private void crearItemEspecial(){
        items.add(itemEspecial);
    }

    public void stopGame() // called by the user to stop execution
    {
        running = false;
    }

    public CopyOnWriteArrayList<Enemigo> getEnemigos(){
        return enemigos;
    }

    public void createWalls(){
        LevelImage level = new LevelImage("/Nivel1.png");
        level.loadImageLevel(bloques);
    }
    public CopyOnWriteArrayList<Bloque> getWalls(){
        return bloques;
    }

    public CopyOnWriteArrayList<Item> getItems(){return items;}

    public CopyOnWriteArrayList<EnemigoBurbuja> getEnemigosBurbuja(){return enemigosBurbuja;}

    public void checkCollisionsEnemigoBurbuja() {
        Rectangle r1 = jugador.getBounds();
        for (EnemigoBurbuja e : enemigosBurbuja) {

            Rectangle r2 = e.getBounds();

            if (r1.intersects(r2)) {
                enemigosBurbuja.remove(e);
                items.add(new Item(e.getX(), e.getY()));
                notifyObserversEstadisticas();
            }
        }
    }

    public void checkCollisionsItems() {
        Rectangle r1 = jugador.getOffsetBounds();

        for (Item i : items) {

            Rectangle r2 = i.getBounds();

            if (r1.intersects(r2)) {
                items.remove(i);
                notifyObserversEstadisticas();
                jugador.sumarPuntaje(i.getPuntaje());
                if (i instanceof ItemEspecial) {
                    System.out.println("Nueva Habilidad");
                    jugador.cambiarHabilidad();
                    //TODO: aca se setearia la nueva habilidad
                }

            }
        }
    }

    @Override
    public boolean registerObserver(Observer observer) {
        return observers.add(observer);
    }

    @Override
    public boolean removeObserver(Observer observer) {
        return observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer o :
                observers) {
            o.update();
        }
    }

    @Override
    public boolean registerObserver(ObserverEstadisticas observer) {

        return observersEstadisticas.add(observer);
    }

    @Override
    public boolean removeObserver(ObserverEstadisticas observer) {

        return observersEstadisticas.remove(observer);
    }


    public void notifyObserversEstadisticas() {
        for (ObserverEstadisticas o :
                observersEstadisticas) {
            o.updateEstadisticas();
        }
    }
}
