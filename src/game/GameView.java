package game;

import city.cs.engine.UserView;
import city.cs.engine.World;
import javax.swing.*;
import java.awt.*;
import org.jbox2d.common.Vec2;

public class GameView extends UserView {
    private Player player;
    private Image background;
    private Score score;
    private Health health;

    // Constructor to create the game view
    public GameView(World world, int width, int height, Player player, Score score, Health health) {
        super(world, width, height);
        this.player = player;
        this.score =  score;
        this.health =  health;

        // Load the background image
        background = new ImageIcon("data/background1.png").getImage();
    }



    // Draw the foreground
    @Override
    protected void paintForeground(Graphics2D g) {
        super.paintForeground(g);

        // Draw health bar
        health.draw(g);

        // Draw score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score.getScore(), 20, 60);
    }

    public void setPlayer(Player newPlayer) {
        this.player = newPlayer;
    }
    
    public void setBackgroundImage(String path) {
        background = new ImageIcon(path).getImage();
    }

    // Draw the background image
    @Override
    protected void paintBackground(Graphics2D g) {
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Update the camera to follow the player
    public void updateCamera() {
//        if (player != null) {
//            this.setCentre(player.getPosition());
//        }
        if (player != null) {
            Vec2 currentCenter = this.getCentre();
            Vec2 targetCenter = player.getPosition();
            float t = 0.1f; // Interpolation factor (adjust for smoothness)
            Vec2 newCenter = new Vec2(
                    currentCenter.x * (1 - t) + targetCenter.x * t,
                    currentCenter.y * (1 - t) + targetCenter.y * t
            );
            this.setCentre(newCenter);
        }
    }
}
