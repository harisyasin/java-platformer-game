package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bullet extends DynamicBody implements CollisionListener {
    private static final Shape bulletShape = new BoxShape(0.3f, 0.3f);
    private static final BodyImage bulletImage = new BodyImage("data/bullet.png", 0.6f);

    private Health health;

    public Bullet(World world, Vec2 position, Vec2 velocity, Health health) {
        super(world, bulletShape);
        this.health = health;
        addImage(bulletImage);
        setPosition(position);
        setLinearVelocity(velocity);
        addCollisionListener(this);


        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destroy();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player) {
            health.takeDamage(50);
            System.out.println("Player hit by bullet! Health: " + health.getCurrentHealth());
            destroy();
        }
    }
}
