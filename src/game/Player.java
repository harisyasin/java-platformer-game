package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;


public class Player extends Walker {
    private static final Shape playerShape = new BoxShape(0.9f, 1.5f);
    private static final BodyImage idleImage = new BodyImage("data/idle.gif", 4);
    private static final BodyImage leftImage = new BodyImage("data/walkleft.gif", 4);
    private static final BodyImage rightImage = new BodyImage("data/walkright.gif", 4);

    public Player(World world) {
        super(world, playerShape);
        addImage(idleImage);
    }

    public void moveLeft() {
        startWalking(-5);
        setImage(leftImage);
    }

    public void moveRight() {
        startWalking(5);
        setImage(rightImage);
    }

    public void stopMoving() {
        setLinearVelocity(new Vec2(0, getLinearVelocity().y));
        stopWalking();
        setImage(idleImage);
    }


    public void jump() {
        if (getLinearVelocity().y >= -0.05 && getLinearVelocity().y <= 0.05) {
            setLinearVelocity(new Vec2(getLinearVelocity().x, 10));
        }
    }

    private void setImage(BodyImage image) {
        removeAllImages();
        addImage(image);
    }
}