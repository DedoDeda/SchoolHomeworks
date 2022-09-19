package unit4.ioLib;
/*
    Java I/O Window support Hebrew/English language.
    Copyright 2006 by Ilan Perets.
    work with java v1.5 and up.
*/

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;


/**
 * <h3 dir="rtl" style="color:red; font-family: Helvetica, Arial, sans-serif">
 * ����� �� ������ ��� ��� ��� �������� ���� ������ ������ ����� ����� ������ ��������
 * <br>���� �����: ����� �� ����� �� ����� �'���� 1.5 �����.  
 * </h3>
 *
 * @author ���� ���� �����, ����� ������ ������, ����������� ������, �������
 * @version 26.11.2007
 */
public final class IO 
{
	private static final String IO_TITLE = "���� ��� ���";
	private static final int WIDTH = 670;
	private static final int HEIGHT = 470;
	private static final int fontSize = 14;
	private static final String fontName = "Courier New"; //fixedsys
	private static Color bgColor = Color.WHITE; 	// Color.BLACK;
	private static Color txtColor = Color.BLACK;	// Color.YELLOW;
    private static boolean finishRead;
    private static boolean readOneKey;
    private static boolean readOneKeyCode;
    private static boolean isLTR;
    private static int lastKeyCodeReaded;
    private static TextArea area;
    private static JFrame frame;
    private static JComboBox cbSize;
    private static int startPos;
    private static int currentPos;
    private static String userInput;
    private static JTextField status;
    private static JPanel panel;
    private static JButton btnDir;
    private static JButton btnCopy;
    private static JButton btnSave;
    private static int selectedFontSize;
    private static JFileChooser fc;
    //private static boolean isIOWINopen;
    
    /***** Private Methods ************************************************************/
    // private the empty constructor to prevent instance 
    private IO(){}
   
    static 
    {      
    	
     	try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception exception1)
        {
        	try
        	{
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            }
        	catch(Exception exception2)
        	{
        		System.err.println("Error loading L&F: " + exception2);
        	}
        }
        
    	area = new TextArea(new Dimension(WIDTH,HEIGHT),fontSize,fontName,bgColor,txtColor);    
        readOneKey = false;
		selectedFontSize = fontSize;
       	frame = new JFrame(IO_TITLE);
        //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

       
        frame.setSize(WIDTH, HEIGHT);
        frame.getContentPane().setLayout(new java.awt.BorderLayout());
        frame.setJMenuBar(createMenu());
        frame.setLocationRelativeTo(null);
        //frame.setIconImage(Utils.getHujiImage());        
        frame.setAlwaysOnTop(true);
        frame.getContentPane().add(area, java.awt.BorderLayout.CENTER);
        
        // create status panel
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        status = new JTextField("");
        panel.add(status);
       
        frame.getContentPane().add(panel, java.awt.BorderLayout.SOUTH);
        //frame.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        frame.setVisible(true);
        
        status.setEditable(false);
        status.setFont(new Font(status.getFont().getFamily(),1,status.getFont().getSize()));
        
        
        
        // add keyboard listener
        area.getScreen().addKeyListener(new KeyAdapter()
        {
        	 public void keyPressed(KeyEvent e) 
        	 {
        		 	area.print("",txtColor,false);
        		 	area.getScreen().grabFocus();
        	    	//System.err.println("MyKeyListener.keyPressed: "+e.getKeyCode()+","+e.getKeyChar()+","+KeyEvent.getKeyText(e.getKeyCode()));
        		 	lastKeyCodeReaded = e.getKeyCode();
        		 	if(readOneKeyCode)
        		 	{
        		 		finishRead = true;
        		 		return;
        		 	}
        	    	switch(e.getKeyCode())
        	    	{
        	    		case KeyEvent.VK_ENTER: 
        	    			area.getScreen().getCaret().setDot(area.getScreen().getDocument().getLength());
        	    			finishRead = true; 
        	    			break;
        	    	}
        	  }
        });
        
        //add document listener
        area.getScreen().getDocument().addDocumentListener(new DocumentListener()
        {
        	  public void insertUpdate(DocumentEvent e) 
              {
        		  currentPos = e.getDocument().getLength();
              }
              public void removeUpdate(DocumentEvent e) 
              {
              }
              public void changedUpdate(DocumentEvent e)
              {
              }
        });
        
        
        
