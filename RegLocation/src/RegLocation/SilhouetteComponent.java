package RegLocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

public class SilhouetteComponent extends JComponent
{
    final static long delay = 100; //ms
    static float R;
    Silhouette silhouette;
    SilhouetteView silhouetteView;
    Vector<MarksListener> MarksListeners;
    SizesConverter converter;
    Vector<Mark> MAL;

    public SilhouetteComponent(float R)
    {
        this.R = R;
        MarksListeners = new Vector<MarksListener>();
        MAL = new Vector<Mark>();
        silhouette = new Silhouette();
        converter = new SizesConverter(getSize());
        silhouetteView = new SilhouetteView(MAL,converter);

        Thread Animator = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    while(true)
                    {
                        silhouetteView.paintPoints(getGraphics());
                        Thread.currentThread().sleep(delay);
                    }
                }
                catch (InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Animator.start();

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int x = e.getX();
                int y = e.getY();

                markAdd(converter.toFloat(x - getWidth()/2),converter.toFloat(getHeight()/2 - y));
            }
        });

        addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                Rectangle b = e.getComponent().getBounds();
                e.getComponent().setBounds(b.x,b.y,b.width,b.width);
                converter.setSCS(e.getComponent().getSize());
                paintComponent(getGraphics());
                //Update component?
            }
        });

    }

    public void setR(float R)
    {
        SilhouetteComponent.R = R;
        silhouetteView.paint(getGraphics());
    }

    public void addMarksListener(MarksListener listener)
    {
        MarksListeners.add(listener);
    }

    public void MarksActionPerformed(Mark mk)
    {
        for(MarksListener item : MarksListeners)
            item.actionPerformed(mk);
    }

    public void markAdd(float x,float y)
    {
        TOL type = TOL.FOREVER;
        Color color = Color.GREEN;

        if(silhouette.InRegion(x,y) != 0)
        {
            type = TOL.EXPAND;
            color = Color.RED;
        }
        Mark newMark = new Mark(x,y,type,color);
        MAL.add(newMark);
        MarksActionPerformed(newMark);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        silhouetteView.paint(g);
    }

    @Override
    public void update(Graphics g)
    {
        super.update(g);
        paintComponent(g);
    }


}
