package sistema.systems.graphics;

import java.text.MessageFormat;

public class BattleSlot {
    /**
     * Marks one of the default positions for characters in the battlefield
     * */

    private int id;
    private int x;
    private int y;
    private boolean isPlayerSlot;

    public BattleSlot(int id, int x, int y, boolean isPlayerSlot) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.isPlayerSlot = isPlayerSlot;
    }

    @Override
    public String toString() {
        String side = isPlayerSlot ? "Player" : "Enemy";
        return MessageFormat.format("{0} Battle slot ID {1} (x: {2}, y: {3})", side, id, x, y);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public boolean isPlayerSlot() {
        return isPlayerSlot;
    }

    public void setPlayerSlot(boolean playerSlot) {
        isPlayerSlot = playerSlot;
    }

    public void setY(int y) {
        this.y = y;
    }
}
