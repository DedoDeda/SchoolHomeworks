package unit4.turtleLib;

import java.awt.*;
import java.awt.event.*;

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

@SuppressWarnings("serial") 
class LogoWindow extends JFrame
{
    LogoPlane plane;
    private static LogoWindow defaultWindow;
    public JFrame frame;

    static 
    {
        defaultWindow = new LogoWindow();
        defaultWindow.setVisible(true);
    	String[] msg = {"������ ��������� ���� �� ������..."};
    	showHebrewMessageDialog(defaultWindow,msg,"���� �����",1);
    }
    
    public LogoWindow()
    {
    	
    	try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception exception1)
		{
			System.err.println("Error loading L&F: " + exception1);
		}	
    	frame = this;
    	//setIconImage(Utils.getHujiImage());
    	setJMenuBar(createMenu());
    	setTitle("���� �����");
    	setLocation(200,100);
        setLayout(new BorderLayout(3, 3));
        setSize(600, 500);
        plane = new LogoPlane();
        add(plane, "Center");
       
        addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent windowevent)
            {
                System.exit(0);
            }
        });
    }
    
    
    public LogoPlane getPlane()
    {
        return plane;
    }

    public static LogoWindow getDefaultWindow()
    {
        return defaultWindow;
    }
    
    private JMenuBar createMenu()
	{	
		//Create the menu bar.
		JMenuBar menuBar = new JMenuBar();
		menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);


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
            	String[] explain = {"����","\n","����� ���� ����� ������� ���� ����� �����."};
            	showHebrewMessageDialog(frame, explain, "���� �����" ,JOptionPane.INFORMATION_MESSAGE);		
            }
        });
        helpMenu.add(menuItem);
        menuItem = new JMenuItem("������ ������");
		menuItem.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuItem.setFont(new Font(menuItem.getFont().getFamily(), Font.PLAIN, menuItem.getFont().getSize()-1));
		menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionevent)
            {
            	String[] about = {"������ ������","\n",CENTER,TEAM,"\n","�����: ������ ������ ����� ���."};
            	showHebrewMessageDialog(frame ,about, "���� �����" ,JOptionPane.INFORMATION_MESSAGE);		
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
