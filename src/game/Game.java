package game;

import city.cs.engine.*;
import javax.swing.*;
import java.awt.event.*;

public class Game {
    private GameLevel currentLevel;
    private GameView view;
    private JFrame frame;
    private Timer cameraTimer;

    public Game() {
        frame = new JFrame("City Game");

        // Create and populate level 1
        currentLevel = new Level1(this);
        currentLevel.populate();

        // Create view and attach to player
        view = new GameView(currentLevel.getWorld(), 800, 600, currentLevel.getPlayer(), currentLevel.getScore(), currentLevel.getHealth());

        frame.setContentPane(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(new Controls(currentLevel.getPlayer(), this));

        // Update camera
        cameraTimer = new Timer(5, e -> view.updateCamera());
        cameraTimer.start();

        // Optional debug viewer
        new DebugViewer(currentLevel.getWorld(), 500, 500);

        currentLevel.getWorld().start();
    }

    public void resetGame() {
        System.out.println("Resetting game...");
        currentLevel.getWorld().stop();
        frame.dispose();
        new Game();
    }

    public static void main(String[] args) {
        new Game();
    }
}
