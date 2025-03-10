package src;
public class Timer {
    private float duration = 0.0f;
    private float current_time = 0.0f;

    // Constructor
    public Timer(float duration) {
        this.duration = duration;
    }

    public float GetCurrentTime() {
        return this.current_time;
    }

    public float GetDuration() {
        return this.duration;
    }

    public void Update(float delta) {
        if (current_time > 0.0f) {
            current_time -= delta;
        } else if (current_time < 0.0f) {
            current_time = 0.0f;
        }
    }

    public void Fire() {
        current_time = duration;
    }
}
