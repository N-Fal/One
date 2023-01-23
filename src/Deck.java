import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;

public class Deck
{
    private ArrayList<Card> drawPile = new ArrayList<Card>(), discardPile = new ArrayList<Card>();

    public Deck() // Default constructor creates a default Uno deck
    {
        // a standard Uno deck has 108 cards: one zero of every color (4), two of every number from 1 to 9 of every color (72), 2 skips, reverses, and draw 2s for each color (24), 4 draw 4 wild cards (4), and 4 normal wild cards (4)

        for (int i = 1; i <= 4; i++)
        {
            drawPile.add(new Card(0, i, this)); // once of each color of 0
        }

        for (int i = 1; i <= 12; i++) // numbers 1-9, skip (10), reverse (11), and add 2 (12)
        {
            for (int j = 1; j <= 4; j++) // each color
            {
                for (int k = 0; k < 2; k++) // 2 of each
                {
                    drawPile.add(new Card(i, j, this));
                }
            }
        }

        for (int i = 13; i <= 14; i++) // add 4 and wild
        {
            for (int j = 0; j < 4; j++) // 4 of each
            {
                drawPile.add(new Card(i, Card.WILD, this));
            }
        }
    }

    public void shuffle() // randomizes the position of every card in the deck
    {
        ArrayList<Card> newOrder = new ArrayList<Card>();
        int random;
        int length = drawPile.size();

        for (int i = 0; i < length; i++)
        {
            random = (int) (Math.random() * drawPile.size());
            newOrder.add(drawPile.get(random));
            drawPile.remove(random);
        }
        drawPile = newOrder;
    }

    public void reshuffleDiscardPileIntoDrawPile() // add every card in the discard pile, except for the top card of the discard pile, into the draw pile.
    {
        for (int i = discardPile.size() - 2; i >= 0; i--)
        {
            drawPile.add(discardPile.get(i));
            discardPile.remove(i);
        }

        shuffle();

        // setting all the wild cards that had their color changed back into wilds.
        for (Card c : drawPile)
        {
            if (c.getNumber() == Card.CIRCLE || c.getNumber() == Card.DRAW4)
            {
                c.setColor(Card.WILD);
            }
        }

        System.out.println("The deck successfully reset!");
    }

    public void removeTopCard() // removes the top card of the deck (for player drawing purposes)
    {
        drawPile.remove(drawPile.size() - 1);
    }

    public void addToDiscard(Card c) // adds a given card to the discard pile
    {
        discardPile.add(c);
    }

    public Card getTopDiscard()
    {
        // OOB exception happened here

        return discardPile.get(discardPile.size() - 1);
    }

    public void mill() // moves the top card of the draw pile to the discard pile
    {
        addToDiscard(drawPile.get(drawPile.size() - 1));
        removeTopCard();
    }

    public int size()
    {
        return drawPile.size();
    }

    public int discardSize()
    {
        return discardPile.size();
    }

    public Card get(int index)
    {
        return drawPile.get(index);
    }

    public String toString()
    {
        return drawPile + " \n" + discardPile;
    }
}