package sistema.characters;

import sistema.systems.graphics.Sprite;
import sistema.systems.graphics.SpriteStore;

import java.awt.*;
import java.util.Objects;

public abstract class Actor {

    private int id;
    private String name;
    private int hpMax;
    private int hpCurrent;
    private int attack;
    private int speed;
    private int initiative;
    private Sprite sprite;

    private double x;
    private double y;

    public Actor(String name, int hpMax, int hpCurrent, int speed, String mainBattleSprite) {
        this.name = name;
        this.hpMax = hpMax;
        this.hpCurrent = hpCurrent;
        this.speed = speed;
        this.sprite = SpriteStore.get().getSprite(mainBattleSprite);
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
        if (hpCurrent > 0) {
            return hpCurrent;
        }
        return 0;

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

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getSpriteHeight() {
        return sprite.getHeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return id == actor.id &&
                hpMax == actor.hpMax &&
                hpCurrent == actor.hpCurrent &&
                attack == actor.attack &&
                speed == actor.speed &&
                initiative == actor.initiative &&
                Double.compare(actor.x, x) == 0 &&
                Double.compare(actor.y, y) == 0 &&
                Objects.equals(name, actor.name) &&
                Objects.equals(sprite, actor.sprite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hpMax, hpCurrent, attack, speed, initiative, sprite, x, y);
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void attackOtherActor(Actor actor) {

        actor.setHpCurrent(actor.getHpCurrent() - attack);
        System.out.println(this + " gave " + actor + " " + attack + " points of damage.");
        System.out.println(actor + " have " + actor.getHpCurrent() + " HP left.");
    }

    public void draw(Graphics g) {
        sprite.draw(g, (int) x, (int) y);
    }
}
