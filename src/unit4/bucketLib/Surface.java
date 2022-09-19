package unit4.bucketLib;

import java.awt.*;
import java.awt.image.*;
import java.awt.print.*;
import javax.swing.*;

abstract class Surface extends JPanel  implements Printable
{

    public Object AntiAlias;
    public Object Rendering;
    public AlphaComposite composite;
    public Paint texture;
    public String perfStr;
    public BufferedImage bimg;
    public int imageType;
    public String name;
    public boolean clearSurface;
    public boolean dontThread;
    public AnimatingSurface animating;
    protected long sleepAmount;
    private long orig;
    private long start;
    private long frame;
    private Toolkit toolkit;
    private boolean perfMonitor;
    private boolean outputPerf;
    private int biw;
    private int bih;
    private boolean clearOnce;
    static byte lut1Arr[] = {
        0, -1
    };
    static byte lut2Arr[] = {
        0, 85, -86, -1
    };
    static byte lut4Arr[] = {
        0, 17, 34, 51, 68, 85, 102, 119, -120, -103, 
        -86, -69, -52, -35, -18, -1
    };
    private static final int REPORTFRAMES = 30;

    public Surface()
    {
        AntiAlias = RenderingHints.VALUE_ANTIALIAS_ON;
        Rendering = RenderingHints.VALUE_RENDER_SPEED;
        clearSurface = true;
        sleepAmount = 50L;
        setDoubleBuffered(this instanceof AnimatingSurface);
        toolkit = getToolkit();
        name = getClass().getName();
        name = name.substring(name.indexOf(".", 7) + 1);
        setImageType(0);
        if(this instanceof AnimatingSurface)
        {
            animating = (AnimatingSurface)this;
        }
    }

    public int getImageType()
    {
        return imageType;
    }

    public void setImageType(int i)
    {
        if(i == 0)
        {
            if(this instanceof AnimatingSurface)
            {
                imageType = 2;
            } else
            {
                imageType = 1;
            }
        } else
        {
            imageType = i;
        }
        bimg = null;
    }

    public void setAntiAlias(boolean flag)
    {
        AntiAlias = flag ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF;
    }

    public void setRendering(boolean flag)
    {
        Rendering = flag ? RenderingHints.VALUE_RENDER_QUALITY : RenderingHints.VALUE_RENDER_SPEED;
    }

    public void setTexture(Object obj)
    {
        if(obj instanceof GradientPaint)
        {
            texture = new GradientPaint(0.0F, 0.0F, Color.white, getSize().width * 2, 0.0F, Color.green);
        } else
        {
            texture = (Paint)obj;
        }
    }

    public void setComposite(boolean flag)
    {
        composite = flag ? AlphaComposite.getInstance(3, 0.5F) : null;
    }

    public void setMonitor(boolean flag)
    {
        perfMonitor = flag;
    }

    public void setSleepAmount(long l)
    {
        sleepAmount = l;
    }

    public long getSleepAmount()
    {
        return sleepAmount;
    }

    public BufferedImage createBufferedImage(int i, int j, int k)
    {
        BufferedImage bufferedimage = null;
        if(k == 0)
        {
            bufferedimage = (BufferedImage)createImage(i, j);
        } else
        if(k > 0 && k < 14)
        {
            bufferedimage = new BufferedImage(i, j, k);
        } else
        if(k == 14)
        {
            bufferedimage = createBinaryImage(i, j, 2);
        } else
        if(k == 15)
        {
            bufferedimage = createBinaryImage(i, j, 4);
        }
        biw = i;
        bih = j;
        return bufferedimage;
    }

    private BufferedImage createBinaryImage(int i, int j, int k)
    {
        int ai[] = new int[i * j];
        int l = (i * k) / 8;
        if((i * k) % 8 != 0)
        {
            l++;
        }
        byte abyte0[] = new byte[j * l];
        IndexColorModel indexcolormodel = null;
        switch(k)
        {
        case 1: // '\001'
            indexcolormodel = new IndexColorModel(k, lut1Arr.length, lut1Arr, lut1Arr, lut1Arr);
            break;

        case 2: // '\002'
            indexcolormodel = new IndexColorModel(k, lut2Arr.length, lut2Arr, lut2Arr, lut2Arr);
            break;

        case 4: // '\004'
            indexcolormodel = new IndexColorModel(k, lut4Arr.length, lut4Arr, lut4Arr, lut4Arr);
            break;

        case 3: // '\003'
        default:
            (new Exception("Invalid # of bit per pixel")).printStackTrace();
            break;
        }
        DataBufferByte databufferbyte = new DataBufferByte(abyte0, abyte0.length);
        java.awt.image.WritableRaster writableraster = Raster.createPackedRaster(databufferbyte, i, j, k, null);
        return new BufferedImage(indexcolormodel, writableraster, false, null);
    }

