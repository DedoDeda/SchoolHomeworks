package unit4.hanoiLib;

import java.applet.Applet;
import java.awt.*;

class NoFlickers extends Applet
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 749861664477491318L;
	private Image offScreenImage;
    private Graphics offScreenGraphics;
    private Dimension offScreenSize;

    public final synchronized void update(Graphics g)
    {
        Dimension dimension = getSize();
        if(offScreenImage == null || dimension.width != offScreenSize.width || dimension.height != offScreenSize.height)
        {
            offScreenImage = createImage(dimension.width, dimension.height);
            offScreenSize = dimension;
            offScreenGraphics = offScreenImage.getGraphics();
            offScreenGraphics.setFont(getFont());
        }
        offScreenGraphics.fillRect(0, 0, dimension.width, dimension.height);
        paint(offScreenGraphics);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public NoFlickers()
    {
    }
}
