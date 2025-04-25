package game;

import javax.swing.*;
import java.awt.*;

// MainMenu class
public class MainMenu extends JFrame {
    private Game game;

    // Constructor
    public MainMenu(Game game) {
        this.game = game;

        setTitle("Main Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create buttons
        JButton startButton = new JButton("Start Game");
        JButton instructionsButton = new JButton("Instructions");
        JButton controlsButton = new JButton("Controls");
        JButton closeButton = new JButton("Close Game");

        // Add action listeners
        startButton.addActionListener(e -> {
            dispose();
            game.startGame();
        });

        instructionsButton.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Instructions:\n- This is a parkour game. \n- Complete levels by reaching the portal.\n- Avoid enemies and spikes.\n- Collect items for points. \n- Enjoy!",
                "Instructions", JOptionPane.INFORMATION_MESSAGE));

        controlsButton.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Controls:\n- W to jump.\n- A to move left.\n- D to move right.\n- Space Bar to shoot (if you have a gun).\n- Esc to go back to main menu.",
                "Controls", JOptionPane.INFORMATION_MESSAGE));

        closeButton.addActionListener(e -> System.exit(0));

        // Layout
        setLayout(new GridLayout(4, 1, 10, 10));
        add(startButton);
        add(instructionsButton);
        add(controlsButton);
        add(closeButton);
    }
}