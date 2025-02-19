public class Main {
    public static void update(WorldContext world) {
        world.player.rotation += 3.0f*world.delta_time;
    }

    public static void main(String[] args) throws InterruptedException {
        WorldContext world = new WorldContext();
        world.player = new Entity();
        MyFrame frame = new MyFrame(world);

        // Define player model:
        world.player.vertices = new Vector[] {new Vector(-5,-5), new Vector(0,0), new Vector(5,-5), new Vector(0,10), new Vector(-5,-5)};
        world.player.position.x = 50;
        world.player.position.y = 50;

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