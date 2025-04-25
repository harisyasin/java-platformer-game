package game;

import city.cs.engine.*;

public class Portal extends StaticBody implements SensorListener {
    private Game game;
    private Sensor sensor;

    public Portal(World world, Game game) {
        super(world);
        this.game = game;

        // Create a sensor for the portal
        sensor = new Sensor(this, new BoxShape(1f, 2f));
        sensor.addSensorListener(this); // Use SensorListener for sensor events

        // Add visual appearance to the portal
        addImage(new BodyImage("data/portal.png", 4));
    }

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
        // Optional: handle when player exits portal area
    }
}