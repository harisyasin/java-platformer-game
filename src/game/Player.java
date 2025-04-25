package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.swing.Timer;

// Player class
public class Player extends Walker {
    private static final Shape playerShape = new BoxShape(0.9f, 1.5f);
    private static final BodyImage idleImage = new BodyImage("data/idle.gif", 4);
    private static final BodyImage leftImage = new BodyImage("data/walkleft.gif", 4);
    private static final BodyImage rightImage = new BodyImage("data/walkright.gif", 4);
    private boolean hasGun = false;
    private boolean canShoot = true;
    private Health health;


    // Constructor to create player
    public Player(World world) {
        super(world, playerShape);
        this.health = health;
        addImage(idleImage);
    }

    // Move the player left
    public void moveLeft() {
        startWalking(-5);
        setImage(leftImage);
    }

    // Move the player right
    public void moveRight() {
        startWalking(5);
        setImage(rightImage);
    }

    // Stop the player from moving
    public void stopMoving() {
        setLinearVelocity(new Vec2(0, getLinearVelocity().y));
        stopWalking();
        setImage(idleImage);
    }

    // Make the player jump
    public void jump() {
        if (getLinearVelocity().y >= -0.05 && getLinearVelocity().y <= 0.05) {
            setLinearVelocity(new Vec2(getLinearVelocity().x, 10));
            Sound.jumpSound.play();
        }
    }

    // Player picks up gun
    public void pickUpGun() {
        hasGun = true;
    }

    // Shoot gun
    public void shoot() {
        if (hasGun && canShoot) {
            canShoot = false; // Disable shooting
            Sound.shootSound.play();
            Vec2 direction = getLinearVelocity().x >= 0 ? new Vec2(1, 0) : new Vec2(-1, 0);
            Vec2 velocity = direction.mul(20);
            Vec2 spawnOffset = direction.mul(1.5f);
            Vec2 spawnPosition = getPosition().add(spawnOffset);

            new Bullet(getWorld(), spawnPosition, velocity, health, this);

            // Start cooldown timer
            Timer cooldownTimer = new Timer(200, e -> canShoot = true);
            cooldownTimer.setRepeats(false);
            cooldownTimer.start();
        }
    }


    // Swap the image of the player
    private void setImage(BodyImage image) {
        removeAllImages();
        addImage(image);
    }
}