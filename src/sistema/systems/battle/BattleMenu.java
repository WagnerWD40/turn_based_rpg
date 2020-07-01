package sistema.systems.battle;

import sistema.characters.EnemyCharacter;
import sistema.characters.PlayerCharacter;
import sistema.systems.graphics.Cursor;
import sistema.systems.graphics.Entity;
import sistema.systems.graphics.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BattleMenu {

    private ArrayList<PlayerCharacter> playerCharacters;
    private ArrayList<EnemyCharacter> enemyCharacters;
    private ArrayList<String> menuOptions= new ArrayList<>(Arrays.asList(
            "Attack", "Magic", "Item", "Defend"
    ));

    private int selectedCommand = 0; // first index of menuOptions (Attack)
    private int selectedEnemy = 0;
    private KeyInputHandler playerController = new KeyInputHandler();

    // Text dimensions
    final private int firstLineYPos = 520;
    final private int nameStartingXPos = 350;
    final private int hpStartingXPos = 600;
    final private int menuStartingXPos = 80;
    final private int lineHeight = 40;

    // Cursor Controls
    final private int cursorStartingXPos = menuStartingXPos - 60;
    final private Entity cursor = new Cursor(
            "sprites/cursor.png",
            cursorStartingXPos, //(cursor size) 51 + (margin) 9
            firstLineYPos// center in the line
    );
    private boolean commandWasSelected = false;
    private boolean enemyWasSelected = false;
    private boolean acceptPlayerCommands = false;

    public BattleMenu(ArrayList<PlayerCharacter> playerCharacters, ArrayList<EnemyCharacter> enemyCharacters) {
        this.playerCharacters = playerCharacters;
        this.enemyCharacters = enemyCharacters;
    }

    public int getSelectedCommand() {
        return selectedCommand;
    }

    public void setSelectedCommand(int selectedCommand) {
        if (selectedCommand > menuOptions.size() - 1) {
            this.selectedCommand = 0;
        } else if (selectedCommand < 0) {
            this.selectedCommand = menuOptions.size() - 1;
        } else {
            this.selectedCommand = selectedCommand;
        }
    }

    public int getSelectedEnemy() {
        return selectedEnemy;
    }

    public void setSelectedEnemy(int selectedEnemy) {
        if (selectedEnemy > enemyCharacters.size() - 1) {
            this.selectedEnemy = 0;
        } else if (selectedEnemy < 0) {
            this.selectedEnemy = menuOptions.size() - 1;
        } else {
            this.selectedEnemy = selectedCommand;
        }
    }

    public boolean isAcceptPlayerCommands() {
        return acceptPlayerCommands;
    }

    public void setAcceptPlayerCommands(boolean acceptPlayerCommands) {
        this.acceptPlayerCommands = acceptPlayerCommands;
    }

    public boolean isCommandWasSelected() {
        return commandWasSelected;
    }

    public void setCommandWasSelected(boolean commandWasSelected) {
        this.commandWasSelected = commandWasSelected;
    }

    public boolean isEnemyWasSelected() {
        return enemyWasSelected;
    }

    public void setEnemyWasSelected(boolean enemyWasSelected) {
        this.enemyWasSelected = enemyWasSelected;
    }

    public void turnOnPlayerCommand() {
        acceptPlayerCommands = true;
    }

    public void turnOffPlayerCommand() {
        acceptPlayerCommands = false;
    }

    public void draw(Graphics g) {
        for (int i = 0; i < playerCharacters.size(); i++) {
            String characterName = playerCharacters.get(i).getName();
            String characterCurrentHp = Integer.toString(playerCharacters.get(i).getHpCurrent());

            Text name = new Text(characterName, nameStartingXPos, firstLineYPos + (i * lineHeight));
            Text currentHp = new Text(characterCurrentHp, hpStartingXPos, firstLineYPos + (i * lineHeight));

            name.draw(g);
            currentHp.draw(g);
        }

        for (int i = 0; i < menuOptions.size(); i++) {
            Text commandName = new Text(menuOptions.get(i), menuStartingXPos, firstLineYPos + (i * lineHeight));

            commandName.draw(g);
        }

        if (!commandWasSelected && acceptPlayerCommands) {
            cursor.setX(cursorStartingXPos);
            cursor.setY(firstLineYPos + selectedCommand * lineHeight);
        }

        if (commandWasSelected && !enemyWasSelected && acceptPlayerCommands) {
            EnemyCharacter enemy = enemyCharacters.get(selectedEnemy);

            cursor.setX(enemy.getX() - 50);
            cursor.setY(enemy.getY() + enemy.getSpriteHeight() / 2);
        }
    }

    public void drawCursor(Graphics g) {
        cursor.draw(g);
    }
}
