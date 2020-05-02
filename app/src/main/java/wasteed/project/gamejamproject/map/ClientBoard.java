package wasteed.project.gamejamproject.map;

public interface ClientBoard {
    void makeMove(Move move);

    boolean isValid(Move move);

    Tower getTower(int x, int y);
}
