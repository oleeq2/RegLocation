package RegLocation;

import javax.swing.*;
import java.awt.*;

public class SilhouetteView
{
    float R;

    public SilhouetteView(float R)
    {
        this.R = R;
    }

    public void setR(float R)
    {
        this.R = R;
    }

    public float getR()
    {
        return R;
    }

    public void paint(Graphics g, Dimension size)
    {
        g.setColor(new Color(0x99, 0xff, 0x99));
        g.fillRect(0, 0, (int) size.getWidth(), (int) size.getHeight());
        paintSilhouette(g, size);
    }

    void paintSilhouette(Graphics g, Dimension size)
    {
        int xR;
        int yR;
        int x_center;
        int y_center;

        xR = (int) (R * size.getWidth() / 4);
        //yR = (int)(R*size.getHeight()/4);
        yR = xR;

        x_center = (int) (size.getWidth() / 2);
        y_center = (int) (size.getHeight() / 2);

        g.setColor(new Color(0x34, 0xc9, 0x24));
        g.fillArc(x_center - xR / 2, y_center - yR / 2, xR, yR, 0, -90);
        g.fillPolygon(makeTrianglePolygon(x_center, y_center, xR, yR));
        g.fillRect(x_center - xR, y_center - yR / 2, xR, yR / 2);
    }

    Polygon makeTrianglePolygon(int x_center, int y_center, int width, int height)
    {
        int[] x_points = new int[3];
        int[] y_points = new int[3];
        int num_points = x_points.length;

        x_points[0] = x_center - width;
        y_points[0] = y_center;

        x_points[1] = x_center;
        y_points[1] = y_center + height;

        x_points[2] = x_center;
        y_points[2] = y_center;

        return new Polygon(x_points, y_points, num_points);
    }
}
