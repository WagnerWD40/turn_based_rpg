package sistema.systems.battle;

import sistema.characters.EnemyCharacter;
import sistema.characters.PlayerCharacter;
import sistema.systems.graphics.Cursor;
import sistema.systems.graphics.Entity;
import sistema.systems.graphics.Text;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class BattleMenu {

    private ArrayList<PlayerCharacter> playerCharacters;
    private ArrayList<EnemyCharacter> enemyCharacters;
    private boolean acceptPlayerCommands = false;
    private ArrayList<String> menuOptions= new ArrayList<>(Arrays.asList(
            "Attack", "Magic", "Item", "Defend"
    ));

    private int selectedCommand = 0; // first index of menuOptions (Attack)
    private int selectedEnemy = 0;
    private KeyInputHandler playerController = new KeyInputHandler();

    final private int firstLineYPos = 520;
    final private int nameStartingXPos = 350;
    final private int hpStartingXPos = 600;
    final private int menuStartingXPos = 80;
    final private int lineHeight = 40;

    final private Entity cursor = new Cursor(
            "sprites/cursor.png",
            menuStartingXPos - 60, //(cursor size) 51 + (margin) 9
            firstLineYPos// center in the line
    );

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
        this.selectedEnemy = enemyCharacters.size() > 1 ? selectedEnemy : 0;
    }

    public boolean getAcceptPlayerCommands() {
        return acceptPlayerCommands;
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

        cursor.setY(firstLineYPos + selectedCommand * lineHeight);
        cursor.draw(g);
    }
}
