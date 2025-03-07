package game;

import city.cs.engine.BodyImage;
import city.cs.engine.DynamicBody;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controls implements KeyListener {
    private final DynamicBody character;
    private final BodyImage leftImage = new BodyImage("data/walkleft.gif", 4);
    private final BodyImage rightImage = new BodyImage("data/walkright.gif", 4);
    private final BodyImage defaultImage = new BodyImage("data/idle.gif", 4);

    public Controls(DynamicBody character) {
        this.character = character;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> jump();
            case KeyEvent.VK_A -> move(-5, leftImage);
            case KeyEvent.VK_D -> move(5, rightImage);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
            character.setLinearVelocity(new Vec2(0, character.getLinearVelocity().y));
            updateImage(defaultImage);
        }
    }

    private void move(float speed, BodyImage image) {
        character.setLinearVelocity(new Vec2(speed, character.getLinearVelocity().y));
        updateImage(image);
    }

    private void jump() {
        float yVelocity = character.getLinearVelocity().y;
        if (yVelocity >= -0.05 && yVelocity <= 0.05) {
            character.setLinearVelocity(new Vec2(character.getLinearVelocity().x, 10));
        }
    }

    private void updateImage(BodyImage image) {
        character.removeAllImages();
        character.addImage(image);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }
}
