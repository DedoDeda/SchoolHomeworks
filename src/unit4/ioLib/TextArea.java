package unit4.ioLib;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


@SuppressWarnings("serial")
class TextArea extends JPanel 
{
	private JTextPane screen;
	private JScrollPane scroll;
	private SimpleAttributeSet sas;
	private SimpleAttributeSet s;
	private int fontSize;
	private LimitedStyledDocument lpd;
	private Color textColor;
	private Color bgColor;
	private MyCaret myCur;
	
	public TextArea(Dimension dim, int fontSize, String fontName, Color bg, Color txt)
	{
		textColor = txt;
		bgColor = bg;
		
		setLayout(new BorderLayout());
		sas = new SimpleAttributeSet();
		s = new SimpleAttributeSet();
        //Create the document for the text area
        lpd = new LimitedStyledDocument();
        
        //Create the text pane and configure it       
		screen = new JTextPane();
		screen.setDocument(lpd);
		myCur = new MyCaret(500,txt);
        screen.setCaret(myCur);
        screen.setBackground(bgColor);

		screen.setEditable(true);
		screen.setEnabled(true);
		screen.setFocusable(false);
		screen.setPreferredSize(dim);
		//screen.setContentType("text/html");
		screen.setMargin(new Insets(4,4,4,4));
		screen.setMinimumSize(new Dimension(300, 200));
		screen.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scroll = new JScrollPane(screen);
		scroll.setViewportView(screen);
		this.fontSize = fontSize;
		screen.setFont(new Font(fontName,0,fontSize));
		this.add(scroll);
		
		// The next two lines set the System.out to JTextArea
		//Document doc = screen.getDocument();
    	//System.setOut(new PrintStream(new DocumentOutputStream(doc)));
	}
	
	public void setFontFamily(String fontName)
	{
		DefaultStyledDocument doc = (DefaultStyledDocument)screen.getDocument();
		int s = doc.getStartPosition().getOffset();
		int e = doc.getEndPosition().getOffset();
		SimpleAttributeSet attrib = new SimpleAttributeSet();
		StyleConstants.setFontFamily(attrib,fontName);
		doc.setCharacterAttributes(s, e-s, attrib, false);
	}
	
	public void setFontSize(int fontSize)
	{
		DefaultStyledDocument doc = (DefaultStyledDocument)screen.getDocument();
		int s = doc.getStartPosition().getOffset();
		int e = doc.getEndPosition().getOffset();
		SimpleAttributeSet attrib = new SimpleAttributeSet();
		StyleConstants.setFontSize(attrib,fontSize);
		doc.setCharacterAttributes(s, e-s, attrib, false);
		this.fontSize = fontSize;
	}
	
	
	public void setRTL()
	{
		if(screen.getDocument().getLength() != 0)
			println("");
		StyleConstants.setAlignment(sas, StyleConstants.ALIGN_RIGHT);
        screen.setParagraphAttributes(sas, false);
        screen.repaint();
        RTL();
	}
	
	public void setLTR()
	{
		if(screen.getDocument().getLength() != 0)
			println("");
		StyleConstants.setAlignment(sas, StyleConstants.ALIGN_LEFT);
        screen.setParagraphAttributes(sas, false);
        screen.repaint();
        LTR();
	}
	
	public void LTR()
	{
		screen.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	}
	
	public void RTL()
	{
		screen.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	}
		
	public void print(String msg)
	{
		print(msg,textColor,false);
	}
	
	public void print(String msg,Color color,boolean err)
	{
		StyleConstants.setForeground(s, color);
		StyleConstants.setFontSize(s,fontSize);
		//StyleConstants.setFontFamily(s,"fixedsys");
		StyleConstants.setItalic(s, err);
		StyleConstants.setBold(s, !err);
		screen.setCharacterAttributes(s, false);
		screen.setCaretPosition(screen.getStyledDocument().getLength());
		screen.replaceSelection(msg);	
		screen.setCaretPosition(screen.getStyledDocument().getLength());
	}
	
	public void println(String msg)
	{ 
		println(msg,textColor,false);
	}
	
	public void println(String msg,Color color,boolean err)
	{
		StyleConstants.setForeground(s, color);
		StyleConstants.setFontSize(s,fontSize);
		//StyleConstants.setFontFamily(s,"fixedsys");
		StyleConstants.setItalic(s,err);
		StyleConstants.setBold(s,!err);
		screen.setCharacterAttributes(s, false);
		screen.setCaretPosition(screen.getStyledDocument().getLength());
		screen.replaceSelection(msg+"\n");	
		screen.setCaretPosition(screen.getStyledDocument().getLength());
	}
	
	public void clearScreen()
	{
		setLimitedPos(0);
		screen.selectAll();
		screen.setText("");
	}
	
	public JTextPane getScreen()
	{
		return(screen);
	}
	
	public String getDocAsHTML()
	{
		return(screen.getText().toString());
	}
	
	public void setLimitedPos(int pos)
	{
	   	lpd.setPos(pos);
	}
	
	public void setShowRead(boolean status)
	{
	   	lpd.showRead = status;
	}
	
	public void setCaretVisible(boolean status)
	{
	   	screen.getCaret().setVisible(status);
	}

    public void setTextColor(Color color)
    {
    	textColor = color;
    	this.myCur.setCursorColor(color);
    	
    }
    
    public void setCursorColor(Color color)
    {
    	this.myCur.setCursorColor(color);
    }
}

