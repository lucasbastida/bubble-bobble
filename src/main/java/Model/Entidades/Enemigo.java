package Model.Entidades;

public class Enemigo extends Sprite {
    private int index = 0;
    private int time = 2;
    private int direccion = -1; //esta orientado hacia la izquierda

    public Enemigo(int x, int y) {
        super(x, y, 256/4, 128/2);
    }

    public void mover(){
        int speed = 2;
        if(x>1200) direccion = -1;
        if(x<10) direccion = 1;
        x = x + speed*direccion;
    }

    public int getDireccion() {
        return direccion;
    }

}
