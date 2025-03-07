package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controls implements KeyListener {
    private final Player player;

    public Controls(Player player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> player.jump();
            case KeyEvent.VK_A -> player.moveLeft();
            case KeyEvent.VK_D -> player.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
            player.stopMoving();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
