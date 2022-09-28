package unit4.hanoiLib;

import javax.swing.UIManager;

/**
 * <h3 dir="rtl" style="color:red; font-family: Helvetica, Arial, sans-serif">
 * ����� ����� �� ����� ����� ������ �������� �� ����� ����
 * </h3> 
 * 
 * @author ���� ���� �����, ����� ������ ������, ����������� ������, �������
 * @version 26.11.2007
 */
public class HanoiTowersSimulator
{
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
	}
	
	private HanoiTowersSimulator()
	{	
	}
	
	/** <dt dir="rtl"><b>
	 * ����� �� ����� ��������� ������ ���� 
	 * <br/>
	 * ����: ������ ������ ���� ����� ������ ������ HanoiTowers
	 *  @param haoniTowers HanoiTowers ��� ������  
	 *  @param delay ����� �� ������ ��������� ����� �����
	 */
	public static void simulate(Object haoniTowers, int delay)
	{
		if(!haoniTowers.getClass().getName().endsWith("HanoiTowers"))
			System.err.println("Error: The first parameters is NOT an HanoiTowers Object!!!");
		else
		{
			new HanoiTowersThread(haoniTowers,true,delay).start();
		}
	}
	
	/**  <dt dir="rtl"><b>
	 * ����� �� ������ ��� �� ���� ����� ����� ����� ������ ������ ����
	 * <br/> 
	 * ����: ������ ������ ���� ����� ������ ������ HanoiTowers
	 * @param haoniTowers HanoiTowers ��� ������  
	 */
	public static void play(Object haoniTowers)
	{
		if(!haoniTowers.getClass().getName().endsWith("HanoiTowers"))
			System.err.println("Error: The first parameters is NOT an HanoiTowers Object!!!");
		else
			new HanoiTowersThread(haoniTowers,false,1000).start();
	}
}