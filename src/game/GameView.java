package game;

import city.cs.engine.UserView;
import city.cs.engine.World;
import javax.swing.*;
import java.awt.*;
import org.jbox2d.common.Vec2;

/**
 * The GameView class displays the game's graphical view,
 * including the background, player, health bar, and score.
 */
public class GameView extends UserView {
    private Player player;
    private Image background;
    private Score score;
    private Health health;

    /**
     * Constructs the GameView.
     * @param world the game world
     * @param width the view width
     * @param height the view height
     * @param player the player character
     * @param score the score tracker
     * @param health the health tracker
     */
    public GameView(World world, int width, int height, Player player, Score score, Health health) {
        super(world, width, height);
        this.player = player;
        this.score = score;
        this.health = health;

        // Load the background image
        background = new ImageIcon("data/background1.png").getImage();
    }

    /**
     * Draws foreground elements like health bar and score.
     */
    @Override
    protected void paintForeground(Graphics2D g) {
        super.paintForeground(g);

        // Draw the health bar
        health.draw(g);

        // Draw the score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score.getScore(), 20, 60);
    }

    /**
     * Sets a new player to follow.
     * @param newPlayer the new player
     */
    public void setPlayer(Player newPlayer) {
        this.player = newPlayer;
    }

    /**
     * Changes the background image.
     * @param path the file path to the new background image
     */
    public void setBackgroundImage(String path) {
        background = new ImageIcon(path).getImage();
    }

    /**
     * Draws the background image.
     */
    @Override
    protected void paintBackground(Graphics2D g) {
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * Smoothly updates the camera to follow the player.
     */
    public void updateCamera() {
        if (player != null) {
            Vec2 currentCenter = this.getCentre();
            Vec2 targetCenter = player.getPosition();
            float t = 0.1f; // Interpolation factor (smoothness)
            Vec2 newCenter = new Vec2(
                    currentCenter.x * (1 - t) + targetCenter.x * t,
                    currentCenter.y * (1 - t) + targetCenter.y * t
            );
            this.setCentre(newCenter);
        }
    }
}
