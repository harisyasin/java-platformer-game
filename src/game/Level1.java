package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Level1 extends GameLevel {
    public Level1(Game game, Score score, Health health) {
        super(game, score, health);
    }

    @Override
    public String getBackgroundImage() {
        return "data/background1.png";
    }

    @Override
    public void populate() {
        // Position player
        player.setPosition(new Vec2(-25, -8f));

        // Ground platforms
        Shape ground = new BoxShape(7, 0.5f);
        new StaticBody(world, ground).setPosition(new Vec2(-21f, -11.5f));
        new StaticBody(world, ground).setPosition(new Vec2(76f, -20f));
        new StaticBody(world, ground).setPosition(new Vec2(120, 1));

        // Suspended platforms
        Shape platformShape = new BoxShape(2, 0.5f);
        new StaticBody(world, platformShape).setPosition(new Vec2(-20, -7f));
        new StaticBody(world, platformShape).setPosition(new Vec2(-15, -3f));
        new StaticBody(world, platformShape).setPosition(new Vec2(-20, 2f));
        new StaticBody(world, platformShape).setPosition(new Vec2(-15, 7f));
        new StaticBody(world, platformShape).setPosition(new Vec2(42, 2f));
        new StaticBody(world, platformShape).setPosition(new Vec2(52, 2f));
        new StaticBody(world, platformShape).setPosition(new Vec2(62, 2f));

        // Longer ground platform
        Shape longShape = new BoxShape(20, 0.5f);
        new StaticBody(world, longShape).setPosition(new Vec2(13, 7));

        // Walls
        Shape wallShape1 = new BoxShape(0.5f, 8);
        new StaticBody(world, wallShape1).setPosition(new Vec2(70, -6));

        Shape wallShape2 = new BoxShape(0.5f, 14);
        new StaticBody(world, wallShape2).setPosition(new Vec2(74, 0));

        // Thin blocks
        Shape thinShape = new BoxShape(0.5f, 3f);
        new StaticBody(world, thinShape).setPosition(new Vec2(88, -18));
        new StaticBody(world, thinShape).setPosition(new Vec2(94, -14));
        new StaticBody(world, thinShape).setPosition(new Vec2(100, -10));
        new StaticBody(world, thinShape).setPosition(new Vec2(106, -6));

        // Collectibles
        new Collectible(world, score, 20).setPosition(new Vec2(-16, -2));
        new Collectible(world, score, 20).setPosition(new Vec2(-15, 8));
        new Collectible(world, score, 20).setPosition(new Vec2(29, 8));
        new Collectible(world, score, 20).setPosition(new Vec2(62, 3));
        new Collectible(world, score, 20).setPosition(new Vec2(120, 3));

        // Spikes
        new Spike(world, health).setPosition(new Vec2(-19, -5.5f));
        new Spike(world, health).setPosition(new Vec2(-14, -1.5f));
        new Spike(world, health).setPosition(new Vec2(-21f, 3.5f));
        new Spike(world, health).setPosition(new Vec2(-6f, 8.5f));
        new Spike(world, health).setPosition(new Vec2(32f, 8.5f));

        // Zombie (enemy)
        new Zombie(world, health);

        //Portal
        Portal portal = new Portal(getWorld(), game);
        portal.setPosition(new Vec2(125, 3.5f));
    }

}
