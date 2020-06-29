package sistema.systems;

import sistema.characters.Actor;
import sistema.characters.EnemyCharacter;
import sistema.characters.PlayerCharacter;
import sistema.comparators.SortActorByInitiative;

import java.io.IOException;
import java.util.*;

public class OldBattleUnused {

    Random die = new Random();
    LinkedList<Actor> battleOrder = new LinkedList<>();
    List<PlayerCharacter> playerCharacters;
    List<EnemyCharacter> enemyCharacters;

    public OldBattleUnused(List<PlayerCharacter> playerCharacters, List<EnemyCharacter> enemyCharacters) {
        this.playerCharacters = playerCharacters;
        this.enemyCharacters = enemyCharacters;
    }

    public LinkedList<Actor> getBattleOrder() {
        return battleOrder;
    }

    @Override
    public String toString() {
        return this.battleOrder.toString();
    }

    public void initialize() {

        List<Actor> battlePool = new ArrayList<>(this.playerCharacters);
        battlePool.addAll(this.enemyCharacters);

        for (Actor character: battlePool ) {
            character.setInitiative(die.nextInt(20) + character.getSpeed());
        }

        battlePool.sort(new SortActorByInitiative());

        this.battleOrder.addAll(battlePool);
    }

    public void battleStart() throws IOException {
        while (!this.playerCharacters.isEmpty() && !this.enemyCharacters.isEmpty()) {
            turnGameCycle();
        }
    }

    private void turnGameCycle() throws IOException {

        for (Actor character: this.battleOrder) {
            if (character.getClass() == PlayerCharacter.class && character.getHpCurrent() > 0) {

                // open player menu to choose actions
                System.out.println(character.toString());
                System.out.println(character.getHpCurrent());
                //System.in.read();

            } else {

                if (character.getHpCurrent() < 0) {
                    continue;
                }
                PlayerCharacter playerToBeAttacked = getRandomPlayerCharacter();
                character.attackOtherActor(playerToBeAttacked);

                System.out.println(character.toString());
                System.out.println(character.getHpCurrent());
            }

        }

        this.playerCharacters.removeIf(playerCharacter -> playerCharacter.getHpCurrent() < 0);
        this.enemyCharacters.removeIf(enemyCharacter -> enemyCharacter.getHpCurrent() < 0);
    }

    private PlayerCharacter getRandomPlayerCharacter() {

    /*
        if (this.playerCharacters.size() == 0) {

        }

        if (this.playerCharacters.size() < 2) {
            return this.playerCharacters.get(0);
        }

        int choosenCharacterIndex = this.die.nextInt(this.playerCharacters.size());

        return this.playerCharacters.get(choosenCharacterIndex);

     */
        return this.playerCharacters.get(0);
    }
}
