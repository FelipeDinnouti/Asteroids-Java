package src;
import java.lang.Math;

public class Asteroid extends Entity {
    // Level dictates how many times it can be destructed before it disappears, once level reaches 0, it is destroyed (without breaking apart)
    private int vertice_count = 8;
    private float min_size = 20; // Size is multiplied by level
    private float min_random_offset = 4;// Also multiplied by level 
    private int level;  

    public float angular_velocity = 0;

    public Asteroid(int level) {
        this.level = level;

        // Create verticies randomly
        Vector[] model = new Vector[vertice_count];
        float spacing = 3.14159f/vertice_count;
        float radius = min_size*level;

        for (int i = 0; i<vertice_count; i++) {
            float theta = spacing*i;

            // Generate random vertice offset
            Vector offset = new Vector(Math.random()*min_random_offset*level, Math.random()*min_random_offset*level);

            model[i] = new Vector(radius*Math.cos(theta), radius*Math.sin(theta)).plus(offset);
        }    

        this.vertices = model;
    }

    // Getter and Setter
    public int GetVerticeCount() {
        return this.vertice_count;
    }

    public void SetPosition(Vector position) {
        this.position = position;
    }

    public Vector[] GetBoundingBox() {
        Vector lowest, highest;
        lowest = new Vector(9999,9999);
        highest = new Vector(-9999,-9999);

        Vector[] bounding = {lowest, highest};
        

        for (int i = 0; i<this.vertices.length; i++) {
            Vector vertice = this.vertices[i];

            lowest.x = (lowest.x>vertice.x) ? vertice.x : lowest.x;
            lowest.y = (lowest.y>vertice.y) ? vertice.y : lowest.y;

            highest.x = (highest.x<vertice.x) ? vertice.x : highest.x;
            highest.y = (highest.y<vertice.y) ? vertice.y : highest.y;
        }
        
        return bounding;
    }

}
