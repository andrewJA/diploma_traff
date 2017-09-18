import javax.swing.*;
import java.awt.*;

/**
 * Created by qual2 on 06.05.2017.
 */
public class MyJFrame extends JFrame {
    public MyJFrame(String title, int width, int height) {
        super(title);

        super.setSize(width, height);
        super.setLocationRelativeTo((Component)null);
        super.setDefaultCloseOperation(3);
        super.setLayout(new FlowLayout());

    }

    public MyJFrame(String title, int width, int height, Component comp) {
        this(title, width, height);
        super.getContentPane().add(comp);
    }

    public MyJFrame(String title, int width, int height, LayoutManager layout) {
        super(title);

        super.setSize(width, height);
        super.setLocationRelativeTo((Component)null);
        super.setDefaultCloseOperation(3);
        super.setLayout(layout);

    }
}
