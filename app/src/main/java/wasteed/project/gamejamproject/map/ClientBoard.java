package wasteed.project.gamejamproject.map;

public interface ClientBoard {
    void makeMove(Move move, Tower tower);

    boolean isValid(Move move);

    Pair getConfig();

    Tower getTower(int x, int y);
}
