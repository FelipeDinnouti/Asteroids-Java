public class Main {
    public static void update(WorldContext world) {
        Vector player_direction = new Vector(0,1).rotate(world.player.rotation);

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
        
        if (world.input_handler.k_pressed == true) {
            world.camera_position = world.camera_position.lerp(world.player.position, 3.0f*world.delta_time);
        }
        world.camera_position.print();
    
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