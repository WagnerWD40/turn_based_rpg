package sistema.systems.graphics;

import sistema.characters.Actor;

import java.awt.*;

public abstract class Entity {

    /** Entity is an object that can be drawn in the screen and have a sprite */

    protected double x;

    protected double y;

    protected Sprite sprite;

    protected double dx;

    protected double dy;

    public Entity(String ref, int x , int y) {
        this.sprite = SpriteStore.get().getSprite(ref);
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void draw(Graphics g) {
        sprite.draw(g, (int) x, (int) y);
    }

}
