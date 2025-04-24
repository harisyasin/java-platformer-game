package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Level2 extends GameLevel {
    public Level2(Game game, Score score, Health health) {
        super(game, score, health);
    }

    @Override
    public String getBackgroundImage() {
        return "data/background2.png";
    }

    @Override
    public void populate() {
        // Start player at a new location
        getPlayer().setPosition(new Vec2(-5, -10));

        // Add different platforms and layout
        Shape ground = new BoxShape(10, 0.5f);
        new StaticBody(world, ground).setPosition(new Vec2(0, -12));
        new StaticBody(world, ground).setPosition(new Vec2(20, -8));
        new StaticBody(world, ground).setPosition(new Vec2(40, -4));

        Shape longground = new BoxShape(18, 0.5f);
        new StaticBody(world, longground).setPosition(new Vec2(68, 0));
        new StaticBody(world, longground).setPosition(new Vec2(112, 4));

        // Add some new floating platforms
        Shape platform = new BoxShape(3, 0.5f);
        new StaticBody(world, platform).setPosition(new Vec2(2.5f, -2.5f));
        new StaticBody(world, platform).setPosition(new Vec2(24, 1.5f));
        new StaticBody(world, platform).setPosition(new Vec2(140, 9));

        Portal portal = new Portal(getWorld(), game);
        portal.setPosition(new Vec2(143, 11.5f));

        // Different arrangement of spikes and collectibles
        new Collectible(world, score, 20).setPosition(new Vec2(2.5f, -1.5f));
        new Collectible(world, score, 20).setPosition(new Vec2(24, 2.5f));
        new Collectible(world, score, 20).setPosition(new Vec2(66.5f, 1));
        new Collectible(world, score, 20).setPosition(new Vec2(112, 5));
        new Collectible(world, score, 20).setPosition(new Vec2(138, 10));

        new Spike(world, health).setPosition(new Vec2(8, -10.5f));
        new Spike(world, health).setPosition(new Vec2(6.5f, -10.5f));
        new Spike(world, health).setPosition(new Vec2(5, -10.5f));
        new Spike(world, health).setPosition(new Vec2(29, -6.5f));
        new Spike(world, health).setPosition(new Vec2(27.5f, -6.5f));
        new Spike(world, health).setPosition(new Vec2(26, -6.5f));
        new Spike(world, health).setPosition(new Vec2(33, -2.5f));
        new Spike(world, health).setPosition(new Vec2(34.5f, -2.5f));
        new Spike(world, health).setPosition(new Vec2(36, -2.5f));
        new Spike(world, health).setPosition(new Vec2(37.5f, -2.5f));
        new Spike(world, health).setPosition(new Vec2(44.5f, -2.5f));
        new Spike(world, health).setPosition(new Vec2(46f, -2.5f));
        new Spike(world, health).setPosition(new Vec2(47.5f, -2.5f));
        new Spike(world, health).setPosition(new Vec2(53, 1.5f));
        new Spike(world, health).setPosition(new Vec2(54.5f, 1.5f));
        new Spike(world, health).setPosition(new Vec2(56, 1.5f));
        new Spike(world, health).setPosition(new Vec2(61, 1.5f));
        new Spike(world, health).setPosition(new Vec2(62.5f, 1.5f));
        new Spike(world, health).setPosition(new Vec2(64, 1.5f));
        new Spike(world, health).setPosition(new Vec2(69, 1.5f));
        new Spike(world, health).setPosition(new Vec2(70.5f, 1.5f));
        new Spike(world, health).setPosition(new Vec2(72, 1.5f));
        new Spike(world, health).setPosition(new Vec2(77, 1.5f));
        new Spike(world, health).setPosition(new Vec2(78.5f, 1.5f));
        new Spike(world, health).setPosition(new Vec2(80, 1.5f));

        new SuperZombie(world, health);

        System.out.println("Level 2 populated successfully.");
    }
}
