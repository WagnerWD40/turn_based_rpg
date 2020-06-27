package sistema.characters;

import java.util.Objects;

public abstract class Actor {

    private int id;
    private String name;
    private int hpMax;
    private int hpCurrent;
    private int attack;
    private int speed;
    private int initiative;
    private String mainBattleSprite;

    public Actor(String name, int hpMax, int hpCurrent, int speed, String mainBattleSprite) {
        this.name = name;
        this.hpMax = hpMax;
        this.hpCurrent = hpCurrent;
        this.speed = speed;
        this.mainBattleSprite = mainBattleSprite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHpMax() {
        return hpMax;
    }

    public void setHpMax(int hpMax) {
        this.hpMax = hpMax;
    }

    public int getHpCurrent() {
        return hpCurrent;
    }

    public void setHpCurrent(int hpCurrent) {
        this.hpCurrent = hpCurrent;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public String getMainBattleSprite() {
        return mainBattleSprite;
    }

    public void setMainBattleSprite(String mainBattleSprite) {
        this.mainBattleSprite = mainBattleSprite;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return id == actor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void attackOtherActor(Actor actor) {
        actor.setHpCurrent(actor.getHpCurrent() - this.attack);
    }
}
