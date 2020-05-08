import javax.swing.JFrame;

public class Window extends JFrame {

    public Window() {
        setTitle("Bubble Bobble");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

}
