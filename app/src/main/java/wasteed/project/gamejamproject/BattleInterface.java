package wasteed.project.gamejamproject;

import wasteed.project.gamejamproject.map.Battle;

public class BattleInterface implements Battle {

    BattleInterface() {

    }

    public Player fight(Player attacker, Player defender, int x, int y) {
        if (attacker.getUnitInterface().getArmy() > defender.getUnitInterface().getArmy()) {
            return attacker;
        } else {
            return defender;
        }
    }
}
