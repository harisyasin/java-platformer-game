package game;

import city.cs.engine.BodyImage;
import city.cs.engine.DynamicBody;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Handles keyboard input for character movement.
 */
public class Controls implements KeyListener {
    private DynamicBody character;
    private final BodyImage upImage = new BodyImage("data/charup.png", 8);
    private final BodyImage downImage = new BodyImage("data/chardown.png", 8);
    private final BodyImage leftImage = new BodyImage("data/charleft.png", 8);
    private final BodyImage rightImage = new BodyImage("data/charright.png", 8);
    private final BodyImage defaultImage = new BodyImage("data/char.png", 8);
    private Timer resetTimer;

    public Controls(DynamicBody character) {
        this.character = character;
    }

    private void resetCharacterImage() {
        if (resetTimer != null) {
            resetTimer.cancel();
        }
        resetTimer = new Timer();
        resetTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                character.removeAllImages();
                character.addImage(defaultImage);
            }
        }, 2000); // Reset after 2 seconds
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                character.setLinearVelocity(character.getLinearVelocity().add(new org.jbox2d.common.Vec2(0, 10))); // Jump
                character.removeAllImages();
                character.addImage(upImage);
            }
            case KeyEvent.VK_A -> {
                character.setLinearVelocity(new org.jbox2d.common.Vec2(-5, character.getLinearVelocity().y)); // Left
                character.removeAllImages();
                character.addImage(leftImage);
            }
            case KeyEvent.VK_D -> {
                character.setLinearVelocity(new org.jbox2d.common.Vec2(5, character.getLinearVelocity().y)); // Right
                character.removeAllImages();
                character.addImage(rightImage);
            }
            case KeyEvent.VK_S -> {
                character.setLinearVelocity(new org.jbox2d.common.Vec2(0, -5)); // Duck (go down)
                character.removeAllImages();
                character.addImage(downImage);
            }
        }
        resetCharacterImage();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A, KeyEvent.VK_D -> character.setLinearVelocity(new org.jbox2d.common.Vec2(0, character.getLinearVelocity().y)); // Stop horizontal movement
            case KeyEvent.VK_S -> character.setLinearVelocity(new org.jbox2d.common.Vec2(0, 0)); // Stop downward movement
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used but required by KeyListener
    }
}
