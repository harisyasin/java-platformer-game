package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.swing.Timer;

/**
 * Represents a bullet fired by a player or an enemy.
 * Handles bullet movement, collision detection, and destruction after a time limit.
 */
public class Bullet extends DynamicBody implements CollisionListener {
    private static final Shape bulletShape = new BoxShape(0.3f, 0.3f);
    private static final BodyImage bulletImage = new BodyImage("data/bullet.png", 0.6f);

    private Health health;
    private Walker shooter;

    /**
     * Creates a new bullet with specified position, velocity, and shooter.
     * @param world the world the bullet belongs to
     * @param position the initial position of the bullet
     * @param velocity the velocity of the bullet
     * @param health the health system to update if a player is hit
     * @param shooter the entity that fired the bullet (to avoid self-collision)
     */
    public Bullet(World world, Vec2 position, Vec2 velocity, Health health, Walker shooter) {
        super(world, bulletShape);
        this.health = health;
        this.shooter = shooter;

        addImage(bulletImage);         // Add bullet image
        setPosition(position);         // Set initial position
        setLinearVelocity(velocity);   // Set initial velocity
        addCollisionListener(this);    // Listen for collisions
        setGravityScale(0);             // Bullets are not affected by gravity

        // Destroy bullet after 3 seconds to avoid clutter
        Timer timer = new Timer(3000, e -> destroy());
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Handles collision events for the bullet.
     * Bullet is destroyed when it hits players, skeletons, or other bullets.
     * @param e the collision event
     */
    @Override
    public void collide(CollisionEvent e) {
        // Ignore collisions with the shooter itself
        if (e.getOtherBody() == shooter) {
            return;
        }

        // Destroy both bullets if a bullet collides with another bullet
        if (e.getOtherBody() instanceof Bullet) {
            e.getOtherBody().destroy();
            destroy();
        }

        // Handle collision with Player
        if (e.getOtherBody() instanceof Player) {
            health.takeDamage(10); // Reduce player's health
            System.out.println("Enemy hit by bullet!");
            destroy();
        }
        // Handle collision with Skeleton
        else if (e.getOtherBody() instanceof Skeleton) {
            Skeleton skeleton = (Skeleton) e.getOtherBody();
            skeleton.takeDamage(20);
            destroy();
        }
    }
}
