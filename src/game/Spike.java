package game;

import city.cs.engine.*;

/**
 * Spike is a static hazard that damages the player on contact.
 */
public class Spike extends StaticBody implements CollisionListener {
    private static final Shape spikeShape = new BoxShape(0.6f, 1.4f);
    private static final BodyImage spikeImage = new BodyImage("data/spike.png", 2);
    private Health health;

    /**
     * Constructs a Spike object.
     * @param world the world in which the spike exists.
     * @param health the player's health system to damage.
     */
    public Spike(World world, Health health) {
        super(world, spikeShape);
        this.health = health;
        addImage(spikeImage);
        addCollisionListener(this);
    }

    /**
     * Handles collisions with the spike.
     * @param e the collision event.
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player) {
            health.takeDamage(15);
            System.out.println("Player hit spike!");
        }
    }
}
