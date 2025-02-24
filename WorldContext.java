public class WorldContext {
    public Entity player;
    public Entity[] bullets;

    public InputHandler input_handler;
    public Vector camera_position = new Vector(0, 0);
    public float delta_time = 0.016f;

    // Timers
    public Timer shoot_timer = new Timer(0.4f);
}