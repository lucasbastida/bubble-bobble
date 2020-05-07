import javax.swing.JFrame;

public class Window extends JFrame {
    //public static final long serialVersionUID = 1L;

    private GamePanel gamePanel;

    public Window() {
        setTitle("Bubble Bobble");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel());
        // setIgnoreRepaint(true);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

}
