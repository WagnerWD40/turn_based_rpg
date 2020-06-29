package sistema;

import sistema.characters.EnemyCharacter;
import sistema.characters.PlayerCharacter;
import sistema.systems.battle.Battle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {

        Random die = new Random();

        PlayerCharacter player1 = new PlayerCharacter("Player A", 100, 100, 12, "sprites/terra/standing_still.png");
        player1.setAttack(15);
        PlayerCharacter player2 = new PlayerCharacter("Player B", 100, 100, 10, "sprites/terra/esper_standing_still.png");
        player2.setAttack(15);
        EnemyCharacter enemy1 = new EnemyCharacter("Goblin A", 30, 30, 6, "sprites/enemies/goblin.png");
        EnemyCharacter enemy2 = new EnemyCharacter("Goblin B", 30, 30, 4,"sprites/enemies/goblin.png");
        EnemyCharacter enemy3 = new EnemyCharacter("Goblin C", 30, 30, 3, "sprites/enemies/goblin.png");
        EnemyCharacter enemy4 = new EnemyCharacter("Goblin D", 30, 30, 8, "sprites/enemies/goblin.png");
        enemy1.setAttack(6);
        enemy2.setAttack(4);
        enemy3.setAttack(3);
        enemy4.setAttack(8);

        ArrayList<PlayerCharacter> playerGroup = new ArrayList<PlayerCharacter>();
        ArrayList<EnemyCharacter> enemyGroup = new ArrayList<EnemyCharacter>();

        playerGroup.add(player1);
        playerGroup.add(player2);
        enemyGroup.add(enemy1);
        enemyGroup.add(enemy2);
        enemyGroup.add(enemy3);
        enemyGroup.add(enemy4);



        Battle battle = new Battle(playerGroup, enemyGroup);

        battle.gameLoop();

    }
}
