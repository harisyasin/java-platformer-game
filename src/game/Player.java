package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.swing.Timer;

/**
 * Player represents the character controlled by the player in the game.
 * It can move, jump, pick up a gun, and shoot bullets.
 */
public class Player extends Walker {
    private static final Shape playerShape = new BoxShape(0.9f, 1.5f);
    private static final BodyImage idleImage = new BodyImage("data/idle.gif", 4);
    private static final BodyImage leftImage = new BodyImage("data/walkleft.gif", 4);
    private static final BodyImage rightImage = new BodyImage("data/walkright.gif", 4);
    private boolean hasGun = false;
    private boolean canShoot = true;
    private Health health;

    /**
     * Constructs the player character.
     * @param world the game world.
     */
    public Player(World world) {
        super(world, playerShape);
        this.health = health;
        addImage(idleImage);
    }

    /**
     * Moves the player to the left.
     */
    public void moveLeft() {
        startWalking(-6);
        setImage(leftImage);
    }

    /**
     * Moves the player to the right.
     */
    public void moveRight() {
        startWalking(6);
        setImage(rightImage);
    }

    /**
     * Stops the player from moving.
     */
    public void stopMoving() {
        setLinearVelocity(new Vec2(0, getLinearVelocity().y));
        stopWalking();
        setImage(idleImage);
    }

    /**
     * Makes the player jump if they are on the ground.
     */
    public void jump() {
        if (getLinearVelocity().y >= -0.05 && getLinearVelocity().y <= 0.05) {
            setLinearVelocity(new Vec2(getLinearVelocity().x, 10));
            Sound.jumpSound.play();
        }
    }

    /**
     * Allows the player to pick up a gun.
     */
    public void pickUpGun() {
        hasGun = true;
    }

    /**
     * Shoots a bullet if the player has a gun and is able to shoot.
     */
    public void shoot() {
        if (hasGun && canShoot) {
            canShoot = false; // Prevent immediate shooting again
            Sound.shootSound.play();

            // Determine direction based on movement
            Vec2 direction = getLinearVelocity().x >= 0 ? new Vec2(1, 0) : new Vec2(-1, 0);
            Vec2 velocity = direction.mul(20);
            Vec2 spawnOffset = direction.mul(1.5f);
            Vec2 spawnPosition = getPosition().add(spawnOffset);

            new Bullet(getWorld(), spawnPosition, velocity, health, this);

            // Cooldown before being able to shoot again
            Timer cooldownTimer = new Timer(150, e -> canShoot = true);
            cooldownTimer.setRepeats(false);
            cooldownTimer.start();
        }
    }

    /**
     * Changes the player's current image.
     * @param image the new BodyImage to apply.
     */
    private void setImage(BodyImage image) {
        removeAllImages();
        addImage(image);
    }
}
