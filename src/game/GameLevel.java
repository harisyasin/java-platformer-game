package game;

import city.cs.engine.*;

/**
 * An abstract class representing a general game level.
 * Subclasses must define how the level is populated and
 * what background image it uses.
 */
public abstract class GameLevel {
    protected World world;
    protected Player player;
    protected Game game;
    protected Score score;
    protected Health health;

    /**
     * Constructs a new GameLevel.
     * @param game the main Game instance
     * @param score the Score tracker
     * @param health the Health tracker
     */
    public GameLevel(Game game, Score score, Health health) {
        this.game = game;
        this.score = score;
        this.health = health;
        world = new World();
        player = new Player(world);
    }

    /**
     * Populates the level with platforms, enemies, collectibles, etc.
     */
    public abstract void populate();

    /**
     * Returns the background image file path for the level.
     * @return the background image path
     */
    public abstract String getBackgroundImage();

    /**
     * Gets the world for this level.
     * @return the world instance
     */
    public World getWorld() {
        return world;
    }

    /**
     * Gets the player character for this level.
     * @return the player instance
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the score tracker for this level.
     * @return the score instance
     */
    public Score getScore() {
        return score;
    }

    /**
     * Gets the health tracker for this level.
     * @return the health instance
     */
    public Health getHealth() {
        return health;
    }
}
