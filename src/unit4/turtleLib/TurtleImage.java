package unit4.turtleLib;

import java.awt.*;
import java.awt.image.ImageProducer;
import java.io.IOException;

@SuppressWarnings("serial") 
class TurtleImage extends Component
{

    private Image images[];
    private static int numberOfTurtles;
    private double teta;
    private LogoPlane plane;
    private Color penColor;
    unit4.turtleLib.Point loc;
    
    public TurtleImage()
    {
        images = loadImages();
        numberOfTurtles++;
        setSize(40, 40);
        plane = LogoWindow.getDefaultWindow().getPlane();
        plane.addTurtle(this);
        setLocation(plane.getWidth()/2-20,plane.getHeight()/2-20);
        loc = new unit4.turtleLib.Point(getLocation());
        penColor = Color.black;
    }

    public void paint(Graphics gr)
    {
    	Graphics2D g = (Graphics2D) gr;
       
        Image image = getImageForAngle((int)((teta * 180D) / Math.PI));
        int x = (getSize().width - image.getWidth(this)) / 2;
        int y = (getSize().height - image.getHeight(this)) / 2;
       g.drawImage(image, x, y, this); 
    }

    public void setAngle(double d)
    {
        teta = d;
        repaint();
    }
    
    public void setPenColor(Color color)
    {
        this.penColor = color;
    }
    
    public void moveBy(double i, double j, boolean flag)
    {
        unit4.turtleLib.Point point1 = new unit4.turtleLib.Point(loc.x + getSize().width / 2.0, loc.y + getSize().height / 2.0);
        setLocation((int)Math.round(loc.x + i), (int)Math.round(loc.y +j));
        loc = new unit4.turtleLib.Point(loc.x + i,loc.y+j);
        unit4.turtleLib.Point point2 = new unit4.turtleLib.Point(loc.x + getSize().width / 2.0, loc.y + getSize().height / 2.0);
        if(flag)
        {
            plane.addLineSegment(point1, point2, penColor);
        }
        repaint();
    }
 
    protected Image getImageForAngle(int i)
    {
        i = (i % 360 + 360) % 360;
        int j = (int)((float)i / 22.5F);
        if(j > 15 || j < 0)
        {
            j = 0;
        }
        return images[j];
    }

    private Image[] loadImages()
    {
        byte byte0 = 16;
        Image aimage[] = new Image[byte0];
        for(int i = 0; i < byte0; i++)
        {
            String s = "turtle" + i + ".gif";
            try
            {
                ImageProducer imageproducer = (ImageProducer)(TurtleImage.class).getResource(s).getContent();
                aimage[i] = createImage(imageproducer);
            }
            catch(IOException ioexception) { }
        }

        return aimage;
    }
    
}
