package game;

import city.cs.engine.*;

public abstract class GameLevel {
    protected World world;
    protected Player player;
    protected Game game;
    protected Score score;
    protected Health health;

    public GameLevel(Game game, Score score, Health health) {
        this.game = game;
        this.score = score;
        this.health = health;
        world = new World();
        player = new Player(world);
    }

    public abstract void populate();
    public abstract String getBackgroundImage();

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
}
