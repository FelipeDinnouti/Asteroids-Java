package src;
import java.awt.*; 

public class Bullet extends Entity {
    int damage = 0;
    int length = 5;
    Color color = Color.RED;
    boolean friendly;
    float lifetime;

    // Constructor
    public Bullet(Vector position, float rotation, int damage, boolean friendly) {
        this.position = position;
        this.damage = damage;
        this.rotation = rotation;
        this.friendly = friendly;

        lifetime = 1.5f;
    }

    // Getter and setter
    public float GetLifetime() {
        return this.lifetime;
    }
    public void SetLifetime(float lifetime) {
        this.lifetime = lifetime;
    }
    public void DecreaseLifetime(float delta) {
        this.lifetime -= delta;
    }

    public String toString() {
        return String.format("Bullet {lifetime: %f}", lifetime);
    }
}
