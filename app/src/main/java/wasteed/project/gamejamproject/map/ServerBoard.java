package wasteed.project.gamejamproject.map;

public interface ServerBoard extends ClientBoard {
    boolean isValid(Move move);

    void takeCell(int x, int y);

    void fightCell(int x, int y);

    Tower currentTower();

    MyPair getConfig();

    void loseCell(int x, int y, Tower tower);
}
