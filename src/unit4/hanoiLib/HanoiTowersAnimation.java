package unit4.hanoiLib;

import java.awt.*;
import javax.swing.JTextArea;

@SuppressWarnings("serial") 
class HanoiTowersAnimation extends NoFlickers implements Runnable
{ 
		int p;
		int n;
		int moves;
	    int speed;
	    int disk_width;
	    int disk_height;
	    int draw_y;
	    int p_x;
	    int p_diff;
	    int p_width;
	    int p_unit_height;
	    int start_pole;
	    int dest_pole;
	    StringBuffer information;
	    HanoiPole poles[];
	    boolean first_time;
	    boolean job_done;
	    boolean automatic;
	    boolean next_step;
	    HanoiDisk movingDisk;
	    Color movingDiskColor;
	    Color diskColor;
	    Object HT;
	    JTextArea TA;
	     
	    public HanoiTowersAnimation(boolean flag, Object ht, JTextArea ta)
	    {
	    	HT = ht;
	    	TA = ta;
	    	moves=0;
	    	p = 3;
	        n = (Integer)invoke("getNumOfDiscs", null,null);
	        speed = 1000;
	        disk_width = 10;
	        disk_height = 15;
	        draw_y = 220;
	        p_x = 100;
	        p_diff = 200;
	        p_width = 20;
	        p_unit_height = 18;
	        start_pole = -1;
	        dest_pole = -1;
	        information = new StringBuffer(" ");
	        poles = new HanoiPole[p];
	        first_time = true;
	        job_done = false;
	        next_step = false;
	        movingDiskColor = Color.yellow;
	        diskColor = Color.green;
	        automatic = flag;
	        setBackground(Color.white);
	    }

	    public void setAnimationDelay(int delay)
	    {
	    	if(delay<0 || delay>10000)
	    		this.speed = 500;
	    	else
	    		this.speed = delay;
	    }
	    
	    public void setAuto(boolean auto)
	    {
	    	this.automatic = auto;
	    }
	   
	    
	    void Hanoi(int i, String s, String s1, String s2)
	    {
	    	int j,k;
	        HanoiDisk hanoidisk = null;
	        if(i > 1)
	        {
	            Hanoi(i - 1, s, s2, s1);
	        }
	        if(i >= 1)
	        {
	            boolean flag = false;
	            for(j = 0; j < p && !flag; j++)
	            {
	                if(poles[j].nameEquals(s))
	                {
	                    hanoidisk = poles[j].moveOutDisk();
	                    flag = true;
	                }
	            }

	            if(!flag)
	            {
	            	System.out.println("\nError in Hanoi when moving disk out.\n");
		        	System.exit(1);
	            }
	            flag = false;
	            for(k = 0; k < p && !flag; k++)
	            {
	                if(poles[k].nameEquals(s2))
	                {
	                    poles[k].moveInDisk(hanoidisk);
	                    flag = true;
	                }
	            }

	            if(!flag)
	            {
	            	System.out.println("\nError in Hanoi when moving disc in.\n");
		        	System.exit(1);
	            }
	            String s3;
	            if(hanoidisk != null)
	            {
	            	moves++;
	                //s3 = moves + ") Move disc from " + s + " pole to " + s2 + " pole.";
	                s3 = moves + ") " + "����� ���� ���� " + s + " ���� " + s2 + ".";
	                ///// ilan
	                boolean res = (Boolean)invoke("moveDisc",new Class[] {int.class, int.class},new Object[] {new Integer(j), new Integer(k)});
	                if(res != true)
	                {
	                	System.err.println("\n>>> Error in HanoiTowers.java - moveDisc failed.\n");
	    	        	System.exit(1);
	    	        }
	                int x = (Integer)invoke("getSizeTopDisc",new Class[] {int.class},new Object[] {new Integer(k)});
	                if(x != hanoidisk.number())
	                {
	                	System.err.println("\n>>> Error(4) in HanoiTowers.java\n");
	    	        	System.exit(1);
	    	        }	
	                ///// ilan
	                
	                if(poles[2].getNumberDisk() == n)
	                {
	                	///// ilan
	                	int x2 = (Integer)invoke("getNumOfDiscs",new Class[] {int.class},new Object[] {new Integer(3)}); 
	                	if(x2 != n)
		                {
	                		System.err.println("\n>>> Error(3) in HanoiTowers.java\n");
	        	        	System.exit(1);
		    	        }	
	                	////// ilan
	                	
	                    job_done = true;
	                    //s3 = s3 + "  JOB DONE!!!!";
	                }
	            } else
	            {
	                s3 = "�� ���� ������ �� �����";
	            }
	            information = new StringBuffer(s3.length());
	            for(int l = 0; l < s3.length(); l++)
	            {
	                information.append(s3.charAt(l));
	            }
	            appendToTA(information.toString()+"\n");
	            repaint();
	            try
	            {
	                Thread.sleep(speed);
	            }
	            catch(InterruptedException _ex) { }
	        }
	        if(i > 1)
	        {
	            Hanoi(i - 1, s1, s, s2);
	        }
	    }

