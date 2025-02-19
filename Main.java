public class Main {
    public static void update(WorldContext world) {
        world.player.position.x += 1;
    }

    public static void main(String[] args) throws InterruptedException {
        WorldContext world = new WorldContext();
        world.player = new Player();
        MyFrame frame = new MyFrame(world);

        while (true) {
            update(world);
            frame.repaint();
            Thread.sleep(100);
        }
    }
}