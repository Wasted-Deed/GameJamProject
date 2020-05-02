package wasteed.project.gamejamproject.map;

import wasteed.project.gamejamproject.Player;

public interface Battle {
    Player fight(Player attacker, Player defender, int x, int y);
}
