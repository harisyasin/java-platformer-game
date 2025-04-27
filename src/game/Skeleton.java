package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Skeleton is an enemy character that follows the player and shoots projectiles.
 */
public class Skeleton extends Walker implements StepListener, CollisionListener {
    private static final Shape shape = new BoxShape(1f, 1.5f);
    private static final BodyImage leftImage = new BodyImage("data/skeletonleft.gif", 3f);
    private static final BodyImage rightImage = new BodyImage("data/skeletonright.gif", 3f);

    private float speed = 10;
    private boolean movingRight = true;
    private Player player;
    private Health health;
    private Timer shootTimer;
    private int healthPoints = 100;

    /**
     * Constructs a Skeleton enemy.
     * @param world the world the skeleton exists in.
     * @param player the player the skeleton will target.
     * @param health the health system to interact with.
     */
    public Skeleton(World world, Player player, Health health) {
        super(world, shape);
        this.player = player;
        this.health = health;

        setPosition(new Vec2(275, 6));
        addImage(rightImage);

        world.addStepListener(this);
        addCollisionListener(this);

        // Create a timer to shoot bullets every second
        shootTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shoot();
            }
        });
        shootTimer.start();
    }

    /**
     * Moves towards the player each step.
     * @param e the step event.
     */
    @Override
    public void preStep(StepEvent e) {
        Vec2 playerPos = player.getPosition();
        Vec2 myPos = getPosition();
        float diff = playerPos.x - myPos.x;

        if (Math.abs(diff) > 1f) {
            movingRight = diff > 0;
            startWalking(movingRight ? speed : -speed);
            swapImage(movingRight ? rightImage : leftImage);
        } else {
            stopWalking();
        }
    }

    /**
     * No action needed after the step.
     * @param e the step event.
     */
    @Override
    public void postStep(StepEvent e) {}

    /**
     * Handles collision events.
     * @param e the collision event.
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player) {
            health.takeDamage(30);
            System.out.println("Player hit by skeleton!");
        }
    }

    /**
     * Shoots a bullet towards the player.
     */
    private void shoot() {
        Vec2 velocity = movingRight ? new Vec2(18, 0) : new Vec2(-18, 0);
        Vec2 offset = movingRight ? new Vec2(1f, 0) : new Vec2(-1f, 0);
        Vec2 spawnPosition = getPosition().add(offset);
        new Bullet(getWorld(), spawnPosition, velocity, health, this); // Reuse Bullet class
    }

    /**
     * Swaps the skeleton's displayed image.
     * @param image the new image.
     */
    private void swapImage(BodyImage image) {
        removeAllImages();
        addImage(image);
    }

    /**
     * Applies damage to the skeleton.
     * @param amount the amount of damage.
     */
    public void takeDamage(int amount) {
        healthPoints -= amount;
        if (healthPoints <= 0) {
            destroy();
            shootTimer.stop();
            System.out.println("Skeleton defeated!");
        }
    }
}
