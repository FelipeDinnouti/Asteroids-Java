import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {     
    public boolean w_pressed = false;
    public boolean a_pressed = false;
    public boolean s_pressed = false;
    public boolean d_pressed = false;

    public boolean j_pressed = false;
    public boolean h_pressed = false;
    public boolean k_pressed = false;

    public InputHandler() {

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 87) { // W
            w_pressed = true;
        } else if (e.getKeyCode() == 65) { // A
            a_pressed = true;
        } else if (e.getKeyCode() == 83) { // S
            s_pressed = true;
        } else if (e.getKeyCode() == 68) { // D
            d_pressed = true;
        } else if (e.getKeyCode() == 72) { // H
            h_pressed = true;
        } else if (e.getKeyCode() == 74) { // J
            j_pressed = true;
        } else if (e.getKeyCode() == 75) { // K
            k_pressed = true;
        }
    }
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 87) { // W
            w_pressed = false;   
        } else if (e.getKeyCode() == 65) { // A
            a_pressed = false;
        } else if (e.getKeyCode() == 83) { // S
            s_pressed = false;
        } else if (e.getKeyCode() == 68) { // D
            d_pressed = false;
        } else if (e.getKeyCode() == 72) { // H
            h_pressed = false;
        } else if (e.getKeyCode() == 74) { // J
            j_pressed = false;
        } else if (e.getKeyCode() == 75) { // K
            k_pressed = false;
        }
    }
    public void keyTyped(KeyEvent e) {

    }
}
