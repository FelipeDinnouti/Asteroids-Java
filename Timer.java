public class Timer {
    float duration = 0.0f;
    float current_time = 0.0f;

    public Timer(float duration) {
        this.duration = duration;
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
