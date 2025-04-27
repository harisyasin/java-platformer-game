package game;

import city.cs.engine.*;
import javax.swing.*;

/**
 * The Game class manages the overall game flow,
 * including starting the game, switching levels,
 * and resetting or ending the game.
 */
public class Game {
    private GameLevel currentLevel;
    private GameView view;
    private JFrame frame;
    private Timer cameraTimer;
    private Score score;
    private Health health;
    private DebugViewer debugViewer;
    private int levelIndex = 0;
    // 0 = menu, 1 = Level1, 2 = Level2, 3 = Level3

    /**
     * Constructs the Game instance and opens the main menu.
     */
    public Game() {
        // Open the main menu when the game starts
        SwingUtilities.invokeLater(() -> new MainMenu(this).setVisible(true));
    }

    /**
     * Starts the game by loading Level 1, setting up the view,
     * and starting the game world.
     */
    public void startGame() {
        frame = new JFrame("City Game");

        score = new Score();
        health = new Health(100, this);

        // Start with Level 1
        levelIndex = 1;
        loadLevel();

        // Set up the game view
        view = new GameView(currentLevel.getWorld(), 800, 600, currentLevel.getPlayer(), score, health);
        frame.setContentPane(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(new Controls(currentLevel.getPlayer(), this));

        // Update camera smoothly
        cameraTimer = new Timer(1, e -> view.updateCamera());
        cameraTimer.start();

        // Show physics debugging (optional)
        debugViewer = new DebugViewer(currentLevel.getWorld(), 500, 500);
        currentLevel.getWorld().start();
    }

    /**
     * Moves the game to the next level.
     * Shows EndScreen if no more levels.
     */
    public void goToNextLevel() {
        // Stop the current world
        currentLevel.getWorld().stop();
        frame.removeKeyListener(frame.getKeyListeners()[0]);

        // Move to the next level
        levelIndex++;

        if (levelIndex == 2) {
            currentLevel = new Level2(this, score, health);
        } else if (levelIndex == 3) {
            currentLevel = new Level3(this, score, health);
        } else {
            // No more levels - show end screen
            frame.dispose();
            new EndScreen(this, score.getScore()).setVisible(true);
            return;
        }

        // Load and start new level
        currentLevel.populate();
        view.setWorld(currentLevel.getWorld());
        view.setPlayer(currentLevel.getPlayer());
        view.setBackgroundImage(currentLevel.getBackgroundImage());

        frame.addKeyListener(new Controls(currentLevel.getPlayer(), this));
        debugViewer.setWorld(currentLevel.getWorld());
        currentLevel.getWorld().start();
    }

    /**
     * Loads the current level based on levelIndex.
     */
    private void loadLevel() {
        if (levelIndex == 1) {
            currentLevel = new Level1(this, score, health);
        } else if (levelIndex == 2) {
            currentLevel = new Level2(this, score, health);
        } else {
            currentLevel = new Level3(this, score, health);
        }
        currentLevel.populate();
    }

    /**
     * Resets the game back to the main menu.
     */
    public void resetGame() {
        currentLevel.getWorld().stop();
        frame.dispose();
        if (debugViewer != null) debugViewer.dispose();
        new Game();
    }

    /**
     * The main method to launch the game.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Game();
    }
}
