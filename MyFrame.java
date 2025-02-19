import java.awt.*; 
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent; 

public class MyFrame extends Frame {
    WorldContext world;

    public MyFrame(WorldContext nworld) {
        this.world = nworld;

        setVisible(true); 
        setSize(400, 300); 
        addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) 
            { 
                System.exit(0); 
            } 
        }); 
    }
    public void paint(Graphics g) 
    { 
        // Default Player Polygon
        int[] x = {0, 10, 20, 10}; 
        int[] y = {0, 5, 0, 25};

        // Offset the polygon by position
        for (int i = 0; i<4; i++) {
            x[i] += world.player.position.x;
            y[i] += world.player.position.y;
        }

        g.setColor(Color.black);
        g.drawPolygon(x, y, 4); 
        g.fillPolygon(x,y, 4);
    } 
}