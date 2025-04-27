package game;

import javax.swing.*;
import java.awt.*;

/**
 * MainMenu sets up the initial menu screen with buttons to start the game, view instructions, view controls, or close the game.
 */
public class MainMenu extends JFrame {
    private Game game;

    /**
     * Constructs the MainMenu window.
     * @param game the main game object.
     */
    public MainMenu(Game game) {
        this.game = game;

        setTitle("Main Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create menu buttons
        JButton startButton = new JButton("Start Game");
        JButton instructionsButton = new JButton("Instructions");
        JButton controlsButton = new JButton("Controls");
        JButton closeButton = new JButton("Close Game");

        // Add functionality to the Start Game button
        startButton.addActionListener(e -> {
            dispose(); // Close the menu
            game.startGame(); // Start the game
        });

        // Add functionality to the Instructions button
        instructionsButton.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Instructions:\n- This is a parkour game.\n- Complete levels by reaching the portal.\n- Avoid enemies and spikes.\n- Collect items for points.\n- Enjoy!",
                "Instructions", JOptionPane.INFORMATION_MESSAGE));

        // Add functionality to the Controls button
        controlsButton.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Controls:\n- W to jump.\n- A to move left.\n- D to move right.\n- Space Bar to shoot (if you have a gun).\n- Esc to go back to main menu.",
                "Controls", JOptionPane.INFORMATION_MESSAGE));

        // Add functionality to the Close Game button
        closeButton.addActionListener(e -> System.exit(0));

        // Set the layout and add the buttons
        setLayout(new GridLayout(4, 1, 10, 10));
        add(startButton);
        add(instructionsButton);
        add(controlsButton);
        add(closeButton);
    }
}
