package game;

import city.cs.engine.*;
import javax.swing.*;

// Game class
public class Game {
    private GameLevel currentLevel;
    private GameView view;
    private JFrame frame;
    private Timer cameraTimer;
    private Score score;
    private Health health;
    private DebugViewer debugViewer;

// Constructor
    public Game() {
        frame = new JFrame("City Game");

        // Set up initial score and health
        score = new Score();
        health = new Health(100, this);

        // Start with Level 1
        currentLevel = new Level3(this, score, health);
        currentLevel.populate();

        // Create view and attach to player
        view = new GameView(currentLevel.getWorld(), 800, 600, currentLevel.getPlayer(), score, health);

        frame.setContentPane(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(new Controls(currentLevel.getPlayer(), this));

        // Start camera update timer
        cameraTimer = new Timer(1, e -> view.updateCamera());
        cameraTimer.start();

        debugViewer = new DebugViewer(currentLevel.getWorld(), 500, 500);

        currentLevel.getWorld().start();
    }

    // To switch levels
    public void goToNextLevel() {
        currentLevel.getWorld().stop();
        frame.removeKeyListener(frame.getKeyListeners()[0]);

        if (currentLevel instanceof Level1) {
            currentLevel = new Level2(this, score, health);
        } else if (currentLevel instanceof Level2) {
            currentLevel = new Level3(this, score, health);
        } else {
            System.out.println("All levels completed!");
            System.exit(0);
        }

        currentLevel.populate();
        view.setWorld(currentLevel.getWorld());
        view.setPlayer(currentLevel.getPlayer());
        view.setBackgroundImage(currentLevel.getBackgroundImage());

        // Reattach key listener to new player
        frame.addKeyListener(new Controls(currentLevel.getPlayer(), this));

        // Update DebugViewer
        debugViewer.setWorld(currentLevel.getWorld());

        currentLevel.getWorld().start();
    }

    // Reset game
    public void resetGame() {
        System.out.println("Resetting game...");
        currentLevel.getWorld().stop();
        frame.dispose();
        if (debugViewer != null) {
            debugViewer.dispose(); // Close the debug viewer
        }
        new Game();
    }

    public static void main(String[] args) {
        new Game();
    }

}
