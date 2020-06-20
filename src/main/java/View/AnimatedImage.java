package View;

import java.awt.image.BufferedImage;

public class AnimatedImage {

    private int index = 0;
    private int time = 2;

    private SpriteSheet sheet;
    private BufferedImage spriteActual;

    public AnimatedImage(SpriteSheet sheet) {
        this.sheet = sheet;
    }

    public void animate(int direccion) {
        if (time > 4) {
            time = 0;
            if (direccion == 1)
                if (index < sheet.getColumnas() - 1) {
                    index++;
                    spriteActual = sheet.getSpriteImage(1, index);
                } else {
                    index = 0;
                    spriteActual = sheet.getSpriteImage(1, index);
                }
            if (direccion == -1)
                if (index < sheet.getColumnas() - 1) {
                    index++;
                    spriteActual = sheet.getSpriteImage(0, index);
                } else {
                    index = 0;
                    spriteActual = sheet.getSpriteImage(0, index);
                }
        } else time++;
    }

    public BufferedImage getSpriteActual() {
        return spriteActual;
    }
}
