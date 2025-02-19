public class Entity {
    public Vector[] vertices;

    public Vector position;
    public float rotation = 0.0f; // in radians

    public Entity() {
        position = new Vector(50,50);
    }
}