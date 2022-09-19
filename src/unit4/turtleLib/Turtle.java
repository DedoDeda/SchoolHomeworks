package unit4.turtleLib;

import java.awt.Color;

/**
 * <h3 dir="rtl" style="color:red; font-family: Helvetica, Arial, sans-serif">
 * ����� �� ������ �� ���� �������� ���� ����� �� ���� ����. 
 * ���� ������ ��, ��� ����� ����� ����� ����� ������ �����.
 * ���, ���� ��� ��� "������" �� ��� ������ �� ����� ����� - 
 * ���� ����� �� ������ ������� �� ��� ����� ����� �� ���� �� ����� �����. 
 * ��� �� ���, ���� ���� ����� ������� ��������� ���� ����� �� ����� �����. 
 * ���� �� ����� ����� ������ �� �� ���� �� ��� ����
 * </h3>
 * @author ���� ���� �����, ����� ������ ������, ����������� ������, �������
 * @version 26.11.2007
 * <br/>
 * @see 
 * <table>
 * 	<tr>
 * 		<td style="background:#9999FF"><b> :������, ������� ���� ������ ����� ��� ����� ����� �������� ����� 100 </b></td>
 * 	</tr>
 * 	<tr>
 * 		<td style="background:#CCCCFF">
 * <pre>
 * <b>import</b> unit4.turtleLib.Turtle;
 * 
 * <b>public class</b> TurtleDrawRectangle 
 * {
 * 	<b>public static void </b>main(String[] args) 
 *	{
 *		Turtle t1 = new Turtle();
 *
 *	  	t1.tailDown();
 *	  
 *	  	t1.moveForward(100);
 *	  	t1.turnRight(90);
 *	  	t1.moveForward(100);
 *	  	t1.turnRight(90);
 *	  	t1.moveForward(100);
 *	  	t1.turnRight(90);
 *	  	t1.moveForward(100);
 *	  		
 *		t1.tailUp();
 *	  	t1.moveForward(50);
 * 	}
 * }</pre>
 * </td>
 * </tr>
 * </table>
 */
public class Turtle
{
    private double alpha;
    private boolean tailDown;
    private TurtleImage image;
    private int delay;

    /** <dt dir="rtl">
     * ������ ����� ��� ���� �� ������ �����,���� ����� ����� �����
     */
    public Turtle()
    {
        this.alpha = 90D;
        this.delay = 500;
        this.image = new TurtleImage();
        this.image.setAngle(Math.toRadians(alpha));
        this.image.setPenColor(Color.BLACK);
    }

    /** <dt dir="rtl">
     * ������ ����� �� ��� ���� ����� ����� ����� 
     * ����: �� ���� �� ��� ���� ��� ����� ����� ��
     */
    public void moveForward(double x)
    {
        double d1 = x * Math.cos(Math.toRadians(alpha));
        double d2 = -x * Math.sin(Math.toRadians(alpha));
        image.moveBy(d1, d2, tailDown);
        delay();
    }

    /** <dt dir="rtl">
     * ������ ����� �� ��� ���� ����� ����� �����
     * ����: �� ���� �� ��� ���� ��� ����� ����� �� 
     */
    public void moveBackward(double x)
    {
        moveForward(-x);
    }

    /** <dt dir="rtl">
     * ������ ���� �� ��� ��� ���� ����� ���� ��� ����� ����� 
     */
    public void turnLeft(double d)
    {
        alpha += d;
        //alpha = alpha % 360;
        //System.out.println("d=" + d + ", alpha=" + alpha);
        image.setAngle(Math.toRadians(alpha));
        delay();
    }

    /** <dt dir="rtl">
     * ������ ���� �� ��� ��� ���� ����� ���� �� ����� ����� 
     */
    public void turnRight(double d)
    {
        turnLeft(-d);
    }

    /** <dt dir="rtl">
     * ������ ������ �� ��� ��� 
     */
    public void tailDown()
    {
        tailDown = true;
    }

    /** <dt dir="rtl">
     * ������ ����� �� ��� ���
     */
    public void tailUp()
    {
        tailDown = false;
    }

    /** <dt dir="rtl">
     * ������ ������ ����� �� ���� ������ ���
     * 
     * @param status ��� '���' ���� �� ���, ��� '���' ����� �� ���
     */
    public void setVisible(boolean status)
    {
    	image.setVisible(status);
    }
    
    /** <dt dir="rtl">
     * ������ ������ ����� �� ��� ���� ���
     * 
     * @param milliseconds ���� ���� ����� (���� ����) ������ ����� ���
     * 
     */
    public void setDelay(int milliseconds)
    {
    	if(milliseconds>0)
    		this.delay = milliseconds;
    }
    
    
    /** <dt dir="rtl">
     * ������ ������ ����� �� ���� �� ����� ���
     * 
     * @param color ��� 
     */
    public void setTailColor(Color color)
    {
    	this.image.setPenColor(color);
    }
    
    
    private void delay()
    {
        if(delay == 0)
            return;
        try
        {
            Thread.sleep(delay);
        }
        catch(InterruptedException interruptedexception) { }
    }
}
