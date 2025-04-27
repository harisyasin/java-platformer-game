package game;

import city.cs.engine.SoundClip;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;

/**
 * Sound loads and manages all the audio used in the game.
 */
public class Sound {
    public static SoundClip backgroundMusic;
    public static SoundClip shootSound;
    public static SoundClip jumpSound;
    public static SoundClip damageSound;
    public static SoundClip deathSound;
    public static SoundClip collectibleSound;
    public static SoundClip gunPickupSound;
    public static SoundClip portalSound;

    // Load the sounds when the class is first used
    static {
        try {
            backgroundMusic = new SoundClip("data/sounds/background.wav");
            shootSound = new SoundClip("data/sounds/shoot.wav");
            jumpSound = new SoundClip("data/sounds/jump.wav");
            damageSound = new SoundClip("data/sounds/damage.wav");
            deathSound = new SoundClip("data/sounds/death.wav");
            collectibleSound = new SoundClip("data/sounds/collect.wav");
            gunPickupSound = new SoundClip("data/sounds/gunpickup.wav");
            portalSound = new SoundClip("data/sounds/portal.wav");

            backgroundMusic.setVolume(0.3);
            backgroundMusic.loop(); // Play background music on loop

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            System.out.println("Error loading sound: " + e);
        }
    }
}