        // add caret listener
        area.getScreen().addCaretListener(new CaretListener()
        {
            public void caretUpdate(CaretEvent e) 
            {
            	int endPos = area.getScreen().getDocument().getLength(); 
            	
            	try 
            	{
            		if(readOneKey)
            		{
            			userInput = new String(area.getScreen().getText(startPos,endPos-startPos));
            			if(userInput.length() == 1)
            				finishRead = true;
            		}
            		else
            			userInput = new String(area.getScreen().getText(startPos,endPos-startPos-1));
				} 
            	catch (BadLocationException e1) 
            	{			
            		
				}
				
				if(e.getDot() < startPos)
            	{
            		area.getScreen().getCaret().setDot(endPos);
            	}	
            }
        });
    
        //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() 
               	{
               	    public void windowClosing(WindowEvent winEvt) 
               	    {
               	        //close();
               	    	Runtime.getRuntime().exit(0);
               	    }
               	});
        isLTR=true;
        fc = null;
        clear();
    }

	private static final String CENTER = "����� ������ ������ �� ����������� ������ �������� 2007.";
	private static final String TEAM = "���� ������: ����' ������ ����, ���� �����, ��� ��������, ���� ���, ��� ��������, ��� ���� ����� ����.";

	private static  void showHebrewMessageDialog(Component cmp, String[] msg, String title, int msgType) 
	{
		JPanel area = new JPanel(new GridLayout(msg.length,1));
		JLabel[] labels = new JLabel[msg.length];
		
		for(int i=0; i<msg.length; i++)
		{
			labels[i] = new JLabel(msg[i]);
			area.add(labels[i]);
			labels[i].setHorizontalAlignment(JTextField.RIGHT);
			if(i==0)
				labels[i].setFont(new Font(labels[i].getFont().getFamily(), Font.BOLD, labels[i].getFont().getSize()));
			else
				labels[i].setFont(new Font(labels[i].getFont().getFamily(), Font.PLAIN,  labels[i].getFont().getSize()));			
		}
    	
    	JOptionPane.showMessageDialog(cmp,area,title,msgType);
	}
	
    // Read a string from the text field.
    // Wait for event handler to set read to true.
    private static String read()
    {
		area.print("",txtColor,false);
		userInput = "";
		startPos = currentPos;
		//System.err.println(startPos);
		finishRead = false;
		area.setLimitedPos(startPos);
		area.getScreen().setFocusable(true);
		area.getScreen().grabFocus();
		if(isLTR)
		{
			status.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			status.setText("Wait for input...");
		}
		else
		{
			status.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			status.setText("����� ����...");
		}
		//area.setShowRead(true);	
		
		while(!finishRead) 
			area.getScreen().grabFocus();
		
		
		//sarea.setShowRead(false);
		status.setText("");
		area.getScreen().setFocusable(false);
		area.grabFocus();
		
		return(userInput);
    }
    	
    // Append a string to the text area
    private static void append(String s)
    {
        area.print(s);
    }
    
    
    /***** Public Methods ************************************************************/
   
    /** 
	 * <dt dir="rtl">
     * ���� ��� ��� ���
     */
    public static void clear()
    {
        area.clearScreen();
        if(isLTR)
        	english();
        else
        	hebrew();
    }
    
    /** <dt dir="rtl">
     * ���� �� ��� ���� ���
     * @param autoClose ����� ���������: �� ���� ��� ����� ����� ���� ����� �����, ���� ����� ����� ����
     */
    public static void close(boolean... autoClose) 
    {
    	if(autoClose.length!=0)
		{
    		if(autoClose[0])
    			if(isLTR)
    				pause("Press any key to close IO window...");
    			else
    				pause("��� �� ��� ����� ������ ���� ���� ���...");
		}
        frame.dispose();
    }

    /** <dt dir="rtl">
     * ���� ������ ������ ������
     */
    public static void hebrew()
    {
    	btnDir.setText("���� ���� �����");
    	isLTR=false;
    	area.setRTL();
    }
    
    /** <dt dir="rtl">
     * ���� ������ ������ �������
     */
    public static void english()
    {
    	btnDir.setText("���� ���� �����");
    	isLTR=true;
    	area.setLTR();
    }
    
    /** <dt dir="rtl">
     * ����� ��� ����
     * 
     * @param str ����� ����������: ������ ������ ���� ����
     * @return ����� �� ��� ���� �����
     */
	public static int readKey(String... str)
	{
		printStr(str);
		readOneKeyCode = true;
		area.setShowRead(false);
		read();
		area.setShowRead(true);
		readOneKeyCode = false;
		return(lastKeyCodeReaded);
	}
	
    /** <dt dir="rtl">
     * ����� ������
     * 
     * @param str ����� ����������: ������ ������ ���� ���� 
     * @return Enter ������� ������� ���� ���� 
     */
	public static String readString(String... str)   
    { 
		printStr(str);
    	String s = read();
    	return s; 
    }

	/** <dt dir="rtl">
	 * ����� �� ����
	 * 
     * @param str ����� ����������: ������ ������ ���� ���� 
	 * @return ��� ������ 
	 */
	public static char readChar(String... str) 
    {	
		printStr(str);
        String s;
        while (true) 
        {
        	readOneKey = true;
            s = read();
            if ((s != null) && (s.length() == 1))
            {
            	readOneKey = false;
            	return s.charAt(0); 
            }
        }
    }
    
	/** <dt dir="rtl">
	 * ����� ��� �������
	 * 
     * @param str ����� ����������: ������ ������ ���� ���� 
	 * @return ���� �������� ������
	 */
	public static boolean readBoolean(String... str) 
	{ 
		printStr(str);
        String s;
        while (true) 
        {
            s = read().trim();
            if (s.equals("true")) 
            {
            	return true;
            }
            else 
            	if (s.equals("false")) 
            		return false;
            	else
            	{ 
            		//area.print("^ ",errColor);
                	if(!isLTR)
                	{
                		area.println(">>> ��� ���� - ��� ������� �� ����!",txtColor,true);
                		area.print(">>> ��� ���: ",txtColor,true);
                	}
                	else
                	{
                		area.println(">>> Error input - invalid boolean value!",txtColor,true);
                		area.print(">>> Try again: ",txtColor,true);          		
                	}
            	}
        }
    }

	/** <dt dir="rtl">
	 * ����� ���� ���
	 * 
     * @param str ����� ����������: ������ ������ ���� ���� 
	 * @return ����� ���� ������
	 */
	public static int readInt(String... str)
    { 
		printStr(str);
        String s;
        while (true) 
        {
            s = read();
            try 
            { 
            	int x = Integer.parseInt(s); 
            	return x;
            } 
            catch(NumberFormatException exception) 
            { 
            	//area.setFont(new Font(fontName,Font.ITALIC,fontSize));
            	if(!isLTR)
            	{
            		area.println(">>> ��� ���� - ���� ��� �� ����!",txtColor,true);
            		area.print(">>> ��� ���: ",txtColor,true);
            	}
            	else
            	{
            		area.println(">>> Error input - invalid int value!",txtColor,true);
            		area.print(">>> Try again: ",txtColor,true);          		
            	}
            	//area.setFont(new Font(fontName,Font.PLAIN,fontSize));
            }
        }
    }
    
    /** <dt dir="rtl">
     * ����� ���� ��� ����
     * 
     * @param str ����� ����������: ������ ������ ���� ���� 
     * @return ����� ���� ����� ������
     */
	public static long readLong(String... str) 
    { 
		printStr(str);
        String s;
        while (true) 
        {
            s = read();
            try 
            { 
            	long x = Long.parseLong(s); 
            	return x;
            } 
            catch(NumberFormatException exception) 
            { 
            	//area.print("^ ",txtColor);
            	if(!isLTR)
            	{
            		area.println(">>> ��� ���� - ���� ��� ���� �� ����!",txtColor,true);
            		area.print(">>> ��� ���: ",txtColor,true);
            	}
            	else
            	{
            		area.println(">>> Error input - invalid long value!",txtColor,true);
            		area.print(">>> Try again: ",txtColor,true);          		
            	}
            }
        }
    }
    
	/** <dt dir="rtl">
	 * ����� ���� ����
	 *  
     * @param str ����� ����������: ������ ������ ���� ���� 
	 * @return ����� ����� ������
	 */
    public static float readFloat(String... str) 
    { 
    	printStr(str);
        String s;
        while (true) 
        {
            s = read();
            try 
            { 
            	float x = Float.parseFloat(s);
            	return x;
            } 
            catch(NumberFormatException exception) 
            { 
            	//area.print("^ ",txtColor);
            	if(!isLTR)
            	{
            		area.println(">>> ��� ���� - ���� ������ �� ����!",txtColor,true);
            		area.print(">>> ��� ���: ",txtColor,true);
            	}
            	else
            	{
            		area.println(">>> Error input - invalid float value!",txtColor,true);
            		area.print(">>> Try again: ",txtColor,true);          		
            	}
            }
        }
    }

    /** <dt dir="rtl"> 
     * ����� ���� ���� ����
     * 
     * @param str ����� ����������: ������ ������ ���� ���� 
     * @return ����� ����� ����� ������
     */
    public static double readDouble(String... str) 
    { 
    	printStr(str);
        String s;
        while (true) 
        {
            s = read();
            try 
            { 
            	double x = Double.parseDouble(s); 
            	return x;
            } 
            catch(NumberFormatException exception) 
            { 
            	//area.print("^ ",txtColor);
            	if(!isLTR)
            	{
            		area.println(">>> ��� ���� - ���� ������ ���� �� ����!",txtColor,true);
            		area.print(">>> ��� ���: ",txtColor,true);
            	}
            	else
            	{
            		area.println(">>> Error input - invalid double value!",txtColor,true);
            		area.print(">>> Try again: ",txtColor,true);          		
            	}
            } 
        }
    }
    
    /** <dt dir="rtl">
     * ����� ������� ������ ����� ��� �����
     * 
     * @param msg ����� ����������: ������ ������ ���� ������ 
     */
	public static void pause(String... msg)
	{
		if(msg.length==0)
		{
			if(isLTR)
				area.print("Press any key to continue...");
			else
				area.print("��� �� ��� ����� �����...");
		}
		else
			printStr(msg);
		readKey();
	}
	
    /** <dt dir="rtl">
     * ����� ����� ������ �� ��� ��
     * 
     * @param format ������ �������
     * @param args ������ ������
     */
    public static  void printf(String format, Object... args) 
    { 
    	PrintStream backupOut = System.out;
    	
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(baos, true));
    	try
    	{
    		System.out.printf(format,args);
    	}
    	catch (Exception e) 
    	{
    		if(!isLTR)
        	{
        		area.println(">>> ������ ������� printf �����!",txtColor,true);
        	}
        	else
        	{
        		area.println(">>> Print using printf failed!",txtColor,true);          		
        	}
		}
    	System.setOut(backupOut);
    	append(""+String.valueOf(baos)); 
    }
    
    /** <dt dir="rtl">
     * ����� ��� �� ����� �����
     * 
     * @param val ����� ������
     */
    public static  void print(Object val) { append(""+String.valueOf(val)); }
   
    /** <dt dir="rtl">
     * ����� ��� �� ����� ����� ����� ����� ����
     * 
     * @param val ����� ������
     */
    public static  void println(Object val) { append(""+String.valueOf(val)+'\n'); }
    
    /** <dt dir="rtl">
     * ���� ����� ����
     */
    public static  void println() { append(""+'\n'); }
    
    /** <dt dir="rtl">
     * ����� ������� ����� �� ��� ��� ��� ���� ���
     * 
     * @param color ��� ��� ����
     */
    public static void setBackGroundColor(Color color)
    {
    	bgColor = new Color(color.getRGB());
    	area.getScreen().setBackground(bgColor);
    }
    
    
    /** <dt dir="rtl">
     * ����� ������� ����� �� ��� ���� ����
     * 
     * @param color ��� ���� ����
     */
    public static void setTextColor(Color color)
    {
    	txtColor = new Color(color.getRGB());
    	area.setTextColor(txtColor);
    }
    
    
    /** <dt dir="rtl">
     * ����� ������� ������ ���� ��� �����
     * 
     * @param milliSeconds ������ �����-�����
     */
    public static void delay(long milliSeconds)
    {
    	if(isLTR)
		{
			status.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			status.setText("In delay...");
		}
		else
		{
			status.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			status.setText("���� ������...");
		}
    	
    	try
    	{
    		Thread.sleep(milliSeconds);
    	}
    	catch (Exception e) {
		}
    	
    	status.setText("");
    }
    
    /** <dt dir="rtl">
     * ����� ������� ����� ���� ������� ������ ������
     * 
     * @param frequency ������ �����
     * @param duration ��� ���� ������ ����� �����-�����
     */
    public static void sound(int frequency, int duration)
    {
    	sound(frequency,duration,1.0);
    }  
 
    
    /** <dt dir="rtl">
     * ������ ������ ������ ������� �� ���� ������� ��� ���� ������� �����
     *  ����� �����:����:���� 
     */
    public static String getCurrentTime()
    {    
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        
        return(formatter.format(new Date()));
    }
    
    /** <dt dir="rtl">
     * ������ ������ ������ ������� �� ������ ������ ��� ���� ����� �����
     *  ������ ���/����/���
     */
    public static String getCurrentDate()
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        
        return(sdf.format(new Date()));
    }
    
    ////////// private methods --------------------------------------------
   
    private static void sound(int hz, int msecs, double vol)
	{	
		byte[] buf = new byte[msecs * 8];
		
		for (int i=0; i<buf.length; i++) 
		{
			double angle = i / (8000.0 / hz) * 2.0 * Math.PI;
			buf[i] = (byte)(Math.sin(angle) * 127.0 * vol);
		}
		
		//	shape the front and back ends of the wave form
		for (int i=0; i<20 && i < buf.length / 2; i++) 
		{
			buf[i] = (byte)(buf[i] * i / 20);
			buf[buf.length - 1 - i] = (byte)(buf[buf.length - 1 - i] * i / 20);
		}
		
		AudioFormat af = new AudioFormat(8000f,8,1,true,false);
		try
		{
			SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
			sdl.open(af);
			sdl.start();
			sdl.write(buf,0,buf.length);
			sdl.drain();
			sdl.close();
		}
		catch (Exception e) {
			System.err.println("Error from IO: problem with sound card!");
		}
	}
    
    private static void printStr(String... str)
    {
    	for(String s : str)
			print(s);
    }
    
	private static JMenuBar createMenu()
	{	
		//Create the menu bar.
		JMenuBar menuBar = new JMenuBar();
		menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	        
		 JLabel lbl = new JLabel("  ���� ����: ");
	     //lbl.setFont(new Font("Courier", Font.BOLD, 15));
	     menuBar.add(lbl);
					
	     String[] fontSizes = (new String[] {
	    		 "8", "9", "10", "11", "12", "14", "16", "18", "20", "22", 
	    		 "24", "26", "28", "36", "48", "72"
	     });
	     cbSize = new JComboBox(fontSizes);
	     cbSize.setMaximumSize(new Dimension(50, 21));
	     cbSize.setEditable(true);
	     cbSize.setSelectedItem(String.valueOf(fontSize));
	     cbSize.setEditable(false);
	     cbSize.setFocusable(false);
	     cbSize.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	     ActionListener actionlistener = new ActionListener() 
	     {
	    	 public void actionPerformed(ActionEvent actionevent)
	    	 {
	    		 selectedFontSize = 0;
	    		 try
	    		 {
	    			 selectedFontSize = Integer.parseInt(cbSize.getSelectedItem().toString());
	    		 }
	    		 catch(NumberFormatException numberformatexception)
	    		 {
	    			 return;
	    		 }
	    		 area.setFontSize(selectedFontSize);
	    	 }
	    	 
	     };
	     cbSize.addActionListener(actionlistener);
	     menuBar.add(cbSize);	
	        
	    JLabel lbl2 = new JLabel("   ");
	    menuBar.add(lbl2);
	     
		btnSave = new JButton("���� ����");
		btnSave.setFocusable(false);
        actionlistener = new ActionListener() 
        {
                public void actionPerformed(ActionEvent actionevent)
                {
                	saveFile();
                }
        };
        btnSave.addActionListener(actionlistener);
        menuBar.add(btnSave);
       
        JLabel lbl3 = new JLabel("   ");
	    menuBar.add(lbl3);
	     
        btnCopy = new JButton("���� ����");
        btnCopy.setFocusable(false);
        actionlistener = new ActionListener() 
        {
                public void actionPerformed(ActionEvent actionevent)
                {
                	        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
                	        String s = area.getDocAsHTML();
                	        StringSelection contents =  new StringSelection(s);
                	        cb.setContents(contents, null);
                }
        };
        btnCopy.addActionListener(actionlistener);
        menuBar.add(btnCopy);
        
        JLabel lbl4 = new JLabel("   ");
	    menuBar.add(lbl4);
	    
        btnDir = new JButton("���� ���� �����");
        btnDir.setFocusable(false);
        actionlistener = new ActionListener() 
        {
                public void actionPerformed(ActionEvent actionevent)
                {
                	if (btnDir.getText().equals("���� ���� �����"))
                	{
                		area.RTL(); 
                		btnDir.setText("���� ���� �����");
                	}
                	else
                		if (btnDir.getText().equals("���� ���� �����"))
                		{
                    		area.LTR();
                    		btnDir.setText("���� ���� �����");
                		}
                }

            };
        btnDir.addActionListener(actionlistener);
        menuBar.add(btnDir);
        

		// Build the HELP menu.
		menuBar.add(Box.createHorizontalGlue());
		JMenu helpMenu = new JMenu("�����");
		helpMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		helpMenu.setFont(new Font(helpMenu.getFont().getFamily(), Font.BOLD, helpMenu.getFont().getSize()-1));
		
		JMenuItem menuItem = new JMenuItem("����");
		menuItem.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuItem.setFont(new Font(menuItem.getFont().getFamily(), Font.PLAIN, menuItem.getFont().getSize()-1));
		menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionevent)
            {
            	String[] explain = {"����","\n","������� ��� ��� ��� ���� ������ ������ ����� ����� ������ ��������."};
            	showHebrewMessageDialog(frame, explain, IO_TITLE ,JOptionPane.INFORMATION_MESSAGE);
            	area.grabFocus();
            	area.getScreen().grabFocus();
            }
        });
        helpMenu.add(menuItem);
        menuItem = new JMenuItem("������ ������");
		menuItem.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuItem.setFont(new Font(menuItem.getFont().getFamily(), Font.PLAIN, menuItem.getFont().getSize()-1));
		menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionevent)
            {
            	String[] about = {"������ ������","\n",CENTER,TEAM,"\n","�����: ���� ���."};
            	showHebrewMessageDialog(frame ,about, IO_TITLE ,JOptionPane.INFORMATION_MESSAGE);
            	area.grabFocus();
            	area.getScreen().grabFocus();
            }
        });
        helpMenu.add(menuItem);
		menuBar.add(helpMenu,Component.LEFT_ALIGNMENT);
        
		return menuBar;
	}
    
	
	private static void saveFile()
	{
		if(fc==null)
			fc = new JFileChooser();
		
		int returnVal = fc.showSaveDialog(area);
        if (returnVal == JFileChooser.APPROVE_OPTION) 
        {
            File file = fc.getSelectedFile();
            try
    		{
            	if(!file.getName().endsWith(".html"))
            		file = new File(fc.getSelectedFile()+".html");
    			FileOutputStream fn = new FileOutputStream(file);
    			if (!btnDir.getText().equals("���� ���� �����"))
    				fn.write("<HTML dir='rtl'>".getBytes());
    			String str = "<BODY><XMP style='FONT-FAMILY: fixedsys; FONT-SIZE: " + 14 +"px;'>";
    			fn.write(str.getBytes());
    			fn.write(area.getDocAsHTML().getBytes());
    			fn.write("</XMP></BODY></HTML>".getBytes());
    			fn.close();
    		}
    		catch(Exception exception)
    		{
    			String[] msgs = {"���� ������ �����!"};
    			showHebrewMessageDialog(area,msgs, IO_TITLE ,JOptionPane.INFORMATION_MESSAGE);		        
    		} 
        }
        else 
            return;
	}	 
}

