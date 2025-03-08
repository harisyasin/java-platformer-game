package game;

import java.awt.*;

public class Health {
    private int maxHealth;
    private int currentHealth;
    private Game game;

    public Health(int maxHealth, Game game) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.game = game;
    }

    public void takeDamage(int damage) {
        currentHealth -= damage;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
        System.out.println("Health: " + currentHealth);

        if (currentHealth == 0) {
            System.out.println("Player died! Resetting game...");
            game.resetGame();
        }
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    // Render health bar
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
