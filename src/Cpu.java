import java.util.Collections;

public class Cpu extends Player
{

    public Cpu(Deck d, int n)
    {
        super(d, ("CPU " + n));
    }

    @Override
    public void takeTurn()
    {
        try
        {
            Thread.sleep(750); // to make the cpu feel more "real"
        } catch(InterruptedException f) {}

        // 1. draw until you have a playable card
        for (int i = 0; i < hand.size(); i++)
        {
            while (!canPlay())
            {
                draw();

                Main.panel.repaint();
                try
                {
                    Thread.sleep(750); // to make the cpu feel more "real"
                } catch(InterruptedException f) {}
            }
        }

        Collections.sort(hand);

        // turn off the color picker
        if (Main.panel.colorPickerShown())
        {
            Main.panel.toggleColorPicker();
        }

        // 2. find the first playable card and play it
        // TO-DO: make the cpu play skipping cards optimally instead of what is essentially random (loop backwards?)
        int selectedCard = -1;

        for (int i = 0; i < hand.size(); i++)
        {
            if (hand.get(i).isPlayable())
            {
                selectedCard = i;
                break;
            }
        }
        // prioritize playing a skip card over a regular card to encourage chaining skips together
        for (int i = 0; i < hand.size(); i++)
        {
            if (hand.get(i).isSkip() && hand.get(i).isPlayable())
            {
                selectedCard = i;
                break;
            }
        }

        discard(selectedCard);

        // 3. make the player draw cards if needed
        if (Main.deck.getTopDiscard().getNumber() == Card.DRAW2)
        {
            Main.player.draw2();
        }
        else if (Main.deck.getTopDiscard().getNumber() == Card.DRAW4)
        {
            Main.player.draw4();
        }

        // 4. choose the best wild color if a wild card was just played
        if (Main.deck.getTopDiscard().getColor() == Card.WILD)
        {
            Main.panel.setPickerColor(0);
            Main.panel.toggleColorPicker();
            Main.panel.repaint();

            try {Thread.sleep(1000);} catch(InterruptedException e) {}
            Main.deck.getTopDiscard().setColor(selectBestColor());
            Main.panel.setPickerColor(Main.deck.getTopDiscard().getColor());
        }

        try {Thread.sleep(50 / 3);} catch(InterruptedException e) {}
        Main.panel.updateLog();
    }

    // returns whichever color the cpu has the most of
    private int selectBestColor()
    {
        int[] colorCounts = new int[4];
        for (Card c : hand)
        {
            switch (c.getColor())
            {
                case Card.RED:
                    colorCounts[0]++;
                    break;
                case Card.GREEN:
                    colorCounts[1]++;
                    break;
                case Card.BLUE:
                    colorCounts[2]++;
                    break;
                case Card.YELLOW:
                    colorCounts[3]++;
                    break;
                default:
            }
        }

        int biggest = Integer.MIN_VALUE;

        for (int i = 0; i < colorCounts.length; i++)
        {
            if (colorCounts[i] > biggest)
            {
                biggest = i;
            }
        }

        return biggest + 1;
    }
}
