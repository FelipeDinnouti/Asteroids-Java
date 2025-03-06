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

        if ((world.shoot_timer.current_time==0.0f) && (world.input_handler.j_pressed == true)) {
            // Start the timer again
            world.shoot_timer.Fire();

            // Create the bullet
            Bullet bullet = new Bullet(new Vector(world.player.position), world.player.rotation, 0, false);

            // Bullet count is a bad name because it doesn't count bullets,
            // I use it to know what is the first null spot where a bullet could go.
            world.bullets[world.bullet_count] = bullet;
            world.bullet_count += 1;

            // Pushes the player back
            world.player.velocity = world.player.velocity.minus(player_direction.mult(8.0f));
        }

        if ((world.shotgun_timer.current_time==0.0f) && (world.input_handler.h_pressed == true)) {
            // Start the timer again
            world.shotgun_timer.Fire();

            // Create the bullet
            Bullet bullet1 = new Bullet(new Vector(world.player.position), world.player.rotation, 0, false);
            Bullet bullet2 = new Bullet(new Vector(world.player.position), world.player.rotation - 0.2f, 0, false);
            Bullet bullet3 = new Bullet(new Vector(world.player.position), world.player.rotation - 0.1f, 0, false);
            Bullet bullet4 = new Bullet(new Vector(world.player.position), world.player.rotation + 0.1f, 0, false);
            Bullet bullet5 = new Bullet(new Vector(world.player.position), world.player.rotation + 0.2f, 0, false);

            // Bullet count is a bad name because it doesn't count bullets,
            // I use it to know what is the first null spot where a bullet could go.
            world.bullets[world.bullet_count++] = bullet1;
            world.bullets[world.bullet_count++] = bullet2;
            world.bullets[world.bullet_count++] = bullet3;
            world.bullets[world.bullet_count++] = bullet4;
            world.bullets[world.bullet_count++] = bullet5;

            // Pushes the player back
            world.player.velocity = world.player.velocity.minus(player_direction.mult(80.0f));
        }

        // Update bullets
        for (int i = 0; i<world.bullet_count+1; i++) {
            Bullet b = world.bullets[i];
            if (b == null) continue;

            // Decreasing the lifetime of the bullet
            b.lifetime -= world.delta_time;

            // Delete bullet and fill the hole
            if (b.lifetime < 0) {
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
        Vector test = new Vector(5,5);
        Vector rotated = test.rotate(3.57f);
        System.out.print(rotated.x);
        System.out.print(rotated.y);

        while (true) {
            update(world);
            frame.repaint();
            Thread.sleep(16);
        }
    }
}