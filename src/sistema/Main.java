package sistema;

import sistema.characters.EnemyCharacter;
import sistema.characters.PlayerCharacter;
import sistema.systems.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {


        Random die = new Random();

        PlayerCharacter player1 = new PlayerCharacter("Player", 100, 100, 10, "sprites/terra/standing_still.png");
        player1.setAttack(15);
        PlayerCharacter player2 = new PlayerCharacter("Player", 100, 100, 10, "sprites/terra/esper_standing_still.png");
        player2.setAttack(15);
        EnemyCharacter enemy1 = new EnemyCharacter("Goblin", 30, 30, 3, "sprites/enemies/goblin.png");
        EnemyCharacter enemy2 = new EnemyCharacter("Goblin", 30, 30, 3,"sprites/enemies/goblin.png");
        EnemyCharacter enemy3 = new EnemyCharacter("Goblin", 30, 30, 3, "sprites/enemies/goblin.png");
        EnemyCharacter enemy4 = new EnemyCharacter("Goblin", 30, 30, 3, "sprites/enemies/goblin.png");
        enemy1.setAttack(10);
        enemy2.setAttack(10);

        ArrayList<PlayerCharacter> playerGroup = new ArrayList<PlayerCharacter>();
        ArrayList<EnemyCharacter> enemyGroup = new ArrayList<EnemyCharacter>();

        playerGroup.add(player1);
        playerGroup.add(player2);
        enemyGroup.add(enemy1);
        enemyGroup.add(enemy2);
        enemyGroup.add(enemy3);
        enemyGroup.add(enemy4);

//        Battle battle = new Battle(playerGroup, enemyGroup);
//        battle.initialize();
//        battle.battleStart();
//

        Game game = new Game(playerGroup, enemyGroup);

        game.gameLoop();

    }
}
