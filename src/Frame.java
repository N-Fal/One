import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame
{
    private Image icon = new ImageIcon("src/Assets/Icon.png").getImage();

    public Frame(Panel p, Dimension d)
    {
        setTitle("One: the conceptually original card game");
        setSize(d);
        add(p);
        setLocation(0, 0);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setIconImage(icon);
    }
}
