package unit4.bucketLib;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.Vector;


class BucketSystem extends AnimatingSurface
{

    private static final int CLOSE = 0;
    private static final int OPEN = 1;
    private static final int FORWARD = 0;
    private static final int BACKWARD = 1;
    private static final int DOWN = 2;
    private static final int UP = 3;
    private int aw;
    private int ah;
    private int x;
    private int y;
    private int angleStart;
    private int angleExtent;
    private int mouth;
    private int direction;
    Vector<Bucket> buckets;
    int maxCapacity;
    double plus;
    GradientPaint t1;
    GradientPaint t2;
    int count;
    TexturePaint water;
    Bucket first;
    Bucket second;
    GeneralPath p;
    float px1;
    float py1;
    float cx1;
    float cy1;
    float cx2;
    float cy2;
    float px2;
    float py2;

    public void addBucket(Bucket bucket)
    {
        if(bucket.capacity > maxCapacity)
        {
            maxCapacity = bucket.capacity;
            plus = (double)bucket.capacity / 150D;
        }
        buckets.add(bucket);
    }

    public BucketSystem()
    {
        angleStart = 45;
        angleExtent = 270;
        mouth = 0;
        direction = 0;
        buckets = new Vector<Bucket>();
        maxCapacity = 0;
        plus = 0.0D;
        count = 0;
        p = new GeneralPath(0);
        setBackground(Color.white);
        t1 = new GradientPaint(0.0F, 0.0F, Color.cyan, 20F, 20F, Color.blue, true);
        t2 = new GradientPaint(0.0F, 0.0F, Color.cyan, 30F, 30F, Color.blue, true);
    }

    public void reset(int i, int j)
    {
        x = 0;
        y = 0;
        aw = i / 12;
        ah = j / 12;
    }

    void setAction(Bucket bucket, Bucket bucket1)
    {
        for(int i = 0; i < buckets.size(); i++)
        {
            Bucket bucket2 = (Bucket)buckets.elementAt(i);
            if(bucket2 == bucket)
            {
                first = bucket;
                second = bucket1;
                return;
            }
            if(bucket2 == bucket1)
            {
                first = bucket1;
                second = bucket;
                return;
            }
        }

    }

    public void step(int i, int j)
    {
        for(int k = 0; k < buckets.size(); k++)
        {
            Bucket bucket = (Bucket)buckets.elementAt(k);
            if(bucket.pour != null)
            {
                if(bucket.quantity > 0.0D && bucket.pour.quantity < (double)bucket.pour.capacity)
                {
                    bucket.quantity -= plus;
                    bucket.pour.quantity += plus;
                } else
                {
                    bucket.pour = null;
                    first = null;
                    second = null;
                }
            } else
            if(bucket.empting)
            {
                if(bucket.quantity > 0.0D)
                {
                    bucket.quantity -= plus;
                } else
                {
                    bucket.empting = false;
                }
            } else
            if(bucket.filling)
            {
                if(bucket.quantity < (double)bucket.fillAmount)
                {
                    bucket.quantity += plus;
                } else
                {
                    bucket.filling = false;
                }
            }
        }

    }


    public void render(int i, int j, Graphics2D graphics2d)
    {
    	//System.out.println("render");
        int k = 55;
        int l = k + k / 3;
        int i1 = ((i + l) - k - k) / (l + k);
        int k1;
        if(i1 < buckets.size())
        {
            k1 = k;
        } else
        {
            int j1 = buckets.size();
            k1 = ((i + l) - k - k - j1 * (l + k)) / 2 + k;
        }
        graphics2d.setStroke(new BasicStroke(5F));
        double d = (0.5D * (double)j) / (double)maxCapacity;
        GradientPaint gradientpaint;
        if(count == 0)
        {
            gradientpaint = t1;
        } else
        {
            gradientpaint = t2;
        }
        count = 1 - count;
        for(int l1 = 0; l1 < buckets.size(); l1++)
        {
            Bucket bucket = (Bucket)buckets.elementAt(l1);
            int i2 = (k + l) * l1 + k1;
            float f = (float)(d * (double)bucket.capacity);
            int j2 = (int)((double)j - (0.20000000000000001D * (double)j + (double)f));
            graphics2d.setColor(Color.black);
            graphics2d.drawLine(i2, j2, i2, (int)((float)j2 + f));
            graphics2d.drawLine(i2, (int)((float)j2 + f), i2 + k, (int)((float)j2 + f));
            graphics2d.drawLine(i2 + k, j2, i2 + k, (int)((float)j2 + f));
            double d1 = d * bucket.quantity;
            java.awt.geom.Rectangle2D.Float float1 = new java.awt.geom.Rectangle2D.Float();
            float1.setFrame(i2+3, (double)j - ((double)j * 0.20000000000000001D + d1)-3, k-5, d1);
            graphics2d.setColor(Color.blue);
            graphics2d.setPaint(gradientpaint);
            graphics2d.fill(float1);
            graphics2d.setColor(Color.RED);
            graphics2d.setFont(new Font("Arial",1,11));
            graphics2d.drawString(bucket.name, i2, j2+f+15);
            graphics2d.setFont(new Font("Arial",0,11));
            //graphics2d.drawString("capacity: "+bucket.capacity, i2, j2+f+30);
            int am1 = (int)(bucket.getCurrentAmount() * 10000);
            double am2 = am1 / 10000.0;
            //graphics2d.drawString("amount: "+am2, i2, j2+f+45);
            if(bucket == first)
            {
                px1 = i2;
                py1 = (float)(0.80000000000000004D * (double)j - d1);
                cx1 = (float)((double)i2 + (double)k * 0.5D);
                cy1 = 0.0F;
            } else
            if(bucket == second)
            {
                p.reset();
                p.moveTo(px1, py1);
                cx2 = (float)((double)i2 + (double)k * 0.5D);
                cy2 = 0.0F;
                px2 = i2 + k;
                py2 = (float)(0.80000000000000004D * (double)j - d1);
                p.curveTo(cx1, cy1, cx2, cy2, px2, py2);
                p.lineTo(px2 - 5F, py2);
                p.curveTo(cx2, cy2 + 5F, cx1, cy1 + 5F, px1 + 5F, py1);
                graphics2d.setColor(Color.black);
                graphics2d.draw(p);
                graphics2d.setPaint(gradientpaint);
                graphics2d.fill(p);
            } else
            if(bucket.empting)
            {
                px1 = i2;
                py1 = (float)(0.80000000000000004D * (double)j - d1);
                cx1 = (float)((double)i2 + (double)k * 0.5D);
                cy1 = 0.0F;
                p.reset();
                p.moveTo(px1, py1);
                cx2 = (float)((double)i2 + (double)k * 1.5D);
                cy2 = 0.0F;
                px2 = i2 + k * 2;
                py2 = (float)(0.80000000000000004D * (double)j);
                p.curveTo(cx1, cy1, cx2, cy2, px2, py2);
                p.lineTo(px2 - 5F, py2);
                p.curveTo(cx2, cy2 + 5F, cx1, cy1 + 5F, px1 + 5F, py1);
                graphics2d.setColor(Color.black);
                graphics2d.draw(p);
                graphics2d.setPaint(gradientpaint);
                graphics2d.fill(p);
                graphics2d.setPaint(gradientpaint);
                graphics2d.fill(new java.awt.geom.Rectangle2D.Float(0.0F, 0.81F * (float)j, i, (float)(0.16D * (double)j)));
            }
        }

    }
}
