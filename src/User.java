import java.util.ArrayList;

public class User extends Player
{
    private int chosenCardIndx = -1;
    private ArrayList<Card> playableHand;

    public User(Deck d, String n)
    {
        super(d, n);
    }

    public void discard(int index)
    {
        super.discard(index);
    }

    public void cardClicked(int index)
    {
        chosenCardIndx = index;
        // System.out.println(hand.get(index) + " was chosen");
    }

    @Override
    public void takeTurn()
    {
        while (!canPlay())
        {
            try
            {
                Thread.sleep(50 / 3); // wait until a playable card is drawn
            } catch (InterruptedException f) {}
        }

        while (chosenCardIndx == -1)
        {
            try
            {
                Thread.sleep(50 / 3); // wait until a card is chosen
            } catch(InterruptedException f) {}
        }


        if (Main.panel.colorPickerShown())
        {
            Main.panel.toggleColorPicker();
        }

        discard(chosenCardIndx);

        if (Main.deck.getTopDiscard().getNumber() == Card.DRAW2)
        {
            Main.opponent.draw2();
        }
        else if (Main.deck.getTopDiscard().getNumber() == Card.DRAW4)
        {
            Main.opponent.draw4();
        }

        if (Main.deck.getTopDiscard().getColor() == Card.WILD) // if you just played a wild
        {
            Main.panel.setPickerColor(0);
            Main.panel.toggleColorPicker();
            Main.panel.repaint();

            while (Main.deck.getTopDiscard().getColor() == Card.WILD)
            {
                try
                {
                    Thread.sleep(50 / 3); // wait until a color is chosen
                } catch(InterruptedException f) {}
            }

            Main.panel.setPickerColor(Main.deck.getTopDiscard().getColor());
        }
        chosenCardIndx = -1;
        Main.panel.updateLog();
    }
}