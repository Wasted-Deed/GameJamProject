package wasteed.project.gamejamproject.map;

public interface ClientBoard {
    void makeMove(Move move);

    Tower getTower(int x, int y);
}
