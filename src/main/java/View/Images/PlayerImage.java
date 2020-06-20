package View.Images;

public class PlayerImage extends AnimatedImage {

    public PlayerImage(SpriteSheet sheet) {
        super(sheet);
    }

    private PlayerState state = PlayerState.IDLE;

    private int index = 0;
    private int time = 2;

    public void animate(int direccion) {
        if (time > 4) {
            time = 0;
            if (index < sheet.getColumnas() - 1) {
                index++;
            } else {
                index = 0;
            }
            if (direccion == 1) { //is esta mirando a la derecha
                switch (state) {
                    case MOVING_RIGHT:
                        spriteActual = sheet.getSpriteImage(1, index);
                        break;
                    case IDLE:
                        spriteActual = sheet.getSpriteImage(1, 4);
                        break;
                    case JUMPING:
                        spriteActual = sheet.getSpriteImage(2, 3);
                        break;
                    case ATTACKING:
                        spriteActual = sheet.getSpriteImage(2, 1);
                        break;
                }
            } else if (direccion == -1) {
                switch (state) {
                    case MOVING_LEFT:
                        spriteActual = sheet.getSpriteImage(0, index);
                        break;
                    case IDLE:
                        spriteActual = sheet.getSpriteImage(0, 0);
                        break;
                    case JUMPING:
                        spriteActual = sheet.getSpriteImage(2, 2);
                        break;
                    case ATTACKING:
                        spriteActual = sheet.getSpriteImage(2, 0);
                        break;
                }
            }

        } else time++;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

}
