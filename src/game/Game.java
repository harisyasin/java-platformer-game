package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.swing.*;

public class Game {
    private World world;
    private JFrame frame;

    public Game() {

        // Create the game world
        world = new World();
        frame = new JFrame("City Game");

        // Create player as a Walker
        Player player = new Player(world);
        player.setPosition(new Vec2(-25, -8f));

        // Create score and health
        Score score = new Score();
        Health health = new Health(100, this);

        // Create ground platforms
        Shape ground = new BoxShape(7, 0.5f);
        new StaticBody(world, ground).setPosition(new Vec2(-21f, -11.5f));
        new StaticBody(world, ground).setPosition(new Vec2(76f, -20f));
        new StaticBody(world, ground).setPosition(new Vec2(120, 1));

        // Create suspended platforms
        Shape platformShape = new BoxShape(2, 0.5f);
        new StaticBody(world, platformShape).setPosition(new Vec2(-20, -7f));
        new StaticBody(world, platformShape).setPosition(new Vec2(-15, -3f));
        new StaticBody(world, platformShape).setPosition(new Vec2(-20, 2f));
        new StaticBody(world, platformShape).setPosition(new Vec2(-15, 7f));
        new StaticBody(world, platformShape).setPosition(new Vec2(42, 2f));
        new StaticBody(world, platformShape).setPosition(new Vec2(52, 2f));
        new StaticBody(world, platformShape).setPosition(new Vec2(62, 2f));

        // Create longer ground platform
        Shape longShape = new BoxShape(20, 0.5f);
        new StaticBody(world, longShape).setPosition(new Vec2(13, 7));

        //Create walls
        Shape wallShape1 = new BoxShape(0.5f, 8);
        new StaticBody(world, wallShape1).setPosition(new Vec2(70, -6));

        Shape wallShape2 = new BoxShape(0.5f, 14);
        new StaticBody(world, wallShape2).setPosition(new Vec2(74, 0));

        //Create thin blocks
        Shape thinShape = new BoxShape(0.5f, 3f);
        new StaticBody(world, thinShape).setPosition(new Vec2(88, -18));
        new StaticBody(world, thinShape).setPosition(new Vec2(94, -14));
        new StaticBody(world, thinShape).setPosition(new Vec2(100, -10));
        new StaticBody(world, thinShape).setPosition(new Vec2(106, -6));

        // Add collectibles
        new Collectible(world, score, 20).setPosition(new Vec2(-16, -2));
        new Collectible(world, score, 20).setPosition(new Vec2(-15, 8));
        new Collectible(world, score, 20).setPosition(new Vec2(29, 8));
        new Collectible(world, score, 20).setPosition(new Vec2(62, 3));
        new Collectible(world, score, 20).setPosition(new Vec2(120, 3));

        // Add spikes
        new Spike(world, health).setPosition(new Vec2(-19, -5.5f));
        new Spike(world, health).setPosition(new Vec2(-14, -1.5f));
        new Spike(world, health).setPosition(new Vec2(-21f, 3.5f));
        new Spike(world, health).setPosition(new Vec2(-6f, 8.5f));
        new Spike(world, health).setPosition(new Vec2(32f, 8.5f));

        // Add zombie (enemy)
        new Zombie(world, health);

        // Create game view and attach to player
        GameView view = new GameView(world, 800, 600, player, score, health);

        // Update camera position
        Timer timer = new Timer(5, e -> view.updateCamera());
        timer.start();

        // Set up JFrame
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        // Add key listener to JFrame
        frame.addKeyListener(new Controls(player, this));

        // Debug viewer (optional)
        new DebugViewer(world, 500, 500);

        // Start the world simulation
        world.start();
    }

    // Reset the game
    public void resetGame() {
        System.out.println("Resetting game...");
        world.stop();;
        frame.dispose();
        new Game();
    }

    // Run the game
    public static void main(String[] args) {
        new Game();
    }
}
