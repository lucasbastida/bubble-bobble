import Model.GamePanel;
import View.Window;
import org.junit.Test;

import static org.junit.Assert.*;

public class GamePanelTest {

    @Test
    public void startGame(){
        Window window = new Window();
        GamePanel gamePanel = (GamePanel) window.getContentPane();
        assertTrue(gamePanel.running);
    }

    @Test
    public void stopGame() {
        Window window = new Window();
        GamePanel gamePanel = (GamePanel) window.getContentPane();
        gamePanel.stopGame();
        assertFalse(gamePanel.running);
    }

}