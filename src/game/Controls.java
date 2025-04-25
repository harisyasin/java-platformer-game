package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Controls class
public class Controls implements KeyListener {
    private final Player player;
    private final Game game;

    // Constructor
    public Controls(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    // Handle key presses
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> player.jump();
            case KeyEvent.VK_A -> player.moveLeft();
            case KeyEvent.VK_D -> player.moveRight();
            case KeyEvent.VK_R -> game.resetGame();
            case KeyEvent.VK_SPACE -> player.shoot(); // Press space to shoot

        }
    }

    // Stop moving when A or D is released
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
            player.stopMoving();
        }
    }

    // Not used
    @Override
    public void keyTyped(KeyEvent e) {}
}
