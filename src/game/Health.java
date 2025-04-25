package game;

import java.awt.*;

public class Health {
    private int maxHealth;
    private int currentHealth;
    private Game game;

    // Constructor to create health
    public Health(int maxHealth, Game game) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.game = game;
    }

    // Take damage
    public void takeDamage(int damage) {
        currentHealth -= damage;
        Sound.damageSound.play();
        if (currentHealth < 0) {
            currentHealth = 0;
        }
        System.out.println("Health: " + currentHealth);

        // Reset game if health is 0
        if (currentHealth == 0) {
            Sound.deathSound.play();
            System.out.println("Player died! Resetting game...");
            game.resetGame();
        }
    }

    // Return current health
    public int getCurrentHealth() {
        return currentHealth;
    }

    // Draw health bar
    public void draw(Graphics2D g) {
        int barWidth = 200;
        int barHeight = 20;
        int x = 20;
        int y = 20;

        // Background bar
        g.setColor(Color.GRAY);
        g.fillRect(x, y, barWidth, barHeight);

        // Current health
        g.setColor(Color.RED);
        int healthWidth = (int) ((currentHealth / (double) maxHealth) * barWidth);
        g.fillRect(x, y, healthWidth, barHeight);

        // Border
        g.setColor(Color.BLACK);
        g.drawRect(x, y, barWidth, barHeight);
    }
}
