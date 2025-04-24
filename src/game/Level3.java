package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Level3 extends GameLevel {

        public Level3(Game game, Score score, Health health) {
            super(game, score, health);
        }

        @Override
        public String getBackgroundImage() {
            return "data/background3.png";
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
            new StaticBody(world, ground).setPosition(new Vec2(60, 0));

            // Add some new floating platforms
            Shape platform = new BoxShape(3, 0.5f);
            new StaticBody(world, platform).setPosition(new Vec2(25, 5));
            new StaticBody(world, platform).setPosition(new Vec2(35, 8));
            new StaticBody(world, platform).setPosition(new Vec2(45, 10));

            // Different arrangement of spikes and collectibles
            new Collectible(world, score, 20).setPosition(new Vec2(25, 6));
            new Collectible(world, score, 20).setPosition(new Vec2(35, 9));
            new Collectible(world, score, 20).setPosition(new Vec2(45, 11));

            new Spike(world, health).setPosition(new Vec2(8, -10.5f));
            new Spike(world, health).setPosition(new Vec2(6.5f, -10.5f));
            new Spike(world, health).setPosition(new Vec2(5, -10.5f));
            new Spike(world, health).setPosition(new Vec2(30, -4.5f));
            new Spike(world, health).setPosition(new Vec2(50, -0.5f));

            // New enemies
            new Zombie(world, health);

            System.out.println("Level 2 populated successfully.");
        }
    }

