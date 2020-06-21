package View.Images;

import Model.Entidades.Bloque;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

public class LevelImage{

    private BufferedImage image;

    public LevelImage(String dir){
        image = cargarSprite(dir);
    }

    public void loadImageLevel(CopyOnWriteArrayList<Bloque> bloques){
        int h = image.getHeight();
        int w = image.getWidth();

        for(int x = 0; x < h; x++){
            for(int y = 0; y < w; y++){
                int pixel = image.getRGB(x,y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel >> 0) & 0xff;

                if(red == 255 & green == 255 & blue == 255){
                    bloques.add(new Bloque(x*32,y*32)); //coordenadas*tamanio de sprite
                }
            }
        }
    }
    private BufferedImage cargarSprite(String dir) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(dir));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    //NOTA para la creacion de nuevos niveles: en la pantalla entran 22x39 pixeles (panelSize/tamanioSprite aprox)
}
