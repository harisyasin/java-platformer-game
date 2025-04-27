package game;

import city.cs.engine.*;

/**
 * Portal allows the player to move to the next level when they make contact with it.
 */
public class Portal extends StaticBody implements SensorListener {
    private Game game;
    private Sensor sensor;

    /**
     * Constructs a Portal in the given world.
     * @param world the game world.
     * @param game the main game object.
     */
    public Portal(World world, Game game) {
        super(world);
        this.game = game;

        // Create and attach a sensor to detect player contact
        sensor = new Sensor(this, new BoxShape(1f, 2f));
        sensor.addSensorListener(this);

        // Add portal image
        addImage(new BodyImage("data/portal.png", 4));
    }

    /**
     * Triggered when the player contacts the portal sensor.
     * @param e the sensor event.
     */
    @Override
    public void beginContact(SensorEvent e) {
        if (e.getContactBody() instanceof Player) {
            System.out.println("Player entered portal! Switching level...");
            Sound.portalSound.play();
            game.goToNextLevel();
        }
    }

    /**
     * Triggered when contact ends with the portal sensor.
     * @param e the sensor event.
     */
    @Override
    public void endContact(SensorEvent e) {
        // No action needed when player leaves portal sensor
    }
}
