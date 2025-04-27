package game;

import city.cs.engine.*;

/**
 * The Gun class represents a collectible gun item in the game.
 * When the player collides with it, they pick up the gun and
 * an optional barrier is destroyed.
 */
public class Gun extends DynamicBody implements CollisionListener {
    private static final Shape shape = new BoxShape(1f, 0.5f);
    private static final BodyImage image = new BodyImage("data/gun.png", 2);
    private Player player;
    private StaticBody barrier;

    /**
     * Constructs a Gun object.
     * @param world the game world
     * @param player the player who can pick up the gun
     * @param barrier a barrier to destroy upon pickup (optional)
     */
    public Gun(World world, Player player, StaticBody barrier) {
        super(world, shape);
        this.player = player;
        this.barrier = barrier;
        addImage(image);
        addCollisionListener(this);
    }

    /**
     * Handles collision with the player.
     * @param e the collision event
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player) {
            // Player picks up the gun
            player.pickUpGun();
            Sound.gunPickupSound.play();

            // Destroy the barrier if it exists
            if (barrier != null) {
                barrier.destroy();
            }

            // Remove the gun object
            this.destroy();

            System.out.println("Player picked up the gun!");
        }
    }
}
