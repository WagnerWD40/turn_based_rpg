package sistema.systems.graphics;

import sistema.systems.battle.Battle;

public class BackgroundEntity extends Entity {

    Battle battle;

    public BackgroundEntity(Battle battle, String ref, int x, int y) {
        super(ref, x, y);

        this.battle = battle;
    }
}
