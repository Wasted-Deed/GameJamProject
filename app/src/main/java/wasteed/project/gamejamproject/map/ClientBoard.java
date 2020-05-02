package wasteed.project.gamejamproject.map;

public interface ClientBoard {
    void makeMove(Move move);

    Player getOwner(int x, int y);
}
