// To-do: write a compareTo so players can sort their hands

public class Card implements Comparable
{
    public static final int WILD = 0, RED = 1, GREEN = 2, BLUE = 3, YELLOW = 4;
    public static final int SKIP = 10, REVERSE = 11, DRAW2 = 12, DRAW4 = 13, CIRCLE = 14;

    private int number, color;
    private Deck thisDeck;

    public Card(int num, int c, Deck d)
    {
        if (num >= 0 && num <=14) // cards can only be numbered = through 14, if something else is given the card defaults to 0
            number = num;
        else
            number = 0;

        if (number == 13 || number == 14) // 13 and 14 represent +4 and wild cards, both of which are wild color
            color = WILD;
        else if (c >= 0 && c <= 4) // otherwise the color is chosen normally
            color = c;
        else // otherwise defaults to red
            color = RED;

        thisDeck = d;
    }

    public Card() // default constructor, creates a red 0
    {
        number = 0;
        color = RED;
    }

    public int getNumber()
    {
        return number;
    }

    public int getColor()
    {
        return color;
    }

    // for use when the player or the computer chooses a wild card color
    public void setColor(int c)
    {
        if (this.color == WILD)
        {
            color = c;
        }
    }

    public String toString()
    {
        String[] colorOutput = new String[] {"Wild", "Red", "Green", "Blue", "Yellow"};
        String[] numberOutput = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "Skip", "Reverse", "Draw 2", "Draw 4", "Wildcard"};
    return colorOutput[color] + " " + numberOutput[number];
    }

    @Override
    public int compareTo(Object o)
    {
        Card other = (Card)(o);
        if (this.getColor() != other.getColor())
        {
            return this.getColor() - other.getColor();
        }
        else
        {
            return this.getNumber() - other.getNumber();
        }
    }

    public boolean isPlayable()
    {
        if (color == WILD)
        {
            return true;
        }
        else if (thisDeck.getTopDiscard().getColor() == this.color || thisDeck.getTopDiscard().getNumber() == this.number || thisDeck.getTopDiscard().getColor() == WILD)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isSkip()
    {
        switch (getNumber())
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
}