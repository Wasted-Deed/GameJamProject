package wasteed.project.gamejamproject.map;

import androidx.core.util.Pair;

import wasteed.project.gamejamproject.Player;

public interface Battle {
    Player fight(Player attacker, Player defender, int x, int y);

    Pair<Boolean, String> take(Player owner, int x, int y);
}
