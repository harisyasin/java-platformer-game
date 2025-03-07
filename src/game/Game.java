package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Your main game entry point
 */
public class Game {
    /** Initialise a new Game. */
    public Game() {
        // Create game world
        World world = new World();

        // Create ground platform
        Shape shape = new BoxShape(50, 0.5f);
        StaticBody ground = new StaticBody(world, shape);
        ground.setPosition(new Vec2(0f, -11.5f));

        // Create suspended platforms
        Shape platformShape = new BoxShape(2, 0.5f);
        new StaticBody(world, platformShape).setPosition(new Vec2(-20, -7f));
        new StaticBody(world, platformShape).setPosition(new Vec2(-15, -3f));

        // Create player as a Walker
        Player player = new Player(world);
        player.setPosition(new Vec2(-25, -8f));

        Score score = new Score();

        // Add collectibles
        new Collectible(world, score, 10).setPosition(new Vec2(-20, -5));
        new Collectible(world, score, 10).setPosition(new Vec2(-16, -1));
        new Collectible(world, score, 10).setPosition(new Vec2(15, 2));

        // Create game view and attach to player
        GameView view = new GameView(world, 800, 600, player);

        // Update camera position
        Timer timer = new Timer(10, e -> view.updateCamera());
        timer.start();

        // Create JFrame
        final JFrame frame = new JFrame("City Game");
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

    /** Run the game. */
    public static void main(String[] args) {
        new Game();
    }
}