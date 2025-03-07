public class WorldContext {
    public Entity player;
    public Bullet[] bullets;
    public int bullet_count;

    public InputHandler input_handler;
    public Vector camera_position = new Vector(0, 0);
    public float delta_time = 0.016f;

    // Timers
    public Timer shoot_timer   = new Timer(0.1f);
    public Timer shotgun_timer = new Timer (5.0f);

    public WorldContext() {
        bullets = new Bullet[32]; // More than n bullets and it explodes
        bullet_count = 0;
    }
}