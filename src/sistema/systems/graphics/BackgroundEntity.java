package sistema.systems.graphics;

import sistema.systems.Game;

public class BackgroundEntity extends Entity {

    Game game;

    public BackgroundEntity(Game game, String ref, int x, int y) {
        super(ref, x, y);

        this.game = game;
    }
}
