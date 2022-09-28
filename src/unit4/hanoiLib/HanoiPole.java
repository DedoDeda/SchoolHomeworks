package unit4.hanoiLib;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Stack;

class HanoiPole
{

    private Stack<HanoiDisk> pole;
    private int x;
    private int y;
    private int width;
    private int height;
    private String name;
    private Color color;
    private int nextDiskY;
    private HanoiDisk disk;
    private Stack<HanoiDisk> temp_pole;
    private int num_disk;

    private void updateNextDiskY()
    {
        nextDiskY = y + height;
    }

    public HanoiPole()
    {
        width = 8;
        height = 220;
        color = Color.gray;
        pole = new Stack<HanoiDisk>();
        updateNextDiskY();
    }

    public HanoiPole(int i, int j, String s)
    {
        width = 8;
        height = 220;
        color = Color.gray;
        pole = new Stack<HanoiDisk>();
        x = i;
        y = j;
        name = new String(s);
        updateNextDiskY();
    }

    public synchronized void moveInDisk(HanoiDisk hanoidisk)
    {
        pole.push(hanoidisk);
        nextDiskY = nextDiskY - hanoidisk.getHeight();
        hanoidisk.setXY((x - hanoidisk.getWidth() / 2) + width / 2, nextDiskY);
        num_disk++;
    }

    public synchronized HanoiDisk moveOutDisk()
    {
        if(!pole.empty())
        {
            disk = (HanoiDisk)pole.pop();
            nextDiskY = nextDiskY + disk.getHeight();
            num_disk--;
            return disk;
        } else
        {
            return null;
        }
    }

    public synchronized HanoiDisk topDisk()
    {
        if(!pole.empty())
        {
            return (HanoiDisk)pole.peek();
        } else
        {
            return null;
        }
    }

    public synchronized boolean empty()
    {
        return pole.empty();
    }

    public synchronized void clear()
    {
        for(; !empty(); moveOutDisk()) { }
    }

    public synchronized void paint(Graphics g)
    {
        Color color1 = g.getColor();
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(color1);
        temp_pole = new Stack<HanoiDisk>();
        for(; !empty(); temp_pole.push(disk))
        {
            disk = moveOutDisk();
        }

        for(; !temp_pole.empty(); disk.paint(g))
        {
            disk = (HanoiDisk)temp_pole.pop();
            moveInDisk(disk);
        }

    }

    public synchronized void setName(String s)
    {
        name = s;
    }

    public synchronized String getName()
    {
        return name;
    }

    public synchronized boolean nameEquals(String s)
    {
        return name.equals(s);
    }

    public synchronized void setWidth(int i)
    {
        width = i;
    }

    public synchronized void setHeight(int i)
    {
        height = i;
    }

    public synchronized void setXY(int i, int j)
    {
        x = i;
        y = j;
    }

    public synchronized boolean selected(int i, int j)
    {
        int k = (x + width / 2) - 3 * width;
        int l = x + width / 2 + 3 * width;
        int i1 = y;
        int j1 = nextDiskY;
        return i >= k && j >= i1 && i <= l && j <= j1;
    }

    public synchronized int getNumberDisk()
    {
        return num_disk;
    }
}
