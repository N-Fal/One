import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Player
{
    public ArrayList<Card> hand = new ArrayList<Card>();
    public Deck playerDeck;
    private String name;

    public Player(Deck d, String n)
    {
        playerDeck = d;
        name = n;
    }

    public void draw() // removes the top card of the playerDeck and adds it to the players hand
    {
        if (playerDeck.size() == 0)
        {
            playerDeck.reshuffleDiscardPileIntoDrawPile();
        }

        hand.add(playerDeck.get(playerDeck.size() - 1));
        playerDeck.removeTopCard();
    }

    public void discard(int index) // removes an indexed card from the player's hand and adds it to the discard pile of the playerDeck
    {
        playerDeck.addToDiscard(hand.get(index));
        hand.remove(index);
    }

    public boolean canPlay()
    {
        for (Card c : hand)
        {
            if (c.isPlayable())
            {
                return true;
            }
        }
        return false;
    }

    public void draw2()
    {
        draw();
        draw();
    }

    public void draw4()
    {
        draw();
        draw();
        draw();
        draw();
    }

    public ArrayList<Card> getPlayableCards()
    {
        ArrayList<Card> output = new ArrayList<Card>();

        for (Card c : hand)
        {
            if (c.isPlayable())
            {
                output.add(c);
            }
        }

        return output;
    }

    public boolean handIsSorted()
    {
        ArrayList<Card> sortedHand = new ArrayList<Card>();
        for (Card c : hand)
        {
            sortedHand.add(c);
        }
        Collections.sort(sortedHand);
        return (hand.equals(sortedHand));
    }

    public String toString()
    {
        return hand.toString();
    }

    public abstract void takeTurn();

    public boolean hasEmptyHand()
    {
        return (hand.size() == 0);
    }

    public String getName()
    {
        return name;
    }

    // helper method for sortHand(), should only be called after the hand has been sorted.
    public void cycleHand()
    {
        int firstColor = hand.get(0).getColor();

        while (hand.get(0).getColor() == firstColor)
        {
            hand.add(hand.size(), hand.get(0));
            hand.remove (0);
        }
    }
}