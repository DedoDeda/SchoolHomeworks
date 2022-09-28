package unit4.bucketLib;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;



/**
 * <h3 dir="rtl" style="color:red; font-family: Helvetica, Arial, sans-serif">
 * ����� �� ������ ��� ���� 
 * </h3>
 *
 * @author ���� ���� �����, ����� ������ ������, ����������� ������, �������
 * @version 26.11.2007
 */
public class Bucket
{

	static final BucketSystem bs;
	int capacity;
    String name;
    double q;
    double quantity;
    Bucket pour;
    boolean empting;
    boolean filling;
    double fillAmount;
    static JFrame frame;
    
    static 
    {
    	try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception exception1)
		{
			System.err.println("Error loading L&F: " + exception1);
		}	
        bs = new BucketSystem(); 
        frame = new JFrame("���� ������");
        frame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent windowevent)
            {
                System.exit(0);
            }

            public void windowDeiconified(WindowEvent windowevent)
            {
                Bucket.bs.start();
            }

            public void windowIconified(WindowEvent windowevent)
            {
                Bucket.bs.stop();
            }

        });
        frame.setJMenuBar(createMenu());
        frame.add("Center", bs);
        frame.pack();
        frame.setSize(new Dimension(500, 300));
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    	String[] msg = {"������ ��������� ���� �� ������..."};
    	showHebrewMessageDialog(frame,msg,"���� ������",1);

        //frame.setIconImage(Utils.getHujiImage());
        ((Surface) (bs)).animating.start();
    }
    
	
	
    /** <dt dir="rtl"><b>
	 * ������ ���� ��� ��� �������� ���� ������� ������
	 *
	 * @param capacity ������ ����  
	 * @param name         �� ����  
	 */
    public Bucket(int capacity, String name)
    {
        q = 0;
        quantity = 0.0D;
        pour = null;
        empting = false;
        filling = false;
        this.capacity = capacity;
        this.name = name ;
        bs.addBucket(this);
    }

    
    /**  <dt dir="rtl"><b>
	 * ������ ������ �� ���� ���� ������ ���� ���� ������
	 * @return ���� ���� ������� ����
	 */
	public double getCurrentAmount()
	{
		return this.q;
	}
	
	/**  <dt dir="rtl"><b>
	 * ������ ������ �� ������� �� ���� ������
	 * @return ������ ���� ������
	 */
	public int getCapacity()
	{
		return this.capacity;
	}
	
	/**  <dt dir="rtl"><b>
	 * ������ ������ �� ���� ���� ��������� ������� 
	 * ����� ������ ���� ������ ������
	 * @param bucketInto ��� ���� ���� �� ����� �� ���� ����� ������
	 */
    public void pourInto(Bucket bucketInto)
    {
        double i = bucketInto.capacity - bucketInto.q;
        if(q > i)
        {
            q -= i;
            bucketInto.q += i;
        } else
        {
        	bucketInto.q += q;
            q = 0;
        }
        pour = bucketInto;
        bs.setAction(this, pour);
        while(pour != null) 
        {
            sleep(200);
        }
        
        sleep(200);
        quantity = q;
        bucketInto.quantity = bucketInto.q;
    }

    /**  <dt dir="rtl"><b>
	 * ������ ������ �� ���� ������ 
	 */
    public void empty()
    {
        q = 0;
        empting = true;
        while(empting) 
        {
            sleep(200);
        }
        sleep(200);
        quantity = 0.0D;
    }

    /**  <dt dir="rtl"><b>
	 * ������ ����� ������ ���� �� ��� ������ �� ���� ������ ����� ��. 
	 *   �� ���� ���� ��� ���� ������� ����, ���� ����� ����
	 *   ���� ������ ����� 
	 * @param amountToFill ���� ���� ��� �� ���� �� ����
	 */
    public void fill(double amountToFill)
    {
        filling = true;
        double left = capacity - q;
        
        if(amountToFill > left)
        {
            fillAmount = left;
        } else
        {
            fillAmount = amountToFill;
        }
        q += fillAmount;
        while(filling) 
        {
            sleep(200);
        }
        sleep(200);
        quantity = q;
    }

    /**  <dt dir="rtl"><b>
	 * ������ ����� �� ��� ����. �� ���� ������ ���, ������ '���'
	 * ��� �� ������ '���'
	 * @return ��� ������� '���' �� '���', ������ �� ��� ����
	 */
    public boolean isEmpty()
    {
        return q == 0;
    }

    static void sleep(int i)
    {
        try
        {
            Thread.currentThread();
            Thread.sleep(i);
        }
        catch(InterruptedException interruptedexception) { }
    }

    private static JMenuBar createMenu()
	{		
		//Create the menu bar.
		JMenuBar menuBar = new JMenuBar();
		menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		//Build the HELP menu.
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
            	String[] explain = {"����","\n","����� ���� ����� ����� �����."};
            	showHebrewMessageDialog(frame, explain, "���� ������" ,JOptionPane.INFORMATION_MESSAGE);		
            }
        });
        helpMenu.add(menuItem);
        menuItem = new JMenuItem("������ ������");
		menuItem.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuItem.setFont(new Font(menuItem.getFont().getFamily(), Font.PLAIN, menuItem.getFont().getSize()-1));
		menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionevent)
            {
            	String[] about = {"������ ������","\n",CENTER,TEAM,"\n","�����: ����� ��������."};
            	showHebrewMessageDialog(frame ,about, "���� ������" ,JOptionPane.INFORMATION_MESSAGE);		
            }
        });
        helpMenu.add(menuItem);
		menuBar.add(helpMenu,Component.LEFT_ALIGNMENT);

		return menuBar;
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
}
