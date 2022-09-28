package unit4.ioLib;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;

@SuppressWarnings("serial")
class MyCaret extends DefaultCaret 
{
	private Color curColor;
	
	public MyCaret(int blinkRate, Color color)
	{
		setBlinkRate(blinkRate); // default rate
		curColor = color;
	}
	
    public void paint(Graphics g) 
    {
       JTextComponent comp = getComponent();
       
       int dot = getDot();
       Rectangle r = null;
       char c;
      
       try {
          r = comp.modelToView(dot);
          if(r == null)
             return;
          c = comp.getText(dot, 1).charAt(0);
       } catch(BadLocationException e) {
            return;
       }
 
       // erase provious caret
       if((x != r.x) || (y != r.y)) {
           repaint();
           x = r.x;
           y = r.y;
           height = r.height;
       }
		
       g.setColor(this.curColor);
       g.setXORMode(comp.getBackground());
 
       width = g.getFontMetrics().charWidth(c);
       if(c == '\t' || c == '\n')
          width = g.getFontMetrics().charWidth(' ');
       if(isVisible()) {
         g.fillRect(r.x, r.y, width, r.height);
       }
   }
    
    public void setCursorColor(Color color)
    {
    	this.curColor = color;
    }
}


 
