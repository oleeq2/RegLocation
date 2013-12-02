package RegLocation;

import java.lang.Math;
public class Silhouette
{

    public boolean InRegion(Mark mk)
    {
        float[] coords = mk.getCoord();
        return this.InRegion(coords[0],coords[1]) == 1;
    }


    public int InRegion(float x,float y)
    {
        int ret = 0;

        float R = SilhouetteComponent.R;

        if(x >= 0)
            if ( y >= 0 )
                ret = 0;
            else
                ret = ForthQuater(x,y,R);
        else
        if ( y > 0 )
            ret = SndQuater(x,y,R);
        else
            ret = ThrQuater(x,y,R);
        return ret;
    }

    int SndQuater(float x,float y,float R)
    {
        int ret = 0;
        if((x> -R && x < 0) && ( R/2 > y && y > 0  ) )
            ret = 1;
        return ret;
    }

    int ThrQuater(float x,float y,float R)
    {
        int ret = 0;
        float val = (-1)*x -  R;
        if( y > val )
            ret = 1;
        return ret;
    }

    int ForthQuater(float x,float y,float R)
    {
        int ret = 0;
        float val = (float)Math.pow(x,2) + (float)Math.pow(y,2);
        if ( R/2 > val  )
            ret = 1;
        return ret;
    }
}
