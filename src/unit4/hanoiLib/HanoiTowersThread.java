package unit4.hanoiLib;

import java.awt.*;

import javax.swing.*; 	

import java.awt.event.*;

class HanoiTowersThread extends Thread implements Runnable
{
	HanoiTowersAnimation hta;
	JTextArea txtArea;
	private JFrame frame;
	
	public HanoiTowersThread(Object ht, boolean auto, int delay)
	{	 
			super();
			txtArea = new JTextArea();
			txtArea.setFont(new Font("Courier", 0, 14));
			txtArea.setText("\n\n\n\n\n\n");
			txtArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			txtArea.setEditable(false);
			txtArea.setBackground(Color.lightGray);
			
	  		hta = new HanoiTowersAnimation(auto,ht,txtArea);
	  		hta.init();
	  		//JPanel board = new JPanel();
	  		//board.add(hta);
	  		
	  		if (auto)
	  			frame = new JFrame("��� �������� - ����� ����");
	  		else
	  			frame = new JFrame("��� ���� - ����� ����");
	  		
			Box box = Box.createHorizontalBox();
	  		
			JScrollPane scroll = new JScrollPane(txtArea);
			scroll.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			box.add(scroll);
			JPanel view = new JPanel(new GridLayout(1,1));
	        view.add(scroll);
	        
	        
	        //frame.setLayout();
	    	frame.getContentPane().add(hta,BorderLayout.CENTER);
	    	frame.getContentPane().add(view,BorderLayout.SOUTH);
//	    	frame.pack();
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setSize(615, 420);
			//frame.setLocation(200,200);
			frame.setJMenuBar(createMenu()); 
			frame.setLocationRelativeTo(null);
			
			frame.setResizable(false);
			frame.setFocusable(true);
			frame.setAlwaysOnTop(true);
			//frame.pack();
			frame.setVisible(true);
			//frame.setIconImage(Utils.getHujiImage());
			hta.setAuto(auto);
	        hta.setAnimationDelay(delay);
	        if(auto)
	        {	
	        	String[] msg = {"����� ���� - ��� ��������", "������ ��������� ���� �� ������..."};
	        	showHebrewMessageDialog(frame,msg,"����� ����",1);
	        }
	        else
	        {	
	        	String[] msg = {"����� ���� - ��� ����", "������ ����� ��� ����� ������ ����� �����."};
	        	showHebrewMessageDialog(frame,msg,"����� ����",1);
	        }	
	  	}
	
	public void run()
	{
		hta.start();
	}
	

		private JMenuBar createMenu()
		{
			//Where the GUI is created:
			JMenuBar menuBar;
			
			//Create the menu bar.
			menuBar = new JMenuBar();
			menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			
//			//menuBar.add(Box.createHorizontalGlue());
//			if(hta.automatic)
//			{
//				JButton btnCopy = new JButton("���� ��������");
//				btnCopy.setFocusable(false);
//				ActionListener actionlistener = new ActionListener() 
//				{
//					public void actionPerformed(ActionEvent actionevent)
//					{
//						hta.start();
//					}
//				};
//				btnCopy.addActionListener(actionlistener);
//				menuBar.add(btnCopy);
//				
//				btnCopy = new JButton("���� �������� ����");
//				btnCopy.setFocusable(false);
//				actionlistener = new ActionListener() 
//				{
//					public void actionPerformed(ActionEvent actionevent)
//					{
//						//hta.stop();
//						hta.init();
//						hta.start();
//					}
//				};
//				btnCopy.addActionListener(actionlistener);
//				menuBar.add(btnCopy);
//			}
			//Build the first menu.
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
	            	String[] explain = {"����","\n","����� ���� �� ���� �������� ������ ����."};
	            	showHebrewMessageDialog(frame, explain, "����� ����" ,JOptionPane.INFORMATION_MESSAGE);
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
	            	showHebrewMessageDialog(frame ,about, "����� ����" ,JOptionPane.INFORMATION_MESSAGE);
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
