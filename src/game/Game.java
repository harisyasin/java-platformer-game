package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {
    private World world;
    private JFrame frame;

    public Game() {
        // Initialize instance variables properly
        world = new World();
        frame = new JFrame("City Game");

//        // Create ground platform
//        Shape shape = new BoxShape(7, 0.5f);
//        StaticBody ground = new StaticBody(world, shape);
//        ground.setPosition(new Vec2(-21f, -11.5f));

        // Create player as a Walker
        Player player = new Player(world);
        player.setPosition(new Vec2(-25, -8f));

        Score score = new Score();
        Health health = new Health(100, this);

        // Create ground platform
        Shape ground = new BoxShape(7, 0.5f);
        new StaticBody(world, ground).setPosition(new Vec2(-21f, -11.5f));

        // Create suspended platforms
        Shape platformShape = new BoxShape(2, 0.5f);
        new StaticBody(world, platformShape).setPosition(new Vec2(-20, -7f));
        new StaticBody(world, platformShape).setPosition(new Vec2(-15, -3f));
        new StaticBody(world, platformShape).setPosition(new Vec2(-20, 2f));
        new StaticBody(world, platformShape).setPosition(new Vec2(-15, 7f));
        new StaticBody(world, platformShape).setPosition(new Vec2(42, 2f));
        new StaticBody(world, platformShape).setPosition(new Vec2(52, 2f));
        new StaticBody(world, platformShape).setPosition(new Vec2(62, 2f));

        // Create ground platform
        Shape bigplatform = new BoxShape(20, 0.5f);
        new StaticBody(world, bigplatform).setPosition(new Vec2(13, 7));


        // Add collectibles
        new Collectible(world, score, 10).setPosition(new Vec2(-16, -2));
        new Collectible(world, score, 10).setPosition(new Vec2(-15, 8));
        new Collectible(world, score, 10).setPosition(new Vec2(29, 8));
        new Collectible(world, score, 10).setPosition(new Vec2(62, 3));

        // Add spikes
        new Spike(world, health).setPosition(new Vec2(-19, -5.5f));
        new Spike(world, health).setPosition(new Vec2(-14, -1.5f));
        new Spike(world, health).setPosition(new Vec2(-21f, 3.5f));
        new Spike(world, health).setPosition(new Vec2(-6f, 8.5f));
        new Spike(world, health).setPosition(new Vec2(32f, 8.5f));

        // Create game view and attach to player
        GameView view = new GameView(world, 800, 600, player, score, health);

        // Update camera position
        Timer timer = new Timer(10, e -> view.updateCamera());
        timer.start();

        // Add view to JFrame
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        frame.addKeyListener(new Controls(player));

        // Debug viewer (optional)
        new DebugViewer(world, 500, 500);

        // Start world simulation
        world.start();
    }

    /** Reset the game. */
    public void resetGame() {
        System.out.println("Resetting game...");
        world.stop();
        frame.dispose();
        new Game();
    }

    /** Run the game. */
    public static void main(String[] args) {
        new Game();
    }
}
