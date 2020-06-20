package View;

public class EnemyAnimation {

    private int index = 0;
    private int time = 2;

    SpriteSheet sheet;

    public EnemyAnimation(SpriteSheet sheet) {
        this.sheet = sheet;
    }

    public void animacion(int direccion){
        if(time > 4) {
            time = 0;
            if(direccion == 1)
                if (index < sheet.getColumnas()-1) {
                    index++;
                    sheet.setSpriteActual(1, index);
                } else {
                    index = 0;
                    sheet.setSpriteActual(1, index);
                }
            if(direccion == -1)
                if (index < sheet.getColumnas()-1) {
                    index++;
                    sheet.setSpriteActual(0, index);
                } else {
                    index = 0;
                    sheet.setSpriteActual(0, index);
                }
        }else time++;

    }
}
