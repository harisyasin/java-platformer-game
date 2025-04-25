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
    private int levelIndex = 0;
    // 0 = menu, 1 = Level1, 2 = Level2, 3 = Level3

    // Constructor
    public Game() {
        // Launch the main menu
        SwingUtilities.invokeLater(() -> new MainMenu(this).setVisible(true));
    }

    // Start the game
    public void startGame() {
        frame = new JFrame("City Game");

        score = new Score();
        health = new Health(100, this);

        // Start Level 1
        levelIndex = 1;
        loadLevel();

        view = new GameView(currentLevel.getWorld(), 800, 600, currentLevel.getPlayer(), score, health);
        frame.setContentPane(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(new Controls(currentLevel.getPlayer(), this));

        cameraTimer = new Timer(1, e -> view.updateCamera());
        cameraTimer.start();

        debugViewer = new DebugViewer(currentLevel.getWorld(), 500, 500);
        currentLevel.getWorld().start();
    }

    // Goes to next level
    public void goToNextLevel() {
        currentLevel.getWorld().stop();
        frame.removeKeyListener(frame.getKeyListeners()[0]);

        levelIndex++;

        if (levelIndex == 2) {
            currentLevel = new Level2(this, score, health);
        } else if (levelIndex == 3) {
            currentLevel = new Level3(this, score, health);
        } else {
            frame.dispose();
            new EndScreen(this, score.getScore()).setVisible(true);
            return;
        }

        currentLevel.populate();
        view.setWorld(currentLevel.getWorld());
        view.setPlayer(currentLevel.getPlayer());
        view.setBackgroundImage(currentLevel.getBackgroundImage());

        frame.addKeyListener(new Controls(currentLevel.getPlayer(), this));
        debugViewer.setWorld(currentLevel.getWorld());
        currentLevel.getWorld().start();
    }

    // Load the level
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

    // Restart the game
    public void resetGame() {
        currentLevel.getWorld().stop();
        frame.dispose();
        if (debugViewer != null) debugViewer.dispose();
        new Game();
    }

    public static void main(String[] args) {
        new Game();
    }
}