package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Zombie extends Walker implements StepListener, CollisionListener {
    private static final Shape zombieShape = new BoxShape(1.2f, 1.8f);
    private static final BodyImage leftImage = new BodyImage("data/zombieleft.gif", 3.6f);
    private static final BodyImage rightImage = new BodyImage("data/zombieright.gif", 3.6f);

    private float leftBound = -2;
    private float rightBound = 25;
    private float speed = 10;
    private boolean movingRight = true;
    private Health health;

    // Create a zombie
    public Zombie(World world, Health health) {
        super(world, zombieShape);
        this.health = health;
        setPosition(new Vec2(leftBound, 10));
        addImage(leftImage);

        world.addStepListener(this);
        addCollisionListener(this);

        startWalking(speed);
    }

    // Move the zombie left and right
    @Override
    public void preStep(StepEvent stepEvent) {
        float x = getPosition().x;

        if (movingRight && x >= rightBound) {
            movingRight = false;
            startWalking(-speed);
            swapImage(leftImage);
        } else if (!movingRight && x <= leftBound) {
            movingRight = true;
            startWalking(speed);
            swapImage(rightImage);
        }
    }

    // Not used
    @Override
    public void postStep(StepEvent stepEvent) {
    }

    // When the zombie collides with the player
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player) {
            health.takeDamage(50);
            System.out.println("Player hit by zombie! Health: " + health.getCurrentHealth());
        }
    }

    // Swap the image of the zombie
    private void swapImage(BodyImage image) {
        removeAllImages();
        addImage(image);
    }
}
