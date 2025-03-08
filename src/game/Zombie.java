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
    private boolean movingRight = true; // Start moving right
    private Health health;

    public Zombie(World world, Health health) {
        super(world, zombieShape);
        this.health = health;
        setPosition(new Vec2(leftBound, 10));
        addImage(leftImage); // Start with left-facing image

        world.addStepListener(this);
        addCollisionListener(this);

        startWalking(speed); // Start moving
    }

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

    @Override
    public void postStep(StepEvent stepEvent) {
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player) {
            health.takeDamage(50); // Player takes 50 damage
            System.out.println("Player hit by zombie! Health: " + health.getCurrentHealth());
        }
    }

    private void swapImage(BodyImage image) {
        removeAllImages();
        addImage(image);
    }
}
