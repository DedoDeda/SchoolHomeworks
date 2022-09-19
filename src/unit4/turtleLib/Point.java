package unit4.turtleLib;


class Point
{

    double x;
    double y;

    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Point(java.awt.Point p)
    {
        x = p.x;
        y = p.y;
    }
}
