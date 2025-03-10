package src;
public class Entity {
    public Vector[] vertices;

    public Vector position;
    public Vector velocity;
    public float rotation = 0.0f; // in radians

    public Entity() {
        position = new Vector(50,50);
        velocity = new Vector(0,0);
    }
}