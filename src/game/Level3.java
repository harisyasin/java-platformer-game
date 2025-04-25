package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

// Level3 class
public class Level3 extends GameLevel {
        public Level3(Game game, Score score, Health health) {
            super(game, score, health);
        }

        // Add background
        @Override
        public String getBackgroundImage() {
            return "data/background3.png";
        }

        @Override
        public void populate() {

            // Position player
            getPlayer().setPosition(new Vec2(-19, 10));

            // Add ground platforms
            Shape ground = new BoxShape(10, 0.5f);
            new StaticBody(world, ground).setPosition(new Vec2(-19, 7));
            new StaticBody(world, ground).setPosition(new Vec2(298, 8));

            // Add more ground platforms
            Shape longground = new BoxShape(20, 0.5f);
            new StaticBody(world, longground).setPosition(new Vec2(13, 7));
            new StaticBody(world, longground).setPosition(new Vec2(175, 4));

            // Add longer ground
            Shape longground2 = new BoxShape(18, 0.5f);
            new StaticBody(world, longground2).setPosition(new Vec2(112, 4));

            // Add very long ground
            Shape longestground = new BoxShape(30, 0.5f);
            new StaticBody(world, longestground).setPosition(new Vec2(249, 4));

            // Add small platforms
            Shape smallPlatform = new BoxShape(2, 0.5f);
            new StaticBody(world, smallPlatform).setPosition(new Vec2(88, 1));
            new StaticBody(world, smallPlatform).setPosition(new Vec2(143, 4));
            new StaticBody(world, smallPlatform).setPosition(new Vec2(205, 8));
            new StaticBody(world, smallPlatform).setPosition(new Vec2(209, 10));
            new StaticBody(world, smallPlatform).setPosition(new Vec2(213, 12));
            new StaticBody(world, smallPlatform).setPosition(new Vec2(217, 14));

            // Add walls
            Shape thinShape = new BoxShape(0.5f, 3f);
            new StaticBody(world, thinShape).setPosition(new Vec2(40, 4));
            new StaticBody(world, thinShape).setPosition(new Vec2(52, 4));
            new StaticBody(world, thinShape).setPosition(new Vec2(64, 4));
            new StaticBody(world, thinShape).setPosition(new Vec2(76, 4));

            // Add more walls
            Shape thinWall = new BoxShape(0.5f, 1);
            new StaticBody(world, thinWall).setPosition(new Vec2(218.5f, 4.5f));
            new StaticBody(world, thinWall).setPosition(new Vec2(279.5f, 4.5f));

            // Add spikes
            new Spike(world, health).setPosition(new Vec2(-10, 8.5f));
            new Spike(world, health).setPosition(new Vec2(-6, 8.5f));
            new Spike(world, health).setPosition(new Vec2(32, 8.5f));
            new Spike(world, health).setPosition(new Vec2(89, 2.5f));

            new Spike(world, health).setPosition(new Vec2(158, 5.5f));
            new Spike(world, health).setPosition(new Vec2(159.5f, 5.5f));
            new Spike(world, health).setPosition(new Vec2(161, 5.5f));
            new Spike(world, health).setPosition(new Vec2(166, 5.5f));
            new Spike(world, health).setPosition(new Vec2(167.5f, 5.5f));
            new Spike(world, health).setPosition(new Vec2(169, 5.5f));
            new Spike(world, health).setPosition(new Vec2(174, 5.5f));
            new Spike(world, health).setPosition(new Vec2(175.3f, 5.5f));
            new Spike(world, health).setPosition(new Vec2(176.6f, 5.5f));
            new Spike(world, health).setPosition(new Vec2(177.9f, 5.5f));
            new Spike(world, health).setPosition(new Vec2(184, 5.5f));
            new Spike(world, health).setPosition(new Vec2(185.3f, 5.5f));
            new Spike(world, health).setPosition(new Vec2(186.6f, 5.5f));
            new Spike(world, health).setPosition(new Vec2(187.9f, 5.5f));

            // Add collectibles
            new Collectible(world, score, 20).setPosition(new Vec2(29, 8));
            new Collectible(world, score, 20).setPosition(new Vec2(88, 2));
            new Collectible(world, score, 20).setPosition(new Vec2(143, 5));
            new Collectible(world, score, 20).setPosition(new Vec2(192, 5));
            new Collectible(world, score, 20).setPosition(new Vec2(298, 9));

            // Add barrier
            StaticBody barrier = new StaticBody(world, new BoxShape(0.5f, 1));
            barrier.setPosition(new Vec2(273, 4.5f));

            // Add gun
            new Gun(world, getPlayer(), barrier).setPosition(new Vec2(217, 15));

            // Portal to finish the level
            Portal portal = new Portal(getWorld(), game);
            portal.setPosition(new Vec2(306, 10.5f));

            // Enemies
            new SuperZombie(world, health);
            new Zombie(world, health);
            new Skeleton(world, player, health);

        }
    }

