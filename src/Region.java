public class Region
{
    private int x1, x2, y1, y2;
    public Region(int x1, int y1, int x2, int y2)
    {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public boolean inRegion(int mx, int my)
    {
        return (mx > x1 && mx < x2 && my > y1 && my < y2);
    }
}
