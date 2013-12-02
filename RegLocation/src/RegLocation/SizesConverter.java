package RegLocation;

import java.awt.*;
public class SizesConverter
{
    Dimension SCS; //SilhouetteComponentSize
    static final float SilhouetteResolution = 4; //How many R in Plot
    public SizesConverter(Dimension size)
    {
        this.SCS = size;
    }

    public void setSCS(Dimension size)
    {
        this.SCS = size;
    }

    public  int toPx(float distance)
    {
        return toPxX(distance);
    }

    public int toPxY(float distance)
    {
       return (int)((distance*SCS.getHeight())/SilhouetteResolution);
    }

    public int toPxX(float distance)
    {
        return (int)((distance*SCS.getWidth())/SilhouetteResolution);
    }

    public float toFloat(int pxDistance)
    {
        return SilhouetteResolution*pxDistance/((float)SCS.getHeight());
    }

    public int getCenterX()
    {
        return (int)SCS.getWidth()/2;
    }

    public int getCenterY()
    {
        return (int)SCS.getHeight()/2;
    }

    public float getWidth() { return (float)SCS.getWidth(); }
    public float getHeight() { return (float)SCS.getHeight(); }

}
