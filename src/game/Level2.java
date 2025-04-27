package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Level2 sets up the second level of the game.
 * This level includes new platforms, more spikes, and harder enemy challenges.
 */
public class Level2 extends GameLevel {

    /**
     * Constructs Level2.
     * @param game The main game object.
     * @param score The player's score tracker.
     * @param health The player's health tracker.
     */
    public Level2(Game game, Score score, Health health) {
        super(game, score, health);
    }

    /**
     * Returns the background image for Level2.
     * @return the file path of the background image.
     */
    @Override
    public String getBackgroundImage() {
        return "data/background2.png";
    }

    /**
     * Populates Level2 with platforms, spikes, enemies, collectibles, and portal.
     */
    @Override
    public void populate() {
        // Position the player
        getPlayer().setPosition(new Vec2(-5, -10));

        // Create ground platforms
        Shape ground = new BoxShape(10, 0.5f);
        new StaticBody(world, ground).setPosition(new Vec2(0, -12));
        new StaticBody(world, ground).setPosition(new Vec2(20, -8));
        new StaticBody(world, ground).setPosition(new Vec2(40, -4));

        // Create longer ground platforms
        Shape longground = new BoxShape(18, 0.5f);
        new StaticBody(world, longground).setPosition(new Vec2(68, 0));
        new StaticBody(world, longground).setPosition(new Vec2(112, 4));

        // Create smaller platforms
        Shape platform = new BoxShape(3, 0.5f);
        new StaticBody(world, platform).setPosition(new Vec2(2.5f, -2.5f));
        new StaticBody(world, platform).setPosition(new Vec2(24, 1.5f));
        new StaticBody(world, platform).setPosition(new Vec2(140, 9));

        // Create portal to next level
        Portal portal = new Portal(getWorld(), game);
        portal.setPosition(new Vec2(143, 11.5f));

        // Add collectibles
        new Collectible(world, score, 20).setPosition(new Vec2(2.5f, -1.5f));
        new Collectible(world, score, 20).setPosition(new Vec2(24, 2.5f));
        new Collectible(world, score, 20).setPosition(new Vec2(41, -3));
        new Collectible(world, score, 20).setPosition(new Vec2(66.5f, 1));
        new Collectible(world, score, 20).setPosition(new Vec2(138, 10));

        // Add spikes along platforms
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

        // Add SuperZombie as a tougher enemy
        new SuperZombie(world, health);
    }
}
