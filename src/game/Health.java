package game;

import java.awt.*;

/**
 * The Health class manages the player's health points
 * and handles health bar drawing and player death.
 */
public class Health {
    private int maxHealth;
    private int currentHealth;
    private Game game;

    /**
     * Constructs the Health tracker.
     * @param maxHealth the maximum health points
     * @param game the main game instance
     */
    public Health(int maxHealth, Game game) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.game = game;
    }

    /**
     * Reduces health when the player takes damage.
     * @param damage the amount of damage taken
     */
    public void takeDamage(int damage) {
        currentHealth -= damage;
        Sound.damageSound.play();
        System.out.println("Health: " + currentHealth);

        // If health reaches 0, reset the game
        if (currentHealth <= 0) {
            Sound.deathSound.play();
            System.out.println("Player died!");
            game.resetGame();
        }
    }

    /**
     * Returns the current health value.
     * @return the current health points
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Draws the health bar on the screen.
     * @param g the Graphics2D context
     */
    public void draw(Graphics2D g) {
        int barWidth = 200;
        int barHeight = 20;
        int x = 20;
        int y = 20;

        // Draw background (gray)
        g.setColor(Color.GRAY);
        g.fillRect(x, y, barWidth, barHeight);

        // Draw current health (red)
        g.setColor(Color.RED);
        int healthWidth = (int) ((currentHealth / (double) maxHealth) * barWidth);
        g.fillRect(x, y, healthWidth, barHeight);

        // Draw border (black)
        g.setColor(Color.BLACK);
        g.drawRect(x, y, barWidth, barHeight);
    }
}
