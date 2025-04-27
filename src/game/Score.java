package game;

/**
 * Score manages the player's points during the game.
 */
public class Score {

    private int score;

    /**
     * Constructs a new Score with an initial value of zero.
     */
    public Score() {
        this.score = 0;
    }

    /**
     * Adds a number of points to the current score.
     * @param points the points to add.
     */
    public void addPoints(int points) {
        score += points;
        System.out.println("Score: " + score);
    }

    /**
     * Returns the current score.
     * @return the current score.
     */
    public int getScore() {
        return score;
    }
}
