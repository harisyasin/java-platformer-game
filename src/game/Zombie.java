package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Zombie is a basic enemy that patrols between two points and damages the player.
 */
public class Zombie extends Walker implements StepListener, CollisionListener {
    private static final Shape zombieShape = new BoxShape(1.2f, 1.8f);
    private static final BodyImage leftImage = new BodyImage("data/zombieleft.gif", 3.6f);
    private static final BodyImage rightImage = new BodyImage("data/zombieright.gif", 3.6f);

    private float leftBound = -2;
    private float rightBound = 25;
    private float speed = 10;
    private boolean movingRight = true;
    private Health health;

    /**
     * Constructs a Zombie enemy.
     * @param world the world the enemy exists in.
     * @param health the player's health system to attack.
     */
    public Zombie(World world, Health health) {
        super(world, zombieShape);
        this.health = health;
        setPosition(new Vec2(leftBound, 10));
        addImage(leftImage);

        world.addStepListener(this);
        addCollisionListener(this);
        startWalking(speed);
    }

    /**
     * Controls patrol movement between left and right bounds.
     * @param stepEvent the step event.
     */
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

    /**
     * No action after stepping.
     * @param stepEvent the step event.
     */
    @Override
    public void postStep(StepEvent stepEvent) {}

    /**
     * Handles collision with the player.
     * @param e the collision event.
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player) {
            health.takeDamage(10);
            System.out.println("Player hit by zombie!");
        }
    }

    /**
     * Swaps the Zombie's facing image.
     * @param image the new image.
     */
    private void swapImage(BodyImage image) {
        removeAllImages();
        addImage(image);
    }
}
