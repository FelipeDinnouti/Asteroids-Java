package src;

public class WorldContext {
    public Entity player;

    public Bullet[] bullets;
    public int bullet_count;

    public Asteroid[] asteroids;
    public int asteroid_count;

    public InputHandler input_handler;
    public Vector camera_position = new Vector(0, 0);
    public float delta_time = 0.016f;

    // Timers
    public Timer shoot_timer   = new Timer(0.1f);
    public Timer shotgun_timer = new Timer (5.0f);

    public WorldContext() {
        bullets = new Bullet[32]; // More than n bullets and it explodes
        bullet_count = 0;

        asteroids = new Asteroid[64];
        asteroid_count = 0;
    }

    public void CreateAsteroid(int level, Vector position) {
        asteroids[asteroid_count] = new Asteroid(level);
        asteroid_count++;
    }

    public void CreateBullet(Vector position, float rotation, int damage, boolean friendly) {
        bullets[bullet_count] = new Bullet(position, rotation, damage, friendly);
        bullet_count++;
    }
}