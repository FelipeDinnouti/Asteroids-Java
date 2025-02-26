public class Vector {
    public float x, y; 

    float lerp(float a, float b, float f)
    {
        return a * (1.0f - f) + (b * f);
    }

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector(Vector v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vector plus(Vector other) {
        return new Vector(x+other.x, y+other.y);
    }

    public Vector minus(Vector other) {
        return new Vector(x-other.x, y-other.y);
    }

    public Vector mult(float m) {
        return new Vector(x*m, y*m);
    }

    public Vector divide(float d) {
        return new Vector(x/d, y/d);
    }

    public Vector lerp(Vector other, float f) {
        return new Vector(lerp(x,other.x, f), lerp(y, other.y, f));
    }

    public Vector rotate(float angle) { // Assumed in radians
        Vector v = new Vector(0,0);
        double dx = (x* Math.cos(angle))-(y* Math.sin(angle));
        double dy = (float) (x* Math.sin(angle))+(y* Math.cos(angle));

        v.x = (float) dx;
        v.y = (float) dy;
        return v;
    }

    public void print() {
        System.out.println(String.format("{%f, %f}", x, y));
    }
}   