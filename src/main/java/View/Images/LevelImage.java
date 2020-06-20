package View.Images;

import Model.Entidades.Burbujas.Burbuja;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

public class LevelImage{

    private BufferedImage image;

    public LevelImage(String dir){
        image = cargarSprite(dir);
    }

    public void loadImageLevel(CopyOnWriteArrayList<Burbuja> bloques){
        int h = image.getHeight();
        int w = image.getWidth();

        for(int x = 0; x < h; x++){
            for(int y = 0; y < w; y++){
                int pixel = image.getRGB(x,y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel >> 0) & 0xff;

                if(red == 255 & green == 255 & blue == 255){
                    bloques.add(new Burbuja(x*64,y*64,1));
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
}
