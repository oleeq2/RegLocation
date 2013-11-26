package RegLocation;

public class SilhouetteController {
    float R;
    public SilhouetteController(float R)
    {
        this.R = R;
    }

    public void setR(float R) { this.R = R;}

    boolean InRegion(float x, float y) {
        boolean RetValue;

        RetValue = false;

        if (x > 0 && y > 0)
            RetValue = false;
        else if (x > 0 && y < 0)
            RetValue = ForthQuarter(x, y);
        else if (x < 0 && y > 0)
            RetValue = SndQuarter(x, y);
        else if (x < 0 && y < 0)
            RetValue = ThrQuarter(x, y);

        return RetValue;
    }

    boolean SndQuarter(float x, float y) {
        boolean ret = false;
        if ((x > (-R) && x < 0) && (R / 2 > y && y > 0))
            ret = true;
        return ret;
    }

    boolean ThrQuarter(float x, float y) {
        boolean ret = false;
        float val = (-1) * x - R;
        if (y > val)
            ret = true;
        return ret;
    }

    boolean ForthQuarter(float x, float y) {
        boolean ret = false;
        float val = (float) Math.pow(x, 2) + (float) Math.pow(y, 2);
        if (R / 2 > val)
            ret = true;
        return ret;
    }
}
