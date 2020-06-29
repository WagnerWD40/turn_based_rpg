package sistema.systems.battle;

import sistema.characters.EnemyCharacter;
import sistema.characters.PlayerCharacter;

import java.util.ArrayList;
import java.util.Arrays;

public class BattleMenu {

    private ArrayList<PlayerCharacter> playerCharacters;
    private ArrayList<EnemyCharacter> enemyCharacters;
    private boolean acceptPlayerCommands = false;
    private ArrayList<String> menuOptions= new ArrayList<>(Arrays.asList(
            "Attack", "Magic", "Item", "Defend"
    ));

    private int selectedCommand = 0;
    private int selectedEnemy = 0;

    public BattleMenu(ArrayList<PlayerCharacter> playerCharacters, ArrayList<EnemyCharacter> enemyCharacters) {
        this.playerCharacters = playerCharacters;
        this.enemyCharacters = enemyCharacters;
    }

    public int getSelectedCommand() {
        return selectedCommand;
    }

    public void setSelectedCommand(int selectedCommand) {
        this.selectedCommand = selectedCommand;
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

}
