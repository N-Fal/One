import java.awt.*;

/*
 * This is the latest, compilable version of my final project. It works exactly like Uno:
 * - You play a card by clicking on it in your "hand," or the row at the bottom.
 * - It has to match the color or number of the current card in the "discard pile" in the middle.
 * - If you don't have a card to play, click the "deck" in the middle to draw more until you have one to play.
 * - If you play a skip card (Reverse, Skip, Draw 2, or Draw 4) you'll get to play again immediately.
 * - Wild cards will prompt you to select a color with a wheel in the middle.
 * - There are two buttons in the bottom left of the window: a "sort" button and a "cycle" button.
 * - The sort button will sort your hand for you.
 * - The cycle button shifts the cards around.
 * - The list in the top right of the window shows the last few cards played.
 *  - The cpu opponent tries to chain together skip cards when it can, and when choosing a color it chooses whatever color it has the most of.
 */


public class Main
{
    public static Deck deck = new Deck();
    public static User player = new User(deck, "Nolan");
    public static Cpu opponent = new Cpu(deck, 1);

    private static Player[] players = new Player[] {player, opponent};

    private static Dimension screenSize = new Dimension(720, 480);
    private static Listener mouseInput = new Listener();
    public static Panel panel = new Panel(mouseInput, screenSize);
    private static Frame frame = new Frame(panel, screenSize);

    private static boolean playerTurn;
    public static void main(String[] args)
    {
        mouseInput.setFrame(frame);
        mouseInput.initializeHandRegions();

        setup();

//        System.out.println(deck);
//        for (Player p : players)
//        {
//            System.out.println(p);
//        }

        playerTurn = false;

        game:
        while(true)
        {
            for (Player p : players)
            {
                playerTurn = !playerTurn; // true means it's the player's turn, false means it's the opponent's turn
                do
                {
                    panel.repaint();
                    p.takeTurn();
                    if (!determineWinner().equals(""))
                    {
                        break game;
                    }
                } while (topCardSkip());
            }
        }

        // System.out.println(determineWinner() + " won!");
        System.exit(0);
    }

    public static void setup() // deals out a hand to every player, and moves the top card of the deck into the discard pile
    {
        deck.shuffle();

        for (int i = 0; i < 7; i++) // each player gets 7 cards
        {
            for (Player p : players)
            {
                p.draw();
            }
        }

        while (deck.get(deck.size() - 1).getColor() == Card.WILD) // the starting card can't be wild!
        {
            deck.shuffle();
        }

        deck.mill();
    }

    private static String determineWinner()
    {
        for (Player p : players)
        {
            if (p.hasEmptyHand())
            {
                return p.getName();
            }
        }
        return "";
    }

    public static boolean topCardSkip()
    {
        switch(deck.getTopDiscard().getNumber())
        {
            case 10:
            case 11:
            case 12:
            case 13:
                return true;
            default:
                return false;
        }
    }

    public static void repaint()
    {
        panel.repaint();
    }

    public static boolean isPlayerTurn()
    {
        return playerTurn;
    }
}
