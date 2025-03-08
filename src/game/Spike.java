package game;

import city.cs.engine.*;

public class Spike extends StaticBody implements CollisionListener {
    private static final Shape spikeShape = new BoxShape(0.6f, 1.4f);
    private static final BodyImage spikeImage = new BodyImage("data/spike.png", 2);
    private Health health;

    public Spike(World world, Health health) {
        super(world, spikeShape);
        this.health = health;
        addImage(spikeImage);
        addCollisionListener(this);
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player) {
            health.takeDamage(100); // Reduce health by 50
            System.out.println("Player hit spike! Health: " + health.getCurrentHealth());
        }
    }
}
