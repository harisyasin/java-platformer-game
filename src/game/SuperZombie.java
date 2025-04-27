package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SuperZombie is a stronger enemy that patrols and shoots at the player.
 */
public class SuperZombie extends Walker implements StepListener, CollisionListener {
    private static final Shape shape = new BoxShape(1.2f, 1.8f);
    private static final BodyImage leftImage = new BodyImage("data/superleft.gif", 3.6f);
    private static final BodyImage rightImage = new BodyImage("data/superright.gif", 3.6f);

    private float leftBound = 95;
    private float rightBound = 130;
    private float speed = 10;
    private boolean movingRight = true;
    private Health health;
    private Timer shootTimer;

    /**
     * Constructs a SuperZombie enemy.
     * @param world the world the enemy exists in.
     * @param health the player's health system to attack.
     */
    public SuperZombie(World world, Health health) {
        super(world, shape);
        this.health = health;
        setPosition(new Vec2(leftBound, 10));
        addImage(leftImage);

        world.addStepListener(this);
        addCollisionListener(this);
        startWalking(speed);

        // Timer to shoot bullets every 2 seconds
        shootTimer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shoot();
            }
        });
        shootTimer.start();
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
            health.takeDamage(20);
            System.out.println("Player hit by superzombie!");
        }
    }

    /**
     * Swaps the SuperZombie's facing image.
     * @param image the new image.
     */
    private void swapImage(BodyImage image) {
        removeAllImages();
        addImage(image);
    }

    /**
     * Shoots a bullet towards the player.
     */
    private void shoot() {
        Vec2 velocity = movingRight ? new Vec2(15, 0) : new Vec2(-15, 0);
        Vec2 offset = movingRight ? new Vec2(1.2f, 0) : new Vec2(-1.2f, 0);
        Vec2 spawnPosition = getPosition().add(offset);
        new Bullet(getWorld(), spawnPosition, velocity, health, this);
    }
}
