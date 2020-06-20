package View;

import java.awt.image.BufferedImage;

public class EnemyAnimation {

    private int index = 0;
    private int time = 2;

    SpriteSheet sheet;

    public EnemyAnimation(SpriteSheet sheet) {
        this.sheet = sheet;
    }

    public void tickAnimation() {
        if (time > 4) {
            time = 0;
        } else time++;
    }

    public BufferedImage imagenAnimacion(int direccion) {

        if (direccion == 1)
            if (index < sheet.getColumnas() - 1) {
                index++;
                return sheet.getSpriteImage(1, index);
            } else {
                index = 0;
                return sheet.getSpriteImage(1, index);
            }
        if (direccion == -1)
            if (index < sheet.getColumnas() - 1) {
                index++;
                return sheet.getSpriteImage(0, index);
            } else {
                index = 0;
                return sheet.getSpriteImage(0, index);
            }
        return null;
    }
}
