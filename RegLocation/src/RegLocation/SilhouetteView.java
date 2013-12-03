package RegLocation;


import java.awt.*;
import java.util.Vector;

public class SilhouetteView
{
    Vector<Mark> SAL;
    SizesConverter mySizeConverter;
    float MarkMaxR = 10;

    public SilhouetteView(Vector<Mark> SAL,SizesConverter convert)
    {
        this.SAL = SAL;
        mySizeConverter = convert;
    }

    public void paint(Graphics g)
    {

        g.setColor(Color.WHITE);
        g.fillRect(0,0,(int)mySizeConverter.getWidth(),(int)mySizeConverter.getHeight());
        paintSilhouette(g);
        paintAxes(g);
        paintPoints(g);
    }

    void paintSilhouette(Graphics g)
    {
        int R       = mySizeConverter.toPx(SilhouetteComponent.R);
        int HalfR   = mySizeConverter.toPx(SilhouetteComponent.R/2);
        int XCenter = mySizeConverter.getCenterX();
        int YCenter = mySizeConverter.getCenterY();

        g.setColor(Color.BLUE);
        g.fillRect(XCenter-R,YCenter-HalfR,R, HalfR);
        g.fillArc(XCenter-HalfR,YCenter-HalfR,R,R,0,-90);
        g.fillPolygon(getTrianglePolygon(R));
    }

    void paintAxes(Graphics g)
    {
        int R       = mySizeConverter.toPx(SilhouetteComponent.R);
        int HalfR   = mySizeConverter.toPx(SilhouetteComponent.R/2);
        int XCenter = mySizeConverter.getCenterX();
        int YCenter = mySizeConverter.getCenterY();

        g.setColor(Color.BLACK);
        g.drawLine(0, YCenter, 2*XCenter,YCenter);
        g.drawLine(XCenter, 0, XCenter, 2*YCenter);

        g.drawLine(XCenter - R, YCenter + 5, XCenter - R, YCenter - 5);
        g.drawLine(XCenter + R, YCenter - 5, XCenter + R, YCenter + 5);
        g.drawLine(XCenter + 5, YCenter + R, XCenter - 5, YCenter + R);
        g.drawLine(XCenter + 5, YCenter - R, XCenter - 5, YCenter - R);

        g.drawLine(XCenter - HalfR, YCenter + 5, XCenter - HalfR, YCenter - 5);
        g.drawLine(XCenter + HalfR, YCenter - 5, XCenter + HalfR, YCenter + 5);
        g.drawLine(XCenter + 5, YCenter + HalfR, XCenter - 5, YCenter + HalfR);
        g.drawLine(XCenter + 5, YCenter - HalfR, XCenter - 5, YCenter - HalfR);
    }

    public void paintPoints(Graphics g)
    {
        for(Mark item : SAL)
        {
            int item_x,item_y;
            float factor = item.incFactor();
            item_x =  mySizeConverter.getCenterX() + mySizeConverter.toPx(item.getCoord()[0]);
            item_y =  mySizeConverter.getCenterY() - mySizeConverter.toPx(item.getCoord()[1]);
            int radius = (int)(MarkMaxR*factor);

            g.setColor(item.getColor());
            g.fillOval(item_x - radius/2,item_y - radius/2, radius,radius);
        }
    }

    Polygon getTrianglePolygon(int R)
    {
        int XCenter = mySizeConverter.getCenterX();
        int YCenter = mySizeConverter.getCenterY();

        int num_points = 3;
        int[] x_points = new int[num_points];
        int[] y_points = new int[num_points];

        x_points[0] = XCenter - R;
        y_points[0] = YCenter;

        x_points[1] = XCenter;
        y_points[1] = YCenter;

        x_points[2] = XCenter;
        y_points[2] = YCenter + R;

        return new Polygon(x_points,y_points,num_points);
    }
}
