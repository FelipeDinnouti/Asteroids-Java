public class Vector {
    public int x, y; 

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector plus(Vector other) {
        return new Vector(x+other.x, y+other.y);
    }

    public Vector rotate(float angle) { // Assumed in radians
        Vector v = new Vector(0,0);
        double dx = (x* Math.cos(angle))-(y* Math.sin(angle));
        double dy = (float) (x* Math.sin(angle))+(y* Math.cos(angle));

        v.x = (int) dx;
        v.y = (int) dy;
        return v;
    }

    public void print() {
        System.out.println(String.format("{%d, %d}", x, y));
    }
}   