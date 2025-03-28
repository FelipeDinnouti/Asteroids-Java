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

    public void SetPosition(Vector position) {
        this.position = position;
    }

    public void SetVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public Vector GetPosition() {
        return this.position;
    }

    public Vector GetVelocity() {
        return this.velocity;
    }
}