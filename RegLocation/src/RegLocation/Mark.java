package RegLocation;
import java.awt.Color;

public class Mark implements Comparable<Mark>       //MarkView include
{
    static final float step = 0.1f;
    float x;
    float y;
    float factor;
    TOL type;
    Color markColor;

    public Mark(float x,float y,TOL type,Color markColor)
    {
        this(x,y);
        this.type = type;

        if(this.type == TOL.EXPAND)            //TODO
            factor = 0f;
        else
            factor = 1f;

        this.markColor = markColor;

    }

    public Mark(float x, float y)
    {
        this.x = x;
        this.y = y;
        type = TOL.FOREVER;
    }

    public Color getColor() { return markColor;  }

    public float getFactor()
    {
        return factor;
    }

    public float incFactor()
    {
        if(type == TOL.EXPAND)
        {
            factor += step;
            if(factor >= 1)
            {
                type = TOL.FOREVER;
                factor = 1f;
            }
        }
        return factor;
    }

    public float[] getCoord()
    {
        float[] ret = new float[2];
        ret[0] = x;
        ret[1] = y;
        return ret;
    }

    public String toString()
    {
        return "X: " + x + "\n Y: " + y;
    }

    @Override
    public int compareTo(Mark o)
    {
        float[] coord = o.getCoord();
        int ret = 0;
        double r_current = Math.sqrt((float) Math.pow(x, 2) + (float) Math.pow(y, 2));
        double r = Math.sqrt((float) Math.pow(coord[0], 2) + (float) Math.pow(coord[1], 2));
        if (x == coord[0] && y == coord[1])
            ret = 0;
        else if (r_current < r)
            ret = 1;
        else
            ret = -1;

        return ret;
    }
}
