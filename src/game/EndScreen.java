package game;

import javax.swing.*;
import java.awt.*;

// EndScreen class
public class EndScreen extends JFrame {

    // Constructor
    public EndScreen(Game game, int finalScore) {
        setTitle("Game Over");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Buttons
        JLabel endLabel = new JLabel("Game Finished!", SwingConstants.CENTER);
        JLabel scoreLabel = new JLabel("Score: " + finalScore, SwingConstants.CENTER);
        JButton mainMenuBtn = new JButton("Main Menu");
        JButton exitBtn = new JButton("Exit");

        mainMenuBtn.addActionListener(e -> {
            dispose();
            new MainMenu(game).setVisible(true);
        });

        exitBtn.addActionListener(e -> System.exit(0));

        // Layout
        setLayout(new GridLayout(4, 1, 10, 10));
        add(endLabel);
        add(scoreLabel);
        add(mainMenuBtn);
        add(exitBtn);
    }
}
