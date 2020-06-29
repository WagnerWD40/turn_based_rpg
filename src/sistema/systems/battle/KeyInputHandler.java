package sistema.systems.battle;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInputHandler extends KeyAdapter {

    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean confirmPressed = false;
    private boolean cancelPressed = false;

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isConfirmPressed() {
        return confirmPressed;
    }

    public boolean isCancelPressed() {
        return cancelPressed;
    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            confirmPressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            cancelPressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            confirmPressed = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            cancelPressed = false;
        }
    }

}
