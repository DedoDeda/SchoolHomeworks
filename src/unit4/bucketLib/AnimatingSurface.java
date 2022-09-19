package unit4.bucketLib;



abstract class AnimatingSurface extends Surface implements Runnable
{
    public Thread thread;

    public AnimatingSurface()
    {
    }

    public abstract void step(int i, int j);

    public abstract void reset(int i, int j);

    public void start()
    {
        if(thread == null && !super.dontThread)
        {
            thread = new Thread(this);
            thread.setPriority(1);
            thread.setName(super.name + " Demo");
            thread.start();
        } else
        {
            System.out.println("Thread exist: " + (thread != null));
        }
    }

    public synchronized void stop()
    {
        if(thread != null)
        {
            thread.interrupt();
        }
        thread = null;
        notifyAll();
    }

    public void run()
    {
        Thread thread1;
        for(thread1 = Thread.currentThread(); thread == thread1 && !isShowing() || getSize().width == 0;)
        {
            try
            {
                AnimatingSurface tmp = this;
                Thread.sleep(200L);
            }
            catch(InterruptedException interruptedexception) { }
        }

        while(thread == thread1) 
        {
            repaint();
            try
            {
                AnimatingSurface _tmp1 = this;
                Thread.sleep(super.sleepAmount);
            }
            catch(InterruptedException interruptedexception1) { }
        }
        thread = null;
    }
}
