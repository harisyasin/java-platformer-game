package game;

import javax.swing.*;
import java.awt.*;

/**
 * The EndScreen class displays the end-of-game screen,
 * showing the final score and providing options to return
 * to the main menu or exit the game.
 */
public class EndScreen extends JFrame {

    /**
     * Constructs the EndScreen window.
     * @param game the main Game instance
     * @param finalScore the final score achieved by the player
     */
    public EndScreen(Game game, int finalScore) {
        setTitle("Game Over");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create labels and buttons
        JLabel endLabel = new JLabel("Game Finished!", SwingConstants.CENTER);
        JLabel scoreLabel = new JLabel("Score: " + finalScore, SwingConstants.CENTER);
        JButton mainMenuBtn = new JButton("Main Menu");
        JButton exitBtn = new JButton("Exit");

        // Main Menu button returns to MainMenu
        mainMenuBtn.addActionListener(e -> {
            dispose();
            new MainMenu(game).setVisible(true);
        });

        // Exit button closes the program
        exitBtn.addActionListener(e -> System.exit(0));

        // Set layout and add components
        setLayout(new GridLayout(4, 1, 10, 10));
        add(endLabel);
        add(scoreLabel);
        add(mainMenuBtn);
        add(exitBtn);
    }
}
