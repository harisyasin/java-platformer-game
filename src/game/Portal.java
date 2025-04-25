package game;

import city.cs.engine.*;

// Portal class
public class Portal extends StaticBody implements SensorListener {
    private Game game;
    private Sensor sensor;

    // Constructor
    public Portal(World world, Game game) {
        super(world);
        this.game = game;

        // Create a sensor for the portal
        sensor = new Sensor(this, new BoxShape(1f, 2f));
        sensor.addSensorListener(this);

        // Add portal image
        addImage(new BodyImage("data/portal.png", 4));
    }

    // Contact
    @Override
    public void beginContact(SensorEvent e) {
        if (e.getContactBody() instanceof Player) {
            System.out.println("Player entered portal! Switching level...");
            Sound.portalSound.play();
            game.goToNextLevel();
        }
    }

    @Override
    public void endContact(SensorEvent e) {

    }
}