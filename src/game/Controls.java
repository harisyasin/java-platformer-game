package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Handles player input using the keyboard.
 * Allows player movement, jumping, shooting, and resetting the game.
 */
public class Controls implements KeyListener {
    private final Player player;
    private final Game game;

    /**
     * Creates a new Controls object for handling keyboard input.
     * @param player the player being controlled
     * @param game the current game instance
     */
    public Controls(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    /**
     * Handles key press events.
     * W = Jump, A = Move Left, D = Move Right, SPACE = Shoot, ESC = Reset Game.
     * @param e the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> player.jump();          // Jump
            case KeyEvent.VK_A -> player.moveLeft();       // Move left
            case KeyEvent.VK_D -> player.moveRight();      // Move right
            case KeyEvent.VK_ESCAPE -> game.resetGame();   // Reset the game
            case KeyEvent.VK_SPACE -> player.shoot();      // Shoot a bullet
        }
    }

    /**
     * Handles key release events.
     * Stops player movement when left or right key is released.
     * @param e the key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
            player.stopMoving();   // Stop moving left/right
        }
    }

    /**
     * Not used. (Required by interface.)
     * @param e the key event
     */
    @Override
    public void keyTyped(KeyEvent e) {}
}
