package unit4.turtleLib;

import java.awt.Color;
//import java.awt.Point;
import java.io.Serializable;

@SuppressWarnings("serial") 
class LineSegment implements Serializable
{

    public Point from;
    public Point till;
    public Color color;

    public LineSegment(Point point, Point point1, Color color1)
    {
        from = point;
        till = point1;
        color = color1;
    }

    public LineSegment(Point point, Point point1)
    {
        this(point, point1, Color.black);
    }
    
    public String toString()
    {
    	return(color.toString());
    }
}
