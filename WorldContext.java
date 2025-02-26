public class WorldContext {
    public Entity player;
    public Bullet[] bullets;
    public int bullet_count;

    public InputHandler input_handler;
    public Vector camera_position = new Vector(0, 0);
    public float delta_time = 0.016f;

    // Timers
    public Timer shoot_timer = new Timer(0.1f);

    public WorldContext() {
        bullets = new Bullet[16]; // More than 64 bullets and it explodes
        bullet_count = 0;
    }
}