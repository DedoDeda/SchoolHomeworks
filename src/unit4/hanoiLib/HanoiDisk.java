package unit4.hanoiLib;

import java.awt.Color;
import java.awt.Graphics;

class HanoiDisk
{

    private int disk;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean move;
    private Color color;

    public HanoiDisk(int i, int j, int k)
    {
        color = Color.blue;
        disk = i;
        x = 0;
        y = 0;
        width = j;
        height = k;
    }

    public synchronized int number()
    {
        return disk;
    }

    public synchronized void movable(boolean flag)
    {
        move = flag;
    }

    public synchronized boolean ismovable()
    {
        return move;
    }

    public synchronized boolean selected(int i, int j)
    {
        return i >= x && j >= y && i <= x + width && j <= y + height;
    }

    public synchronized void setXY(int i, int j)
    {
        x = i;
        y = j;
    }

    public synchronized void paint(Graphics g)
    {
        Color color1 = g.getColor();
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
        g.setColor(color1);
    }

    public synchronized int getWidth()
    {
        return width;
    }

    public synchronized int getHeight()
    {
        return height;
    }

    public synchronized void setColor(Color color1)
    {
        color = color1;
    }
}
