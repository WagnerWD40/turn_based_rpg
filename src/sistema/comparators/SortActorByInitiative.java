package sistema.comparators;

import sistema.characters.Actor;

import java.util.Comparator;

public class SortActorByInitiative implements Comparator<Actor> {

    public int compare(Actor actor1, Actor actor2) {
        return actor2.getInitiative() - actor1.getInitiative();
    }
}
