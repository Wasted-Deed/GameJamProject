package wasteed.project.gamejamproject.players;

import wasteed.project.gamejamproject.map.BoardConfig;

public abstract class UserInterface {

    private final int simplifyPoints = 75;
    private int gold;
    private int army;
    private int people;

    UserInterface(BoardConfig boardConfig) {
        gold = simplifyPoints;
        army = simplifyPoints;
        people = simplifyPoints;
    }

    protected int getGold() {
        return gold;
    }

    protected int getArmy() {
        return army;
    }

    protected int getPeople() {
        return people;
    }

}
