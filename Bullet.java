import java.awt.*; 

public class Bullet extends Entity {
    int damage = 0;
    int length = 5;
    Color color = Color.RED;
    boolean friendly;
    float lifetime;

    public Bullet(Vector position, float rotation, int damage, boolean friendly) {
        this.position = position;
        this.damage = damage;
        this.rotation = rotation;
        this.friendly = friendly;

        lifetime = 0.5f;
    }
}
