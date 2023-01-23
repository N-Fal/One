import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel
{
    private Image spriteSheet = new ImageIcon("src/Assets/UnoSpriteSheet.png").getImage();
    private Image colorPicker = new ImageIcon("src/Assets/ColorPicker.png").getImage();
    private Image background = new ImageIcon("src/Assets/BackgroundTemplate.png").getImage();
    private Image sortButton = new ImageIcon("src/Assets/SortButton.png").getImage();
    private Image cycleButton = new ImageIcon("src/Assets/CycleButton.png").getImage();

    private ArrayList<String> playLog = new ArrayList<String>();

    private boolean pickerShown;
    private int pickerColor;
    private Listener thisListener;

    public Panel(Listener l, Dimension d)
    {
        setSize(d);
        setPreferredSize(d);
        addMouseListener(l);
        thisListener = l;
        setFocusable(true);
        pickerColor = 0;
    }

    @Override
    public void paint(Graphics g)
    {

        g.clearRect(0, 0, 720, 480);
        g.drawImage(background, 0, 0, 706, 444, 0, 0, 360, 240, null);
        g.drawImage(sortButton, 4, 320, 52, 368, 0, 0, 24, 24, null);
        g.drawImage(cycleButton, 60, 320, 108, 368, 0, 0, 24, 24, null);

        for (int i = 0; i < playLog.size(); i++)
        {
            g.drawString(playLog.get(i), 600, i * 20 + 80);
        }

        drawBack(314, 200, g);

        // these following 2 ifs are a hard fix for a bug involving displaying the wrong card after a color was picked from playing a wild card
        if (Main.deck.getTopDiscard().getNumber() == 13)
        {
            g.drawImage(spriteSheet, 362, 200, 394, 248, 0, 96, 16, 120, null);
        }
        else if (Main.deck.getTopDiscard().getNumber() == 14)
        {
            g.drawImage(spriteSheet, 362, 200, 394, 248, 16, 96, 32, 120, null);
        }
        else
        {
            drawCard(Main.deck.getTopDiscard(), 362, 200, g);
        }

        drawCpuHand(g);
        drawHand(g);
        if (pickerShown)
        {
            drawColorPicker(pickerColor, g);
        }
    }

    public void updateLog()
    {
        playLog.add(Main.deck.getTopDiscard().toString());
        if (playLog.size() >= 10)
        {
            playLog.remove(0);
        }
    }

    private void drawHand(Graphics g)
    {
        for (int i = 0; i < Main.player.hand.size(); i++)
        {
            drawCard(Main.player.hand.get(i), i * 42 + 1, 392, g);
        }
    }

    private void drawCpuHand(Graphics g)
    {
         for (int i = 0; i < Main.opponent.hand.size(); i++)
         {
             drawBack(i * 42 + 1, 4, g);
         }

//         to show the opponent's hand:
//         for (int i = 0; i < Main.opponent.hand.size(); i++)
//         {
//             drawCard(Main.opponent.hand.get(i), i * 42 + 1, 4, g);
//         }
    }

    // draws a Card at the given coordinates on the frame
    private void drawCard(Card c, int posX, int posY, Graphics g)
    {
        g.drawImage(spriteSheet, posX, posY, posX + 32, posY + 48, 16 * getCardCoords(c)[0], 24 * getCardCoords(c)[1], 16 * getCardCoords(c)[0] + 16,24 * getCardCoords(c)[1] + 24, null);
    }

    // gives the row-column spritesheet coordinates of a card (for drawing purposes).

    private void drawBack(int posX, int posY, Graphics g) // draws the back of the deck
    {
        g.drawImage(spriteSheet, posX, posY, posX + 32, posY + 48, 32, 96, 48, 120, null);
    }
    private int[] getCardCoords(Card c)
    {
        int row, column;
        if (c.getColor() != Card.WILD)
        {
            column = c.getColor() - 1;
        }
        else
        {
            column = 4;
        }

        if (c.getNumber() <= 12)
        {
            row = c.getNumber();
        }
        else
        {
            row = c.getNumber() - 13;
        }

        return new int[] {row, column};
    }

    public void toggleColorPicker()
    {
        pickerShown = !pickerShown;
    }

    public boolean colorPickerShown()
    {
        return pickerShown;
    }

    public void setPickerColor(int i)
    {
        pickerColor = i;
    }

    private void drawColorPicker(int index, Graphics g)
    {
        g.drawImage(colorPicker, 410, 200, 458, 248, index * 24, 0, index * 24 + 24, 24, null);
    }
}