package unit4.ioLib;

import javax.swing.text.*; 
import java.awt.Toolkit;

@SuppressWarnings("serial")
class LimitedStyledDocument extends DefaultStyledDocument 
{
	private int pos;
	public boolean showRead;
	
    public LimitedStyledDocument() 
	{
    	pos = 0;
    	showRead = true;
    }

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException 
    {
//System.err.println("**"+str+","+offs+","+pos+"**");
		if (offs >= pos)
		{
			if(showRead)
			{
				super.insertString(offs, str, a);
				//System.err.println("**"+str+"**");
			}
		}
		else
		{
			Toolkit.getDefaultToolkit().beep();
//System.err.println("beep1");
		}
    }
    
    public void remove(int offs, int len) throws BadLocationException
    {
    	//System.err.println("off:"+offs+","+"len:"+len+","+pos);
    	if (offs >= pos) 
    		super.remove(offs, len);
    	else
    	{
    		Toolkit.getDefaultToolkit().beep();
//System.err.println("beep2");
    	}
    }
    
    public void setPos(int pos)
    {
    	this.pos = pos;
    }
}
