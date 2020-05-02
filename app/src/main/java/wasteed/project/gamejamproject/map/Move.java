package wasteed.project.gamejamproject.map;

public class Move {
    private final int x;
    private final int y;
    private final MoveType type;

    public Move(int x, int y, MoveType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public MoveType getType() {
        return type;
    }
}
