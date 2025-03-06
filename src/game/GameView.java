package game;

import city.cs.engine.DynamicBody;
import city.cs.engine.UserView;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;
import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {
    private DynamicBody character;
    private Image background;

    public GameView(World world, int width, int height, DynamicBody character) {
        super(world, width, height);
        this.character = character;

        // Load the background image
        background = new ImageIcon("data/background.png").getImage();
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void updateCamera() {
        if (character != null) {
            this.setCentre(character.getPosition()); // Center camera on character
        }
    }
}
