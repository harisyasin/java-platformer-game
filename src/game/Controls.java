package game;

import city.cs.engine.BodyImage;
import city.cs.engine.DynamicBody;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles keyboard input for character movement.
 */
public class Controls implements KeyListener {
    private DynamicBody character;
    private final BodyImage upImage = new BodyImage("data/jump.gif", 4);
    private final BodyImage leftImage = new BodyImage("data/walkleft.gif", 4);
    private final BodyImage rightImage = new BodyImage("data/walkright.gif", 4);
    private final BodyImage defaultImage = new BodyImage("data/idle.gif", 4);

    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean isJumping = false;

    public Controls(DynamicBody character) {
        this.character = character;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                //if (character.getLinearVelocity().y <= 0.01 && !isJumping) { // Only jump if standing on ground
                if (character.getLinearVelocity().y >= -0.05 && character.getLinearVelocity().y <= 0.05 && !isJumping) { // Only jump if standing on ground
                    character.setLinearVelocity(new org.jbox2d.common.Vec2(character.getLinearVelocity().x, 10));
                    character.removeAllImages();
                    character.addImage(upImage);
                    isJumping = true;

                    // Reset jump animation after 1 second
                    new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            isJumping = false;
                            updateImage();
                        }
                    }).start();
                }
            }
            case KeyEvent.VK_A -> {
                movingLeft = true;
                updateMovement();
            }
            case KeyEvent.VK_D -> {
                movingRight = true;
                updateMovement();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> {
                movingLeft = false;
                updateMovement();
            }
            case KeyEvent.VK_D -> {
                movingRight = false;
                updateMovement();
            }
        }
    }

    private void updateMovement() {
        float xVelocity = 0;
        if (movingLeft) xVelocity = -5;
        if (movingRight) xVelocity = 5;
        character.setLinearVelocity(new org.jbox2d.common.Vec2(xVelocity, character.getLinearVelocity().y));
        updateImage();
    }

    private void updateImage() {
        character.removeAllImages();
        if (isJumping) {
            character.addImage(upImage);
        } else if (movingLeft) {
            character.addImage(leftImage);
        } else if (movingRight) {
            character.addImage(rightImage);
        } else {
            character.addImage(defaultImage);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used but required by KeyListener
    }
}
