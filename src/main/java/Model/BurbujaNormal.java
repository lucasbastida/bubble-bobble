package Model;

import Model.Entidades.Enemigo;
import Model.Entidades.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BurbujaNormal extends Sprite implements Burbuja  {
    private final int direccion;

    public BufferedImage spriteActual;

    public BurbujaNormal(int x, int y, int direccion) {
        super(x, y, 64,128, 1, 2, "/burbuja.png");
        this.direccion = direccion;
        cargarSprite();
    }

    public void animacionDisparar(){
        setSprite(0,0);
    }
    public void animacionStop(){ setSprite(0,1); } //esto no me acuerdo cuando se usa ni por que

    protected void setSprite(int x, int y){
        BufferedImage[][] array = splitImage();
        spriteActual = array[x][y];
    }
    public BufferedImage getSprite(){
        return spriteActual;
    }

    public void mover(){
        int speed = 3;
        setX(getX() + speed*direccion);
       //TODO implementar que las burbujas desaparezan despues de un tiempo
    }
    public void checkCollisions(ArrayList<Enemigo> enemigos) {

        Rectangle r1 = this.getBounds();
        for (Enemigo enemigo : enemigos) {

            Rectangle r2 = enemigo.getBounds();

            if (r1.intersects(r2)) {
                atraparEnemigo(enemigo);
                enemigos.remove(enemigo);
            }
        }

    }

    private void atraparEnemigo(Enemigo enemigo) {

    }

}