	    public void paint(Graphics g)
	    {	
	    	g.setColor(Color.white);
            setBackground(Color.white);
            g.setColor(Color.gray);
	        for(int k=0; k<6; k++)
	        	g.drawLine(p_x - 120, draw_y+k, p_x + p_diff * (p-1) + 120, draw_y+k);
	        for(int i = 0; i < p; i++)
	        {
	            poles[i].paint(g);
	        }
	        
	        if(movingDisk != null)
	        {
	            movingDisk.paint(g);
	        }
	        	        
	        g.setFont(new Font("TimesRoman", 0, 16));
	        g.setColor(Color.black);
	        if(automatic)
	        	g.drawString("���� ������� = " + n + "  ,  ����� ��������� = " + speed + "�`�" + "  ,  ���� ������ = " + moves, 70, 20);
	        else
	        	g.drawString("���� ������� = " + n + "  ,  ���� ������ = " + moves, 190, 20);
	        g.setColor(Color.white);
	        setBackground(Color.white);
	    }

	    public void stop()
	    {
	        job_done = false;
	        first_time = true;
	    }

	    public void init()
	    {        
	        job_done = false;
	        String[] poleName = {"1","2","3"};
	        
	        if(first_time)
	        {
	            first_time = false;
	            for(int i = 0; i < p; i++)
	            {
	            	poles[i] = new HanoiPole();
	                poles[i].setName(poleName[i]);
	            }
	        }
	        
	        for(int i = 0; i < p; i++)
	        {
	            poles[i].clear();
	            poles[i].setHeight(n * p_unit_height);
	            poles[i].setXY(p_x + i * p_diff, draw_y - n * p_unit_height);
	        }

	        for(int j = n; j >= 1; j--)
	        {
	            HanoiDisk hanoidisk = new HanoiDisk(j, j * disk_width * 2, disk_height);
	            hanoidisk.setColor(diskColor);
	            poles[0].moveInDisk(hanoidisk);
	        }
	        
	        
	        ////// ilan
            int y1 = (Integer)invoke("getSizeTopDisc",new Class[] {int.class},new Object[] {new Integer(1)});
            int y2 = (Integer)invoke("getSizeTopDisc",new Class[] {int.class},new Object[] {new Integer(2)});
            int y3 = (Integer)invoke("getSizeTopDisc",new Class[] {int.class},new Object[] {new Integer(3)});
	        if(y1 != 1  ||  y2 != 0  ||  y3 != 0)
	        {
	        	System.err.println("\n>>> Error(1) in HanoiTowers.java\n");
	        	System.exit(1);
	        }
	        
            int z1 = (Integer)invoke("getNumOfDiscs",new Class[] {int.class},new Object[] {new Integer(1)});
            int z2 = (Integer)invoke("getNumOfDiscs",new Class[]{},new Object[] {});
            
	        if(z1 != z2)
	        {
	        	System.err.println("\n>>> Error(2) in HanoiTowers.java\n");
	        	System.exit(1);
	        }	
	        /////// ilan
	        
	        
	        information = new StringBuffer(" ");
	        repaint();
	    }

	    public void start()
	    {
	    	if(automatic)
	    	{
	    		run();
	    	}
	    	else
	    		TA.setText("");
	    }
	    
