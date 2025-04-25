package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.swing.Timer;

// Bullet class
public class Bullet extends DynamicBody implements CollisionListener {
    private static final Shape bulletShape = new BoxShape(0.3f, 0.3f);
    private static final BodyImage bulletImage = new BodyImage("data/bullet.png", 0.6f);

    private Health health;
    private Walker shooter;

    // Constructor
    public Bullet(World world, Vec2 position, Vec2 velocity, Health health, Walker shooter) {
        super(world, bulletShape);
        this.health = health;
        this.shooter = shooter;

        addImage(bulletImage);
        setPosition(position);
        setLinearVelocity(velocity);
        addCollisionListener(this);
        setGravityScale(0);

        Timer timer = new Timer(3000, e -> destroy());
        timer.setRepeats(false);
        timer.start();
    }

    // Collision detection
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == shooter) {
            return;
        }

        // Destroy the bullet if it collides with another bullet
        if (e.getOtherBody() instanceof Bullet) {
            e.getOtherBody().destroy();
            destroy();
        }

        if (e.getOtherBody() instanceof Player) {
            health.takeDamage(10);
            System.out.println("Enemy hit by bullet!");
            destroy();
        }
        else if (e.getOtherBody() instanceof Skeleton) {
            Skeleton skeleton = (Skeleton) e.getOtherBody();
            skeleton.takeDamage(20);
            destroy();
        }
    }

}
