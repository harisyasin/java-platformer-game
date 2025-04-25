package game;

import city.cs.engine.*;

// Gun class
public class Gun extends DynamicBody implements CollisionListener {
    private static final Shape shape = new BoxShape(1f, 0.5f);
    private static final BodyImage image = new BodyImage("data/gun.png", 2);
    private Player player;
    private StaticBody barrier;

    // Constructor
    public Gun(World world, Player player, StaticBody barrier) {
        super(world, shape);
        this.player = player;
        this.barrier = barrier;
        addImage(image);
        addCollisionListener(this);
    }

    // Collision event
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player) {
            player.pickUpGun();
            Sound.gunPickupSound.play();
            if (barrier != null) {
                barrier.destroy();
            }
            this.destroy();
            System.out.println("Player picked up the gun!");
        }
    }
}
