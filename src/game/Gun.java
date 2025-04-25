package game;

import city.cs.engine.*;

import java.util.Stack;

public class Gun extends DynamicBody implements CollisionListener {
    private static final Shape shape = new BoxShape(1f, 0.5f);
    private static final BodyImage image = new BodyImage("data/gun.png", 2);
    private Player player;
    private StaticBody barrier;

    public Gun(World world, Player player, StaticBody barrier) {
        super(world, shape);
        this.player = player;
        this.barrier = barrier;
        addImage(image);
        addCollisionListener(this);
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player) {
            player.pickUpGun(); // Enable shooting
            if (barrier != null) {
                barrier.destroy(); // Destroy the barrier
            }
            this.destroy();
            System.out.println("Player picked up the gun!");
        }
    }
}
