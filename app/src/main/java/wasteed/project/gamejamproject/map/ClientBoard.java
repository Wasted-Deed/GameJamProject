package wasteed.project.gamejamproject.map;

import wasteed.project.gamejamproject.Player;

public interface ClientBoard {
    void makeMove(Move move);

    Player getOwner(int x, int y);
}
