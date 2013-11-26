package RegLocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Silhouette extends JComponent
{
    float R;
    int MarkDefaultR;
    final static int delay = 150;    // Delay in mS

    SilhouetteView view;
    SilhouetteController controller;
    MarksCanvas MarksLayer;

    ArrayList<Mark> Marks;

    ArrayList<MarkSetListener> MarkListeners;

    public Silhouette(float R)
    {

        this.R = R;

        view = new SilhouetteView(R);
        controller = new SilhouetteController(R);
        MarksLayer = new MarksCanvas();

        Marks = new ArrayList<Mark>();
        MarkDefaultR = 15;

        add(MarksLayer);

        MarkListeners = new ArrayList<MarkSetListener>();

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                float x, y;
                int width = getWidth();
                int height = getHeight();

                int y_origin = e.getY();
                int x_origin = e.getX();
                x = 4 * (-width / 2 + x_origin) / (width * getR());
                y = 4 * (height / 2 - y_origin) / (height * getR());


                boolean TOF = false;

                if (controller.InRegion(x, y))
                    TOF = true;

                Mark mark =  new Mark(x_origin, y_origin, TOF);
                Marks.add(mark);
                marksRepaint(getGraphics());
                MarkSetPerformed(mark);
            }
        });


        Thread AnimationThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    while (true)
                    {
                        marksRepaint(getGraphics());
                        Thread.sleep(delay);
                    }
                }
                catch (InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
            }
        });
        AnimationThread.start();
    }

    public void addMarkInR(float x, float y)
    {
        boolean TTL = false;
        if(controller.InRegion(x,y))
            TTL = true;

        int x_px,y_px;

        x_px =   (int)( x*(getWidth()*getR())/4 + getWidth()/2 );
        y_px =   (int)( getHeight()/2 - y*(getWidth()*getR())/4);

        Mark mark = new Mark(x_px,y_px,TTL);
        Marks.add(mark);
        MarkSetPerformed(mark);
        marksRepaint(getGraphics());
    }


    public float getR()
    {
        return R;
    }

    public void setR(float R)
    {

        this.R = R;
        view.setR(R);
        controller.setR(R);

        update(getGraphics());
    }

    public void addMarkSetListener(MarkSetListener listener)
    {
        MarkListeners.add(listener);
    }

    void MarkSetPerformed(Mark x)
    {
        for(MarkSetListener listener : MarkListeners)
            listener.Setted(x);
    }

    @Override
    public void update(Graphics g)
    {
        view.paint(g, getSize());
        marksRepaint(g);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        view.paint(g, getSize());
        marksRepaint(g);
    }


    void marksRepaint(Graphics g)
    {
        MarksLayer.update(g);
    }

    class MarksCanvas extends Canvas
    {

        @Override
        public void update(Graphics g)
        {
            paint(g);
        }

        @Override
        public void paint(Graphics g)
        {

            int index;

            for (index = 0; index < Marks.size(); index++)
            {
                Mark i = Marks.get(index);
                markPaint(g, i);
                i.incTTL();
            }

        }

        void markPaint(Graphics g, Mark mark)
        {
            int x, y;
            int pxMarkR = (int) (mark.getTTL() * MarkDefaultR);

            x = (int)mark.getX();
            y = (int)mark.getY();



            g.setColor(Color.RED);
            g.fillArc(x - pxMarkR / 2, y - pxMarkR / 2, pxMarkR, pxMarkR, 0, 360);
            g.drawString("(" + x + "; " + y + ")", x + MarkDefaultR, y + MarkDefaultR);
           // System.out.println("\nmarkPaint:\n" + "X: " + x + "\nY: " + y + "\nRadius: " + pxMarkR);
        }
    }
}
