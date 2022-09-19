package unit4.turtleLib;

import java.awt.Point;
import java.util.*;
import java.awt.*;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
class LogoPlane extends JPanel
{

    private Vector<LineSegment> drawing;
    
    public LogoPlane()
    {
    	setDoubleBuffered(true);
    	setBackground(Color.WHITE);
        drawing = new Vector<LineSegment>();
        setLayout(null);
    }

    public void addLineSegment(java.awt.Point point, java.awt.Point point1, Color color)
    {
        drawing.addElement(new LineSegment(point, point1, color));
        repaint();
    }

    public void addLineSegment(java.awt.Point point, Point point1)
    {
        addLineSegment(point, point1, Color.black);
    }

    public void paintComponent(Graphics gr) 
    {
        super.paintComponent(gr);
		Graphics2D g = (Graphics2D) gr;
      	//g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      	
        for(int i = 0; i < drawing.size(); i++)
        {
            LineSegment linesegment = drawing.elementAt(i);
            g.setColor(linesegment.color);
            //g.drawLine(linesegment.from.x, linesegment.from.y, linesegment.till.x, linesegment.till.y);
            g.draw(new Line2D.Double(linesegment.from.x, linesegment.from.y, linesegment.till.x, linesegment.till.y));
        }
    }

    public void addTurtle(TurtleImage turtleimage)
    {
        turtleimage.setLocation(getSize().width / 2, getSize().height - 40);
        add(turtleimage);
    }
}
