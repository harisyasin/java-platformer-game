package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    //private final float leftBound = 221;
    //private final float rightBound = 277;

    public Skeleton(World world, Player player, Health health) {
        super(world, shape);
        this.player = player;
        this.health = health;

        setPosition(new Vec2(275, 6));
        addImage(rightImage);

        world.addStepListener(this);
        addCollisionListener(this);

        // Shoot bullets every 1 seconds
        shootTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shoot();
            }
        });
        shootTimer.start();
    }

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

    @Override
    public void postStep(StepEvent e) {}

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player) {
            health.takeDamage(30);
            System.out.println("Player hit by skeleton! Health: " + health.getCurrentHealth());
        }
    }

    private void shoot() {
        Vec2 velocity = movingRight ? new Vec2(18, 0) : new Vec2(-18, 0);
        Vec2 offset = movingRight ? new Vec2(1f, 0) : new Vec2(-1f, 0);
        Vec2 spawnPosition = getPosition().add(offset);
        new Bullet(getWorld(), spawnPosition, velocity, health, this); // reuse Bullet class
    }

    private void swapImage(BodyImage image) {
        removeAllImages();
        addImage(image);
    }

    public void takeDamage(int amount) {
        healthPoints -= amount;
        if (healthPoints <= 0) {
            destroy();
            shootTimer.stop();
            System.out.println("Skeleton defeated!");
        }
    }
}
