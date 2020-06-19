package Model.Entidades;

public class Enemigo extends Sprite {
    private int index = 0;
    private int time = 2;
    private int direccion = -1; //esta orientado hacia la izquierda

    public Enemigo(int x, int y) {
        super(x, y, 128, 256, 2, 4, "/walker.png");
        cargarSprite();
        setSprite(0,0);
    }

    public void mover(){
        int speed = 2;
        if(getX()>1200) direccion = -1;
        if(getX()<10) direccion = 1;
        setX(getX() + speed*direccion);
    }
    public void animacion(){
    if(time > 4) {
        time = 0;
        if(direccion == 1)
        if (index < getColumnas()-1) {
            index++;
            setSprite(1, index);
        } else {
            index = 0;
            setSprite(1, index);
        }
        if(direccion == -1)
            if (index < getColumnas()-1) {
                index++;
                setSprite(0, index);
            } else {
                index = 0;
                setSprite(0, index);
            }
    }else time++;

    }

    //TODO: implementar algoritmo de movimiento
}
