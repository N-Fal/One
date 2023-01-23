import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

public class Listener implements MouseListener
{
    private static ArrayList<Region> handRegions = new ArrayList<Region>();
    private static Region drawRegion = new Region(314, 200, 346, 248);
    private static Region[] colorPickerRegions = new Region[] {new Region(410, 200, 434, 224), new Region(434, 200,458, 224), new Region(410, 224, 434, 248), new Region(434, 224, 458, 248)};
    private static Region sortRegion = new Region(4, 320, 52, 368);
    private static Region cycleRegion = new Region(60, 320, 108, 368);

    public void initializeHandRegions()
    {
        for (int i = 0; i < 17; i++)
        {
            handRegions.add(new Region(i * 42 + 1, 392, i * 42 + 43, 440));
        }
    }

    private Frame thisFrame;

    @Override
    public void mouseClicked(MouseEvent e)
    {
        int mx = getMouseX();
        int my = getMouseY();

        if (drawRegion.inRegion(mx, my) && !Main.player.canPlay() && Main.isPlayerTurn())
        {
            Main.player.draw();
        }

        for (Region r : handRegions)
        {
             if (r.inRegion(mx, my))
             {
                 try
                 {
                     // System.out.println("You clicked your " + Main.player.hand.get(handRegions.indexOf(r)));
                     if (Main.player.hand.get(handRegions.indexOf(r)).isPlayable())
                     {
                         Main.player.cardClicked(handRegions.indexOf(r));
                     }
                 } catch (IndexOutOfBoundsException f) {}
                 break;
             }
        }

        for (int i = 0; i < colorPickerRegions.length; i++)
        {
            if (colorPickerRegions[i].inRegion(mx, my) && Main.panel.colorPickerShown())
            {
                // System.out.println("You clicked " + i);

                switch(i)
                {
                    case 0: // red
                        Main.deck.getTopDiscard().setColor(Card.RED);
                        break;
                    case 1: // blue
                        Main.deck.getTopDiscard().setColor(Card.BLUE);
                        break;
                    case 2: // yellow
                        Main.deck.getTopDiscard().setColor(Card.YELLOW);
                        break;
                    case 3: // green
                        Main.deck.getTopDiscard().setColor(Card.GREEN);
                        break;
                }
            }
        }

        if(sortRegion.inRegion(mx, my))
        {
            Collections.sort(Main.player.hand);
        }

        if (cycleRegion.inRegion(mx, my))
        {
            Main.player.cycleHand();
        }

        Main.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    public void setFrame(Frame f)
    {
        thisFrame = f;
    }

    public int getMouseX()
    {
        return MouseInfo.getPointerInfo().getLocation().x - thisFrame.getLocation().x;
    }

    public int getMouseY()
    {
        // -25 accounts for the taskbar of the window.
        return MouseInfo.getPointerInfo().getLocation().y - thisFrame.getLocation().y - 25;
    }
}