	    public void run()
	    {
	    	TA.setText("����� ��������...\n\n");
	    	try
	         {
	             Thread.sleep(1500L);
	         }
	         catch(InterruptedException _ex) { }	    	 
	        Hanoi(n, "1", "2", "3");
	        appendToTA("\n��������� ������� ������.");
	    }

	    public synchronized boolean mouseDown(Event event, int i, int j)
	    {
	        boolean flag = false;
	        if(job_done || automatic)
	        {
	            return true;
	        }
	        for(int k = 0; k < 3 && !flag; k++)
	        {
	            HanoiDisk hanoidisk = poles[k].topDisk();
	            if(hanoidisk != null && hanoidisk.selected(i, j))
	            {
	                start_pole = k;
	                dest_pole = k;
	                HanoiDisk hanoidisk1 = poles[start_pole].moveOutDisk();
	                movingDisk = hanoidisk1;
	                movingDisk.setColor(movingDiskColor);
	                flag = true;
	            }
	        }

	        if(flag)
	        {
	            repaint();
	        }
	        return true;
	    }

	    public synchronized boolean mouseDrag(Event event, int i, int j)
	    {
	        if(job_done || automatic)
	        {
	            return true;
	        }
	        if(movingDisk != null)
	        {
	            movingDisk.setXY(i - movingDisk.getWidth() / 2, j - movingDisk.getHeight() / 2);
	            repaint();
	        }
	        return true;
	    }

	    public synchronized boolean mouseUp(Event event, int i, int j)
	    {
	        boolean flag = false;
	        String s = " ";
	        if(job_done || automatic)
	        {
	            return true;
	        }
	        if(movingDisk != null)
	        {
	            for(int k = 0; k < 3 && !flag; k++)
	            {
	                if(poles[k].selected(i, j))
	                {
	                    dest_pole = k;
	                    flag = true;
	                }
	            }

	            if(!poles[dest_pole].empty())
	            {
	                HanoiDisk hanoidisk = poles[dest_pole].topDisk();
	                if(hanoidisk.number() < movingDisk.number())
	                {
	                    s = ">>> �� ���� ������ ����� ���� " + poles[start_pole].getName() + " ���� " + poles[dest_pole].getName() + "  !!!";
	                    dest_pole = start_pole;
	                }
	            }
	            movingDisk.setColor(diskColor);
	            poles[dest_pole].moveInDisk(movingDisk);
	            if(start_pole != dest_pole)
	            {
	                s = "����� ���� ���� " + poles[start_pole].getName() + " ���� " + poles[dest_pole].getName() + ".";
	                moves++;
	                if(poles[2].getNumberDisk() == n)
	                {
	                    job_done = true;
	                    s = s + "\n\n�� ����� ���� ��� !!!";
	                }
	            }
	            information = new StringBuffer(s.length());
	            for(int l = 0; l < s.length(); l++)
	            {
	                information.append(s.charAt(l));
	            }

	            movingDisk = null;
	            start_pole = -1;
	            dest_pole = -1;
	            if(information.toString().compareTo(" ")!=0)
	            {
	            	if(information.toString().charAt(0)=='>')
	            		appendToTA("\n" + information.toString());
	            	else
	            		appendToTA("\n" + moves + ") " + information.toString());
	            }
	            repaint();
	        }
	        return true;
	    }

	   public void appendToTA(String s)
	   {
	        TA.append(s);
	        TA.setCaretPosition(TA.getText().length());
	   }
	   
	   public void moveDisc(int fromPole, int toPole)
	   {
		   if(!automatic)
		   {
			   HanoiDisk hanoidisk = poles[fromPole-1].moveOutDisk();
			   poles[toPole-1].moveInDisk(hanoidisk);
			   moves++;
			   String s = moves + ") " + "����� ���� ���� " + poles[fromPole-1].getName() + " ���� " + poles[toPole-1].getName() + ".\n";
	           TA.append(s);
			   repaint();
			   try
	           {
	               Thread.sleep(speed);
	           }
	           catch(InterruptedException _ex) { }
		   }
	   }
	   
	   private Object invoke(String aMethod, Class[] params, Object[] args) 
	   {
		   Object res=null;
		    try 
		    {
		    	res = HT.getClass().getMethod(aMethod,params).invoke(HT, args);
		      } 
		    catch (Exception e) {
		      e.printStackTrace();
		      } 
		    return(res);
		}
}