    public Graphics2D createGraphics2D(int i, int j, BufferedImage bufferedimage, Graphics g)
    {
        Graphics2D graphics2d = null;
        if(bufferedimage != null)
        {
            graphics2d = bufferedimage.createGraphics();
        } else
        {
            graphics2d = (Graphics2D)g;
        }
        graphics2d.setBackground(getBackground());
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, AntiAlias);
        graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, Rendering);
        if(clearSurface || clearOnce)
        {
            graphics2d.clearRect(0, 0, i, j);
            clearOnce = false;
        }
        if(texture != null)
        {
            graphics2d.setComposite(AlphaComposite.SrcOver);
            graphics2d.setPaint(texture);
            graphics2d.fillRect(0, 0, i, j);
        }
        if(composite != null)
        {
            graphics2d.setComposite(composite);
        }
        return graphics2d;
    }

    public abstract void render(int i, int j, Graphics2D graphics2d);

    public void paintImmediately(int i, int j, int k, int l)
    {
        RepaintManager repaintmanager = null;
        boolean flag = true;
        if(!isDoubleBuffered())
        {
            repaintmanager = RepaintManager.currentManager(this);
            flag = repaintmanager.isDoubleBufferingEnabled();
            repaintmanager.setDoubleBufferingEnabled(false);
        }
        super.paintImmediately(i, j, k, l);
        if(repaintmanager != null)
        {
            repaintmanager.setDoubleBufferingEnabled(flag);
        }
    }

    public void paint(Graphics g)
    {
        Dimension dimension = getSize();
        if(imageType == 1)
        {
            bimg = null;
            startClock();
        } else
        if(bimg == null || biw != dimension.width || bih != dimension.height)
        {
            if(animating != null && (biw != dimension.width || bih != dimension.height))
            {
                animating.reset(dimension.width, dimension.height);
            }
            bimg = createBufferedImage(dimension.width, dimension.height, imageType - 2);
            clearOnce = true;
            startClock();
        }
        if(animating != null && animating.thread != null)
        {
            animating.step(dimension.width, dimension.height);
        }
        Graphics2D graphics2d = createGraphics2D(dimension.width, dimension.height, bimg, g);
        render(dimension.width, dimension.height, graphics2d);
        graphics2d.dispose();
        if(bimg != null)
        {
            g.drawImage(bimg, 0, 0, null);
            toolkit.sync();
        }
        if(perfMonitor)
        {
            LogPerformance();
        }
    }

    public int print(Graphics g, PageFormat pageformat, int i)
        throws PrinterException
    {
        if(i >= 1)
        {
            return 1;
        }
        Graphics2D graphics2d = (Graphics2D)g;
        graphics2d.translate(pageformat.getImageableX(), pageformat.getImageableY());
        graphics2d.translate(pageformat.getImageableWidth() / 2D, pageformat.getImageableHeight() / 2D);
        Dimension dimension = getSize();
        double d = Math.min(pageformat.getImageableWidth() / (double)dimension.width, pageformat.getImageableHeight() / (double)dimension.height);
        if(d < 1.0D)
        {
            graphics2d.scale(d, d);
        }
        graphics2d.translate((double)(-dimension.width) / 2D, (double)(-dimension.height) / 2D);
        if(bimg == null)
        {
            Graphics2D graphics2d1 = createGraphics2D(dimension.width, dimension.height, null, graphics2d);
            render(dimension.width, dimension.height, graphics2d1);
            graphics2d1.dispose();
        } else
        {
            graphics2d.drawImage(bimg, 0, 0, this);
        }
        return 0;
    }

    private void startClock()
    {
        orig = System.currentTimeMillis();
        start = orig;
    }

    private void LogPerformance()
    {
        if(frame % 30L == 0L)
        {
            long l = System.currentTimeMillis();
            long l1 = l - start;
            long l2 = l - orig;
            if(frame == 0L)
            {
                perfStr = name + " " + l1 + " ms";
                if(animating == null || animating.thread == null)
                {
                    frame = -1L;
                }
            } else
            {
                String s = Float.toString(30F / ((float)l1 / 1000F));
                s = s.length() >= 4 ? s.substring(0, 4) : s.substring(0, s.length());
                perfStr = name + " " + s + " fps";
            }
            if(outputPerf)
            {
                System.out.println(perfStr);
            }
            start = l;
        }
        frame++;
    }

}
