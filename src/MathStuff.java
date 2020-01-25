public abstract class MathStuff {
    public static float restrictToRange(float x, float min, float max) {
        if (x < min) x = min;
        if (x > max) x = max;

        return x;
    }

    public static double getDistance(float x1, float y1, float x2, float y2) {
        double dX = x2 - x1;
        double dY = y2 - y1;

        return (float) Math.sqrt(dX * dX + dY * dY);

    }
}
