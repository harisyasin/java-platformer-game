package game;

import city.cs.engine.*;

public class Collectible extends DynamicBody implements CollisionListener {
    private static final Shape shape = new CircleShape(0.5f);
    private static final BodyImage image = new BodyImage("data/collectible.png", 1);

    private Score score;
    private int scoreValue;

    // Constructor to create a collectible
    public Collectible(World world, Score score, int scoreValue) {
        super(world, shape);
        addImage(image);
        this.score = score;
        this.scoreValue = scoreValue;
        addCollisionListener(this);
    }

    // Collision event between the collectible and the player
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player) {
            score.addPoints(scoreValue);
            this.destroy();
        }
    }
}
