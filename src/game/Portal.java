package game;

import city.cs.engine.*;

public class Portal extends StaticBody implements CollisionListener {
    private Game game;

    public Portal(World world, Game game) {
        super(world, new BoxShape(1f, 2f));
        this.game = game;
        addCollisionListener(this);
        addImage(new BodyImage("data/portal.png", 4));
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player) {
            System.out.println("Player entered portal! Switching level...");
            game.goToNextLevel();
        }
    }
}
