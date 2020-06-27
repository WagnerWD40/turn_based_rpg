package sistema.systems.graphics;

import sistema.characters.Actor;
import sistema.systems.Game;

public class EnemyEntity extends Entity {

    Game game;
    Actor character;

    public EnemyEntity(Game game, String ref, int x, int y, Actor character) {
        super(ref, x, y);

        this.game = game;
        this.character = character;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Actor getCharacter() {
        return character;
    }

    public void setCharacter(Actor character) {
        this.character = character;
    }
}
