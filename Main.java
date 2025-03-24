
import src.Asteroid;
import src.Bullet;
import src.Entity;
import src.GeometryMath;
import src.InputHandler;
import src.MyFrame;
import src.Vector;
import src.WorldContext;

public class Main {
    static int debug_bullet_id = 0;

    public static void update(WorldContext world) {
        Vector player_direction = new Vector(0,1).rotate(world.player.rotation);

        // Movement logic
        if (world.input_handler.w_pressed == true) {
            world.player.velocity = world.player.velocity.plus(player_direction.mult(10.0f));
        } else
        if (world.input_handler.s_pressed == true) {
            world.player.velocity = world.player.velocity.minus(player_direction.mult(10.0f));
        }
        if (world.input_handler.a_pressed == true) {
            world.player.rotation -= 3.54f*world.delta_time;
        }
        if (world.input_handler.d_pressed == true) {
            world.player.rotation += 3.54f*world.delta_time;
        }

        world.player.position = world.player.position.plus(world.player.velocity.mult(world.delta_time));
        
        if (world.input_handler.k_pressed == true) { // Align camera (debug)
            world.camera_position = world.camera_position.lerp(world.player.position, 3.0f*world.delta_time);
        }

        if ((world.shoot_timer.GetCurrentTime() ==0.0f) && (world.input_handler.j_pressed == true)) {
            // Start the timer again
            world.shoot_timer.Fire();

            world.CreateBullet(new Vector(world.player.position), world.player.rotation, 0, false);

            // Pushes the player back
            world.player.velocity = world.player.velocity.minus(player_direction.mult(8.0f));
        }

        if ((world.shotgun_timer.GetCurrentTime() == 0.0f) && (world.input_handler.h_pressed == true)) {
            // Start the timer again
            world.shotgun_timer.Fire();

            for (float f = -0.3f; f <= 0.3f; f += 0.05f) {
                world.CreateBullet(new Vector(world.player.position), world.player.rotation + f, 0, false);
            }

            // Pushes the player back
            world.player.velocity = world.player.velocity.minus(player_direction.mult(80.0f));
        }

        // Update bullets
        for (int i = 0; i<world.bullet_count+1; i++) {
            Bullet b = world.bullets[i];
            if (b == null) continue;

            // Decreasing the lifetime of the bullet
            b.DecreaseLifetime(world.delta_time); 

            // Delete bullet and fill the hole
            if (b.GetLifetime() < 0) {
                if (i==world.bullet_count) {
                    // Delete the bullet because it is alreay in the right-most part of the array 
                    world.bullets[i] = null;
                } else {
                    // Get the bullet in the right-most part of the array to fill the hole
                    world.bullets[i] = world.bullets[world.bullet_count-1]; 
                    world.bullets[world.bullet_count] = null;
                }

                // Doesn't let bullet count be negative
                if (world.bullet_count > 0) {
                    world.bullet_count -= 1;
                }

                // Doesn't update the bullet
                continue;
            }

            // I love java: adds to the position the direction vector multiplied by velocity
            b.position = b.position.plus(new Vector(0,1).rotate(b.rotation).mult(500.0f*world.delta_time));
        }

        // Update asteroids
        for (int i = 0; i<world.asteroids.length; i++) {
            Asteroid asteroid = world.asteroids[i];

            if (asteroid == null) {continue;}
            
            asteroid.position = asteroid.position.plus(asteroid.velocity.mult(world.delta_time));
            asteroid.rotation += asteroid.angular_velocity;
        }

        // Resolve bullet collisions with asteroids, 3 nested for loops???
        for (int i = 0; i<world.bullet_count+1; i++) {
            Bullet bullet = world.bullets[i];
            if (bullet==null) continue;

            Vector bullet_tip = new Vector(0,1).rotate(bullet.rotation).mult(bullet.GetLength()).plus(bullet.position);

            // Step 1:  check if the bullet tip or tail lies within asteroid bounding box
            for (int n = 0; n<world.asteroid_count+1; n++) {
                Asteroid asteroid = world.asteroids[n];
                if (asteroid==null) continue;

                Vector[] bounding = asteroid.GetBoundingBox();


                // Continues if both tip and tail doesn't lie within bounding box
                if ((GeometryMath.aabb(bounding[0], bounding[1], bullet.position) == false) && (GeometryMath.aabb(bounding[0], bounding[1], bullet_tip) == false)) continue;

                System.out.println("bounding box");

                // Step 2: Check line intersection for each segment of the asteroid
                for (int v = 0; v<asteroid.vertices.length; v++) {
                    Vector a = asteroid.vertices[v];
                    
                    int b_index = (v+1>=asteroid.vertices.length) ? 0 : v+1; // Doesn't overflow the array
                    Vector b = asteroid.vertices[b_index];

                    boolean intersects = GeometryMath.line_intersect(a, b, bullet.position, bullet_tip);
                    //if (intersects) { System.out.println("no way"); }
                }
            }
        }

        // Update timers
        world.shoot_timer.Update(world.delta_time);
        world.shotgun_timer.Update(world.delta_time);
    }

    public static void main(String[] args) throws InterruptedException {
        InputHandler input_handler = new InputHandler();

        WorldContext world = new WorldContext();
        world.player = new Entity();
        world.input_handler = input_handler;

        MyFrame frame = new MyFrame(world);
        frame.addKeyListener(input_handler);

        // Define player model
        world.player.vertices = new Vector[] {new Vector(-5,-5), new Vector(0,0), new Vector(5,-5), new Vector(0,10), new Vector(-5,-5)};
        world.player.position.x = 0;
        world.player.position.y = 0;

        // Test
        world.CreateAsteroid(1, new Vector(50,50));

        System.out.println(GeometryMath.aabb(new Vector(10,10), new Vector(0,0), new Vector(5,5)));
        Vector[] bb =world.asteroids[0].GetBoundingBox();
        bb[0].print();
        bb[1].print();

        while (true) {
            update(world);
            frame.repaint();
            Thread.sleep(16); // default is 16
        }
    }
}