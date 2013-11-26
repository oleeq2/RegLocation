package RegLocation;

public class Mark
{
    float x, y;
    float TTL;
    private static final float step = 0.3f;

    public Mark(float x, float y, boolean isForever)
    {
        this.x = x;
        this.y = y;
        if (isForever)
            TTL = 1;
        else
            TTL = 0.01f;
    }

    public float getTTL()
    {
        return TTL;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float incTTL()
    {
        if (TTL < 1)
        {
            TTL += step;
        }
        return TTL;
    }

    @Override
    public String toString()
    {
        return "X: " + x + "\nY: " + y;
    }
}
