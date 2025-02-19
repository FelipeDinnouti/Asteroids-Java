public class Vector {
    public int x, y; 

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector plus(Vector other) {
        return new Vector(x+other.x, y+other.y);
    }

    public void print() {
        System.out.println(String.format("{%d, %d}", x, y));
    }
}   