package game;

import city.cs.engine.*;

/**
 * Represents a collectible item that increases the player's score when collected.
 */
public class Collectible extends DynamicBody implements CollisionListener {
    private static final Shape shape = new CircleShape(0.5f);
    private static final BodyImage image = new BodyImage("data/collectible.png", 1);

    private Score score;
    private int scoreValue;

    /**
     * Creates a new collectible object.
     * @param world the world the collectible belongs to
     * @param score the score system to update when collected
     * @param scoreValue how many points the collectible is worth
     */
    public Collectible(World world, Score score, int scoreValue) {
        super(world, shape);
        addImage(image);               // Add collectible image
        this.score = score;
        this.scoreValue = scoreValue;
        addCollisionListener(this);    // Listen for collisions
    }

    /**
     * Handles collision with the player.
     * Awards points and plays sound on collection.
     * @param e the collision event
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player) {
            score.addPoints(scoreValue);    // Increase player's score
            Sound.collectibleSound.play();
            this.destroy();                 // Remove collectible
        }
    }
}
