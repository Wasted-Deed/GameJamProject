package wasteed.project.gamejamproject;

import wasteed.project.gamejamproject.map.Battle;

public class BattleInterface implements Battle {

    BattleInterface() {

    }

    public Player fight(Player attacker, Player defender, int x, int y) {
        if (attacker.getUnitInterface().getMoney() > 25) {
            attacker.getUnitInterface().setMoney(attacker.getUnitInterface().getMoney() - 25);
            attacker.getUnitInterface().setArmy(attacker.getUnitInterface().getArmy() +
                    defender.getUnitInterface().getArmy() / 10);
            attacker.getUnitInterface().setPeople(attacker.getUnitInterface().getPeople() +
                    defender.getUnitInterface().getPeople() / 10);
            defender.getUnitInterface().setMoney(defender.getUnitInterface().getMoney() - 25);
            defender.getUnitInterface().setArmy(defender.getUnitInterface().getArmy() -
                    defender.getUnitInterface().getArmy() / 10);
            defender.getUnitInterface().setPeople(defender.getUnitInterface().getPeople() -
                    defender.getUnitInterface().getPeople() / 10);
            return attacker;
        } else {
            return defender;
        }
    }
}
