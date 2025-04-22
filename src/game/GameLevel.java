package game;

import city.cs.engine.*;

public abstract class GameLevel {
    protected World world;
    protected Player player;
    protected Score score;
    protected Health health;

    public GameLevel(Game game) {
        this.world = new World();
        this.score = new Score();
        this.health = new Health(100, game);
        this.player = new Player(world);
        this.player.setPosition(new org.jbox2d.common.Vec2(-25, -8));
    }

    public abstract void populate(); // Place objects, enemies, etc.

    public World getWorld() {
        return world;
    }

    public Player getPlayer() {
        return player;
    }

    public Score getScore() {
        return score;
    }

    public Health getHealth() {
        return health;
    }

    public abstract boolean isCompleted();
}
