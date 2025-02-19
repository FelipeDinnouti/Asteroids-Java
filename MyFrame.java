import java.awt.*; 
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent; 

public class MyFrame extends Frame {
    WorldContext world;

    void printArr(int[] arr) {
        System.out.print("[");
        for (int i = 0; i<arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.print("]\n");
    }

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
        int[] x = new int[world.player.vertices.length];
        int[] y = new int[world.player.vertices.length];

        for (int i=0; i<world.player.vertices.length; i++) {
            // Apply rotation and offset to model
            Vector transformed = world.player.vertices[i].rotate(world.player.rotation).plus(world.player.position);
            
            x[i] = transformed.x;
            y[i] = transformed.y;
        }

        g.setColor(Color.black);
        g.drawPolygon(x, y, world.player.vertices.length); 
        g.fillPolygon(x,y, world.player.vertices.length);
    } 
}